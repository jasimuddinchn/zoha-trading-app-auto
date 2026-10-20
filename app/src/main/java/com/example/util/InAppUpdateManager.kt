package com.example.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.util.Log
import androidx.core.content.FileProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.concurrent.TimeUnit
import java.util.regex.Pattern

/**
 * In-App Update Checker & Installation Manager for Zoha Trading.
 * Fetches and downloads the latest release APK directly from GitHub Releases:
 * https://github.com/jasimuddinchn/zoha-trading-app/releases/latest/download/app-release.apk
 */
object InAppUpdateManager {
    private const val TAG = "InAppUpdateManager"

    // GitHub Releases Repository and Direct APK Download URL
    const val GITHUB_OWNER = "jasimuddinchn"
    const val GITHUB_REPO = "zoha-trading-app"
    const val REMOTE_APK_URL = "https://github.com/jasimuddinchn/zoha-trading-app/releases/latest/download/app-release.apk"
    const val GITHUB_LATEST_RELEASE_URL = "https://github.com/jasimuddinchn/zoha-trading-app/releases/latest"

    const val CURRENT_VERSION_NAME = "1.0.3"
    const val CURRENT_VERSION_CODE = 3

    data class RemoteUpdateInfo(
        val driveVersionName: String, // Kept for notification & badge compatibility
        val installedVersionName: String,
        val isDriveVersionHigher: Boolean,
        val isSameVersion: Boolean,
        val driveFileName: String
    )

    @Volatile
    var cachedUpdateInfo: RemoteUpdateInfo? = null

    /**
     * Gets installed version name from package manager with fallback.
     */
    fun getInstalledVersionName(context: Context): String {
        return try {
            val pInfo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                context.packageManager.getPackageInfo(context.packageName, android.content.pm.PackageManager.PackageInfoFlags.of(0))
            } else {
                @Suppress("DEPRECATION")
                context.packageManager.getPackageInfo(context.packageName, 0)
            }
            pInfo.versionName ?: CURRENT_VERSION_NAME
        } catch (e: Exception) {
            CURRENT_VERSION_NAME
        }
    }

    /**
     * Extracts version string (e.g. "1.0.4", "1.0.1") from text like "v1.0.4" or "app-release.apk".
     */
    fun extractVersionFromFilename(filename: String): String? {
        val pattern = Pattern.compile("(\\d+\\.\\d+(?:\\.\\d+)?)")
        val matcher = pattern.matcher(filename)
        return if (matcher.find()) {
            matcher.group(1)
        } else {
            null
        }
    }

    /**
     * Reads the real package version info directly from any downloaded APK file on disk.
     */
    fun getApkVersion(context: Context, apkFile: File): Pair<String?, Int?> {
        return try {
            val pInfo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                context.packageManager.getPackageArchiveInfo(apkFile.absolutePath, android.content.pm.PackageManager.PackageInfoFlags.of(0))
            } else {
                @Suppress("DEPRECATION")
                context.packageManager.getPackageArchiveInfo(apkFile.absolutePath, 0)
            }
            val verName = pInfo?.versionName
            val verCode = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                pInfo?.longVersionCode?.toInt()
            } else {
                @Suppress("DEPRECATION")
                pInfo?.versionCode
            }
            Pair(verName, verCode)
        } catch (e: Exception) {
            Pair(null, null)
        }
    }

    /**
     * Compares two semantic version strings (e.g. "1.0.4" vs "1.0.3").
     * Returns:
     *   > 0 if v1 > v2 (v1 is higher)
     *   < 0 if v1 < v2 (v1 is lower)
     *   == 0 if v1 == v2 (same version)
     */
    fun compareVersionStrings(v1: String, v2: String): Int {
        val v1Parts = v1.replace(Regex("[^0-9.]"), "").split(".").mapNotNull { it.toIntOrNull() }
        val v2Parts = v2.replace(Regex("[^0-9.]"), "").split(".").mapNotNull { it.toIntOrNull() }
        val maxLen = maxOf(v1Parts.size, v2Parts.size)
        for (i in 0 until maxLen) {
            val p1 = if (i < v1Parts.size) v1Parts[i] else 0
            val p2 = if (i < v2Parts.size) v2Parts[i] else 0
            if (p1 > p2) return 1
            if (p1 < p2) return -1
        }
        return 0
    }

    /**
     * Checks GitHub Releases to discover the latest uploaded APK release version.
     */
    suspend fun checkRemoteVersion(context: Context): Result<RemoteUpdateInfo> = withContext(Dispatchers.IO) {
        try {
            val installedVersion = getInstalledVersionName(context)
            val client = OkHttpClient.Builder()
                .followRedirects(true)
                .followSslRedirects(true)
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build()

            // 1. Fetch latest release page to inspect redirect URL and tag
            val request = Request.Builder()
                .url(GITHUB_LATEST_RELEASE_URL)
                .header("User-Agent", "Mozilla/5.0 (Linux; Android 14) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0 Mobile Safari/537.36")
                .build()

            val response = client.newCall(request).execute()
            var detectedVersion: String? = null

            if (response.isSuccessful) {
                val finalUrl = response.request.url.toString()
                if (finalUrl.contains("/tag/")) {
                    val tagSegment = finalUrl.substringAfterLast("/tag/")
                    detectedVersion = extractVersionFromFilename(tagSegment)
                }
                if (detectedVersion == null) {
                    val html = response.body?.string() ?: ""
                    val titleMatcher = Pattern.compile("<title>Release\\s+([^<]+?)\\s+·").matcher(html)
                    if (titleMatcher.find()) {
                        val rawTitle = titleMatcher.group(1) ?: ""
                        detectedVersion = extractVersionFromFilename(rawTitle)
                    }
                }
            }

            val finalVersion = detectedVersion ?: "1.0.4"
            val comp = compareVersionStrings(finalVersion, installedVersion)
            val isHigher = comp > 0
            val isSame = comp == 0

            val info = RemoteUpdateInfo(
                driveVersionName = finalVersion,
                installedVersionName = installedVersion,
                isDriveVersionHigher = isHigher,
                isSameVersion = isSame,
                driveFileName = "app-release.apk"
            )
            cachedUpdateInfo = info

            if (!isHigher) {
                clearExistingApks(context)
            }

            Result.success(info)
        } catch (e: Exception) {
            Log.e(TAG, "Error checking remote version from GitHub", e)
            Result.failure(e)
        }
    }

    /**
     * Clears all downloaded APKs from the cache directory.
     */
    fun clearExistingApks(context: Context) {
        try {
            val dir = File(context.cacheDir, "updates")
            if (dir.exists()) {
                dir.listFiles()?.forEach { file ->
                    if (file.isFile) file.delete()
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error clearing updates cache", e)
        }
    }

    /**
     * Retrieves already downloaded APK file if present, valid, and strictly higher than installed version.
     */
    fun getExistingApk(context: Context, targetVersionName: String? = null): File? {
        val dir = File(context.cacheDir, "updates")
        if (!dir.exists()) return null
        val installedVersion = getInstalledVersionName(context)

        val files = dir.listFiles() ?: return null
        for (file in files) {
            if (file.isFile && file.name.endsWith(".apk") && file.length() > 500_000 && isValidApkFile(file)) {
                val apkVer = getApkVersion(context, file).first ?: extractVersionFromFilename(file.name)
                if (apkVer != null && compareVersionStrings(apkVer, installedVersion) > 0) {
                    return file
                } else if (apkVer != null && compareVersionStrings(apkVer, installedVersion) <= 0) {
                    file.delete()
                } else if (file.name == "app-release.apk") {
                    return file
                }
            }
        }
        return null
    }

    /**
     * Downloads APK from GitHub direct release link:
     * https://github.com/jasimuddinchn/zoha-trading-app/releases/latest/download/app-release.apk
     * Follows redirect to storage CDN and streams chunks with live progress.
     */
    suspend fun downloadApk(
        context: Context,
        targetVersionName: String = CURRENT_VERSION_NAME,
        onProgress: (downloadedBytes: Long, totalBytes: Long) -> Unit
    ): Result<File> = withContext(Dispatchers.IO) {
        try {
            val updatesDir = File(context.cacheDir, "updates").apply { mkdirs() }
            val tempFile = File(updatesDir, "app-release.apk.download")
            val targetFile = File(updatesDir, "app-release.apk")

            if (tempFile.exists()) tempFile.delete()

            val client = OkHttpClient.Builder()
                .followRedirects(true)
                .followSslRedirects(true)
                .connectTimeout(45, TimeUnit.SECONDS)
                .readTimeout(180, TimeUnit.SECONDS)
                .build()

            val request = Request.Builder()
                .url(REMOTE_APK_URL)
                .header("User-Agent", "Mozilla/5.0 (Linux; Android 14) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0 Mobile Safari/537.36")
                .build()

            val response = client.newCall(request).execute()

            if (!response.isSuccessful) {
                return@withContext Result.failure(Exception("HTTP error ${response.code}: ${response.message}"))
            }

            val downloadBody = response.body ?: return@withContext Result.failure(Exception("APK response body is empty"))
            val totalBytes = downloadBody.contentLength()

            downloadBody.byteStream().use { inputStream ->
                FileOutputStream(tempFile).use { outputStream ->
                    val buffer = ByteArray(8192)
                    var bytesRead: Int
                    var totalDownloaded = 0L

                    while (inputStream.read(buffer).also { bytesRead = it } != -1) {
                        outputStream.write(buffer, 0, bytesRead)
                        totalDownloaded += bytesRead
                        onProgress(totalDownloaded, totalBytes)
                    }
                    outputStream.flush()
                }
            }

            if (!isValidApkFile(tempFile)) {
                tempFile.delete()
                return@withContext Result.failure(Exception("Downloaded file failed integrity verification (invalid APK format)"))
            }

            if (targetFile.exists()) targetFile.delete()
            if (!tempFile.renameTo(targetFile)) {
                tempFile.copyTo(targetFile, overwrite = true)
                tempFile.delete()
            }

            Result.success(targetFile)
        } catch (e: Exception) {
            Log.e(TAG, "Error downloading update APK from GitHub Releases", e)
            Result.failure(e)
        }
    }

    /**
     * Checks if a file has the standard ZIP / APK signature (starts with 'PK\x03\x04')
     * and is at least 500 KB.
     */
    private fun isValidApkFile(file: File): Boolean {
        if (!file.exists() || file.length() < 500_000) return false
        return try {
            FileInputStream(file).use { fis ->
                val header = ByteArray(4)
                val read = fis.read(header)
                read == 4 &&
                    header[0] == 0x50.toByte() && // 'P'
                    header[1] == 0x4B.toByte() && // 'K'
                    header[2] == 0x03.toByte() &&
                    header[3] == 0x04.toByte()
            }
        } catch (e: Exception) {
            false
        }
    }

    /**
     * Triggers direct in-app package installation using FileProvider.
     * If Android 8.0+ unknown app install permission is missing, opens settings prompt.
     */
    fun triggerInstall(context: Context, apkFile: File): Result<Unit> {
        return try {
            if (!apkFile.exists() || apkFile.length() < 500_000) {
                return Result.failure(IllegalStateException("APK file is missing or invalid."))
            }

            // On Android 8.0+ (Oreo, API 26+), verify if install unknown apps permission is granted
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if (!context.packageManager.canRequestPackageInstalls()) {
                    val settingsIntent = Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES).apply {
                        data = Uri.parse("package:${context.packageName}")
                        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    }
                    context.startActivity(settingsIntent)
                    return Result.failure(
                        SecurityException("Please allow 'Install unknown apps' permission for Zoha Trading in Settings, then tap Install.")
                    )
                }
            }

            val apkUri = FileProvider.getUriForFile(
                context,
                "${context.packageName}.fileprovider",
                apkFile
            )

            val installIntent = Intent(Intent.ACTION_VIEW).apply {
                setDataAndType(apkUri, "application/vnd.android.package-archive")
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(installIntent)
            Result.success(Unit)
        } catch (e: Exception) {
            Log.e(TAG, "Failed to launch installer", e)
            Result.failure(e)
        }
    }

    /**
     * Opens direct download link in external browser as a fallback.
     */
    fun openInBrowser(context: Context) {
        try {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(REMOTE_APK_URL)).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(browserIntent)
        } catch (e: Exception) {
            Log.e(TAG, "Failed to open browser", e)
        }
    }
}
