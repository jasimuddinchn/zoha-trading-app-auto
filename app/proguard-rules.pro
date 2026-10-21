# Disable R8/ProGuard shrinking, optimization, and obfuscation
-dontshrink
-dontoptimize
-dontobfuscate

# Preserve attributes required for runtime reflection, Room, and Compose
-keepattributes *Annotation*,Signature,InnerClasses,EnclosingMethod,SourceFile,LineNumberTable

# Preserve all app classes, models, DAOs, and database
-keep class com.example.** { *; }
-keepclassmembers class com.example.** { *; }

# Preserve AndroidX, Lifecycle, ViewModel, Fragment, and Compose
-keep class androidx.** { *; }
-keepclassmembers class androidx.** { *; }

# Preserve Room database and entities
-keep class * extends androidx.room.RoomDatabase
-keep @androidx.room.Entity class * { *; }
-keep @androidx.room.Dao interface * { *; }
-keep class * implements androidx.room.RoomDatabase { *; }

# Preserve Moshi JSON serialization
-keep class com.squareup.moshi.** { *; }
-keep @com.squareup.moshi.JsonQualifier interface * { *; }
-keep @com.squareup.moshi.JsonClass class * { *; }
-keepclassmembers class * {
    @com.squareup.moshi.FromJson *;
    @com.squareup.moshi.ToJson *;
}

# Preserve Firebase and Google Play Services
-keep class com.google.firebase.** { *; }
-keepclassmembers class com.google.firebase.** { *; }
-keep class com.google.android.gms.** { *; }
-keepclassmembers class com.google.android.gms.** { *; }

# Preserve OkHttp and Retrofit
-keep class okhttp3.** { *; }
-keepclassmembers class okhttp3.** { *; }
-keep class retrofit2.** { *; }
-keepclassmembers class retrofit2.** { *; }

# Preserve Kotlin Coroutines and Reflection
-keep class kotlin.** { *; }
-keep class kotlinx.coroutines.** { *; }

# Suppress all warnings
-dontwarn **
