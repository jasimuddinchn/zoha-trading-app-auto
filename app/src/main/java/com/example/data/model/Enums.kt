package com.example.data.model

enum class UserRole(val labelBn: String, val labelEn: String) {
    TEAM_MEMBER("Team Member", "Team Member"),
    ADMIN("Admin Mode 👑", "Admin Mode 👑")
}

enum class PhoneStatus(val code: String, val labelBn: String, val labelEn: String) {
    IN_STOCK("IN_STOCK", "In Stock", "In Stock"),
    IN_TRANSIT("IN_TRANSIT", "In Transit (China)", "In Transit (China)"),
    SOLD("SOLD", "Sold", "Sold"),
    RETURNED("RETURNED", "Returned", "Returned")
}

enum class ConditionGrade(val labelBn: String, val labelEn: String) {
    GRADE_A("Grade A", "Grade A"),
    GRADE_B("Grade B", "Grade B"),
    GRADE_C("Grade C", "Grade C")
}

val BRAND_OPTIONS = listOf(
    "Apple",
    "Samsung",
    "Google",
    "Xiaomi",
    "Redmi",
    "OnePlus",
    "Vivo",
    "Oppo",
    "Realme",
    "Honor",
    "Huawei",
    "Motorola",
    "Infinix",
    "Tecno",
    "Nothing",
    "Sony",
    "Custom..."
)

val COMMON_PHONE_MODELS: Map<String, List<String>> = mapOf(
    "Apple" to listOf(
        "iPhone 16 Pro Max", "iPhone 16 Pro", "iPhone 16 Plus", "iPhone 16",
        "iPhone 15 Pro Max", "iPhone 15 Pro", "iPhone 15 Plus", "iPhone 15",
        "iPhone 14 Pro Max", "iPhone 14 Pro", "iPhone 14 Plus", "iPhone 14",
        "iPhone 13 Pro Max", "iPhone 13 Pro", "iPhone 13", "iPhone 13 mini",
        "iPhone 12 Pro Max", "iPhone 12 Pro", "iPhone 12", "iPhone 12 mini",
        "iPhone 11 Pro Max", "iPhone 11 Pro", "iPhone 11",
        "iPhone XS Max", "iPhone XS", "iPhone XR", "iPhone X",
        "iPhone SE (3rd Gen)", "iPhone SE (2nd Gen)", "iPhone SE (1st Gen)",
        "iPhone 8 Plus", "iPhone 8", "iPhone 7 Plus", "iPhone 7", "iPhone 6s Plus", "iPhone 6s", "iPhone 6 Plus", "iPhone 6",
        "iPad Pro 13 (M4)", "iPad Pro 11 (M4)", "iPad Pro 12.9", "iPad Pro 11",
        "iPad Air 13 (M2)", "iPad Air 11 (M2)", "iPad Air 5", "iPad Air 4",
        "iPad 10th Gen", "iPad 9th Gen", "iPad mini 7", "iPad mini 6"
    ),
    "iPhone" to listOf(
        "iPhone 16 Pro Max", "iPhone 16 Pro", "iPhone 16 Plus", "iPhone 16",
        "iPhone 15 Pro Max", "iPhone 15 Pro", "iPhone 15 Plus", "iPhone 15",
        "iPhone 14 Pro Max", "iPhone 14 Pro", "iPhone 14 Plus", "iPhone 14",
        "iPhone 13 Pro Max", "iPhone 13 Pro", "iPhone 13", "iPhone 13 mini",
        "iPhone 12 Pro Max", "iPhone 12 Pro", "iPhone 12", "iPhone 12 mini",
        "iPhone 11 Pro Max", "iPhone 11 Pro", "iPhone 11",
        "iPhone XS Max", "iPhone XS", "iPhone XR", "iPhone X",
        "iPhone SE (3rd Gen)", "iPhone SE (2nd Gen)", "iPhone SE (1st Gen)",
        "iPhone 8 Plus", "iPhone 8", "iPhone 7 Plus", "iPhone 7", "iPhone 6s Plus", "iPhone 6s", "iPhone 6 Plus", "iPhone 6"
    ),
    "Samsung" to listOf(
        "Galaxy S25 Ultra", "Galaxy S25+", "Galaxy S25",
        "Galaxy S24 Ultra", "Galaxy S24+", "Galaxy S24", "Galaxy S24 FE",
        "Galaxy S23 Ultra", "Galaxy S23+", "Galaxy S23", "Galaxy S23 FE",
        "Galaxy S22 Ultra", "Galaxy S22+", "Galaxy S22",
        "Galaxy S21 Ultra", "Galaxy S21+", "Galaxy S21", "Galaxy S21 FE",
        "Galaxy S20 Ultra", "Galaxy S20+", "Galaxy S20", "Galaxy S20 FE",
        "Galaxy S10+", "Galaxy S10", "Galaxy S10e", "Galaxy S10 Lite", "Galaxy S9+", "Galaxy S9",
        "Galaxy Note 20 Ultra 5G", "Galaxy Note 20", "Galaxy Note 10+", "Galaxy Note 10", "Galaxy Note 9", "Galaxy Note 8",
        "Galaxy Z Fold 6", "Galaxy Z Flip 6", "Galaxy Z Fold 5", "Galaxy Z Flip 5", "Galaxy Z Fold 4", "Galaxy Z Flip 4", "Galaxy Z Fold 3", "Galaxy Z Flip 3", "Galaxy Z Fold 2", "Galaxy Z Flip", "Galaxy Fold",
        "Galaxy A55 5G", "Galaxy A54 5G", "Galaxy A53 5G", "Galaxy A52s 5G", "Galaxy A52 5G", "Galaxy A52 4G", "Galaxy A51", "Galaxy A50",
        "Galaxy A35 5G", "Galaxy A34 5G", "Galaxy A33 5G", "Galaxy A32 5G", "Galaxy A32 4G", "Galaxy A31", "Galaxy A30",
        "Galaxy A25 5G", "Galaxy A24 4G", "Galaxy A23 5G", "Galaxy A23 4G", "Galaxy A22 5G", "Galaxy A22 4G", "Galaxy A21s", "Galaxy A20",
        "Galaxy A16 5G", "Galaxy A16 4G", "Galaxy A15 5G", "Galaxy A15 4G", "Galaxy A14 5G", "Galaxy A14 4G", "Galaxy A13 5G", "Galaxy A13 4G", "Galaxy A12", "Galaxy A11", "Galaxy A10s",
        "Galaxy A06", "Galaxy A05s", "Galaxy A05", "Galaxy A04s", "Galaxy A04e", "Galaxy A04", "Galaxy A03s", "Galaxy A03", "Galaxy A02s",
        "Galaxy M55 5G", "Galaxy M54 5G", "Galaxy M53 5G", "Galaxy M52 5G", "Galaxy M35 5G", "Galaxy M34 5G", "Galaxy M33 5G", "Galaxy M32", "Galaxy M15 5G", "Galaxy M14 5G", "Galaxy M13", "Galaxy M12",
        "Galaxy F55 5G", "Galaxy F54 5G", "Galaxy F34 5G", "Galaxy F15 5G", "Galaxy F14 5G",
        "Galaxy Tab S9 Ultra", "Galaxy Tab S9+", "Galaxy Tab S9", "Galaxy Tab S9 FE+", "Galaxy Tab S9 FE", "Galaxy Tab S8 Ultra", "Galaxy Tab S8+", "Galaxy Tab S8", "Galaxy Tab A9+", "Galaxy Tab A9"
    ),
    "Google" to listOf(
        "Pixel 9 Pro Fold", "Pixel 9 Pro XL", "Pixel 9 Pro", "Pixel 9",
        "Pixel 8a", "Pixel 8 Pro", "Pixel 8",
        "Pixel 7A", "Pixel 7a", "Pixel 7 Pro", "Pixel 7",
        "Pixel Fold",
        "Pixel 6a", "Pixel 6 Pro", "Pixel 6",
        "Pixel 5a 5G", "Pixel 5",
        "Pixel 4a 5G", "Pixel 4a", "Pixel 4 XL", "Pixel 4",
        "Pixel 3a XL", "Pixel 3a", "Pixel 3 XL", "Pixel 3",
        "Pixel 2XL", "Pixel 2 XL", "Pixel 2",
        "Pixel XL", "Pixel 1",
        "Pixel Tablet"
    ),
    "Google Pixel" to listOf(
        "Pixel 9 Pro Fold", "Pixel 9 Pro XL", "Pixel 9 Pro", "Pixel 9",
        "Pixel 8a", "Pixel 8 Pro", "Pixel 8",
        "Pixel 7A", "Pixel 7a", "Pixel 7 Pro", "Pixel 7",
        "Pixel Fold",
        "Pixel 6a", "Pixel 6 Pro", "Pixel 6",
        "Pixel 5a 5G", "Pixel 5",
        "Pixel 4a 5G", "Pixel 4a", "Pixel 4 XL", "Pixel 4",
        "Pixel 3a XL", "Pixel 3a", "Pixel 3 XL", "Pixel 3",
        "Pixel 2XL", "Pixel 2 XL", "Pixel 2",
        "Pixel XL", "Pixel 1",
        "Pixel Tablet"
    ),
    "Xiaomi" to listOf(
        "15 Ultra", "15 Pro", "15",
        "14 Ultra", "14 Pro", "14", "14T Pro", "14T", "14 Civi",
        "13 Ultra", "13 Pro", "13", "13T Pro", "13T", "13 Lite",
        "12 Pro", "12", "12X", "12T Pro", "12T", "12 Lite",
        "11 Ultra", "11 Pro", "11", "11T Pro", "11T", "11 Lite 5G NE", "11 Lite",
        "10 Pro", "10", "10T Pro", "10T", "10 Lite",
        "Mix Fold 4", "Mix Fold 3", "Mix Fold 2", "Mix Flip", "Mix 4",
        "Civi 4 Pro", "Civi 3", "Civi 2", "Civi 1S",
        "Pad 6S Pro", "Pad 6 Pro", "Pad 6", "Pad 5 Pro", "Pad 5",
        "Poco F6 Pro", "Poco F6", "Poco X6 Pro", "Poco X6", "Poco X6 Neo", "Poco M6 Pro", "Poco M6 5G", "Poco M6 Plus", "Poco C65", "Poco C61",
        "Poco F5 Pro", "Poco F5", "Poco X5 Pro", "Poco X5", "Poco M5", "Poco M5s", "Poco C55",
        "Poco F4 GT", "Poco F4", "Poco X4 Pro", "Poco X4 GT", "Poco F3", "Poco X3 Pro", "Poco X3 NFC", "Poco M4 Pro"
    ),
    "Redmi" to listOf(
        "Note 14 Pro+ 5G", "Note 14 Pro 5G", "Note 14 5G",
        "Note 13 Pro+ 5G", "Note 13 Pro 5G", "Note 13 Pro 4G", "Note 13 5G", "Note 13 4G", "Note 13R",
        "Note 12 Pro+ 5G", "Note 12 Pro 5G", "Note 12 Pro 4G", "Note 12 5G", "Note 12 4G", "Note 12 Turbo", "Note 12S", "Note 12T Pro",
        "Note 11 Pro+ 5G", "Note 11 Pro 5G", "Note 11 Pro 4G", "Note 11S 5G", "Note 11S", "Note 11 4G", "Note 11T Pro",
        "Note 10 Pro Max", "Note 10 Pro", "Note 10S", "Note 10 5G", "Note 10",
        "Note 9 Pro Max", "Note 9 Pro", "Note 9S", "Note 9",
        "Note 8 Pro", "Note 8", "Note 7 Pro", "Note 7",
        "K70 Ultra", "K70 Pro", "K70", "K70E",
        "K60 Ultra", "K60 Pro", "K60", "K60E",
        "K50 Gaming", "K50 Ultra", "K50 Pro", "K50",
        "K40 Gaming", "K40 Pro+", "K40 Pro", "K40",
        "Turbo 3",
        "14C", "13C 5G", "13C", "13 5G", "12 5G", "12 4G", "12C", "10C", "10A", "10 2022", "9T", "9A", "9C", "8A",
        "A3x", "A3", "A2+", "A2", "A1+", "A1",
        "Pad Pro", "Pad SE"
    ),
    "OnePlus" to listOf(
        "13", "12", "12R",
        "11", "11R",
        "10 Pro", "10T", "10R",
        "9 Pro", "9", "9RT", "9R",
        "8 Pro", "8T", "8",
        "7T Pro", "7 Pro", "7T", "7",
        "6T", "6", "5T", "5",
        "Open",
        "Nord 4", "Nord 3 5G", "Nord 2T 5G", "Nord 2 5G", "Nord",
        "Nord CE 4 5G", "Nord CE 4 Lite 5G", "Nord CE 3 5G", "Nord CE 3 Lite 5G", "Nord CE 2 5G", "Nord CE 2 Lite 5G",
        "Nord N30 5G", "Nord N20 5G", "Nord N200 5G",
        "Ace 3 Pro", "Ace 3", "Ace 3V", "Ace 2 Pro", "Ace 2", "Ace 2V", "Ace Pro", "Ace", "Ace Racing Edition",
        "Pad 2", "Pad Go", "Pad"
    ),
    "Vivo" to listOf(
        "X200 Pro", "X200 Pro mini", "X200",
        "X100 Ultra", "X100 Pro", "X100s Pro", "X100s", "X100",
        "X90 Pro+", "X90 Pro", "X90s", "X90",
        "X80 Pro", "X80", "X70 Pro+", "X70 Pro", "X60 Pro+", "X60 Pro",
        "X Fold 3 Pro", "X Fold 3", "X Fold 2", "X Fold+", "X Fold", "X Flip",
        "V40 Pro", "V40", "V40e", "V40 Lite 5G", "V40 Lite 4G",
        "V30 Pro", "V30", "V30e", "V30 Lite 5G", "V30 Lite 4G",
        "V29 Pro", "V29", "V29e",
        "V27 Pro", "V27", "V27e",
        "V25 Pro", "V25", "V25e",
        "V23 Pro", "V23 5G", "V23e", "V21 5G", "V20", "V19",
        "T3 Ultra", "T3 Pro 5G", "T3 5G", "T3x 5G", "T3 Lite 5G",
        "T2 Pro 5G", "T2 5G", "T2x 5G", "T1 Pro 5G", "T1 5G",
        "Y300 Pro", "Y200 Pro 5G", "Y200 5G", "Y200e 5G", "Y100 5G", "Y100", "Y58 5G", "Y38 5G", "Y28 5G", "Y28 4G", "Y27s", "Y27 5G", "Y27", "Y18", "Y18e", "Y18t", "Y17s", "Y03", "Y02t", "Y22", "Y21", "Y20",
        "iQOO 13", "iQOO 12 Pro", "iQOO 12", "iQOO 11 Pro", "iQOO 11",
        "iQOO Neo 9 Pro", "iQOO Neo 9", "iQOO Neo 8 Pro", "iQOO Neo 7 Pro",
        "iQOO Z9 Turbo+", "iQOO Z9 Turbo", "iQOO Z9s Pro", "iQOO Z9s", "iQOO Z9 5G", "iQOO Z9x", "iQOO Z7 Pro"
    ),
    "Oppo" to listOf(
        "Find X8 Pro", "Find X8",
        "Find X7 Ultra", "Find X7",
        "Find X6 Pro", "Find X6",
        "Find X5 Pro", "Find X5",
        "Find N3", "Find N3 Flip", "Find N2", "Find N2 Flip", "Find N",
        "Reno 12 Pro 5G", "Reno 12 5G", "Reno 12F 5G", "Reno 12F 4G",
        "Reno 11 Pro 5G", "Reno 11 5G", "Reno 11F 5G",
        "Reno 10 Pro+ 5G", "Reno 10 Pro 5G", "Reno 10 5G",
        "Reno 8 Pro 5G", "Reno 8 5G", "Reno 8T 5G", "Reno 8T 4G",
        "Reno 7 Pro 5G", "Reno 7 5G", "Reno 6 Pro 5G", "Reno 6 5G",
        "F27 Pro+ 5G", "F27 5G", "F25 Pro 5G", "F23 5G", "F21s Pro 5G", "F21s Pro", "F21 Pro 5G", "F21 Pro", "F19 Pro+", "F19 Pro", "F19", "F17 Pro", "F17",
        "K12x 5G", "K12 5G", "K11 5G", "K10 5G",
        "A3 Pro 5G", "A3x 5G", "A3x", "A3 5G", "A3", "A79 5G", "A78 5G", "A78 4G", "A59 5G", "A58 4G", "A38", "A18", "A17", "A17k", "A16", "A15s"
    ),
    "Realme" to listOf(
        "GT 7 Pro", "GT 6", "GT 6T", "GT 5 Pro", "GT 5", "GT Neo 6", "GT Neo 6 SE", "GT Neo 5", "GT Neo 3", "GT 2 Pro", "GT 2", "GT Master Edition",
        "13 Pro+ 5G", "13 Pro 5G", "13+ 5G", "13 5G",
        "12 Pro+ 5G", "12 Pro 5G", "12+ 5G", "12 5G", "12x 5G",
        "11 Pro+ 5G", "11 Pro 5G", "11 5G", "11x 5G",
        "10 Pro+ 5G", "10 Pro 5G", "10 4G",
        "9 Pro+ 5G", "9 Pro 5G", "9 4G", "9 5G", "9i 5G", "9i",
        "8 Pro", "8", "8 5G", "8i", "7 Pro", "7", "6 Pro", "6",
        "P2 Pro 5G", "P1 Pro 5G", "P1 Speed 5G", "P1 5G",
        "Narzo 70 Pro 5G", "Narzo 70 Turbo 5G", "Narzo 70 5G", "Narzo 70x 5G",
        "Narzo 60 Pro 5G", "Narzo 60 5G", "Narzo 60x 5G", "Narzo 50 Pro 5G", "Narzo 50",
        "C67 4G", "C65 5G", "C65", "C63 5G", "C63", "C61", "C55", "C53", "C51", "C35", "C33", "C31", "C30", "C25Y", "C21Y", "C11"
    ),
    "Honor" to listOf(
        "Magic 7 Pro", "Magic 7",
        "Magic 6 Pro", "Magic 6", "Magic 6 RSR Porsche Design", "Magic 6 Lite",
        "Magic V3", "Magic V2", "Magic Vs", "Magic V", "Magic V Flip",
        "Magic 5 Pro", "Magic 5", "Magic 5 Lite",
        "Magic 4 Pro",
        "200 Pro", "200", "200 Lite", "200 Smart",
        "100 Pro", "100",
        "90 Pro", "90", "90 Lite",
        "70 Pro+", "70 Pro", "70",
        "50 Pro", "50", "50 Lite",
        "X9b 5G", "X9a 5G", "X8b", "X8a", "X7b 5G", "X7b", "X6b", "X6a", "X5 Plus"
    ),
    "Huawei" to listOf(
        "Mate 70 Pro", "Mate 70",
        "Pura 70 Ultra", "Pura 70 Pro+", "Pura 70 Pro", "Pura 70",
        "Mate 60 Pro+", "Mate 60 Pro", "Mate 60 RS", "Mate 60",
        "Mate X5", "Mate X3", "Mate Xs 2",
        "P60 Pro", "P60 Art", "P60",
        "Mate 50 Pro", "Mate 50 RS", "Mate 50",
        "P50 Pro", "P50 Pocket", "P50",
        "Nova 13 Pro", "Nova 13", "Nova 12 Ultra", "Nova 12 Pro", "Nova 12", "Nova 12 Lite", "Nova 12 SE",
        "Nova 11 Pro", "Nova 11 Ultra", "Nova 11", "Nova 11i",
        "Nova 10 Pro", "Nova 10", "Nova 9",
        "Nova Y91", "Nova Y72", "Nova Y70", "Nova Y61"
    ),
    "Motorola" to listOf(
        "Edge 50 Ultra", "Edge 50 Pro", "Edge 50 Fusion", "Edge 50 Neo",
        "Edge 40 Pro", "Edge 40", "Edge 40 Neo",
        "Edge 30 Ultra", "Edge 30 Pro", "Edge 30 Fusion", "Edge 30 Neo", "Edge 30",
        "Razr 50 Ultra", "Razr 50", "Razr 40 Ultra", "Razr 40",
        "G85 5G", "G84 5G", "G64 5G", "G54 5G", "G45 5G", "G34 5G", "G24 Power", "G24", "G14", "G04s", "G04", "E13"
    ),
    "Infinix" to listOf(
        "GT 20 Pro", "GT 10 Pro",
        "Zero Flip", "Zero 30 5G", "Zero 30 4G", "Zero Ultra", "Zero 20",
        "Note 40 Pro+ 5G", "Note 40 Pro 5G", "Note 40 Pro 4G", "Note 40 5G", "Note 40 4G", "Note 40X 5G",
        "Note 30 Pro", "Note 30 5G", "Note 30 VIP", "Note 30", "Note 12 Pro", "Note 12",
        "Hot 50 Pro+", "Hot 50 5G", "Hot 40 Pro", "Hot 40i", "Hot 30 5G", "Hot 30i", "Hot 30", "Hot 20 5G", "Hot 20", "Hot 12",
        "Smart 8 Pro", "Smart 8 Plus", "Smart 8", "Smart 8 HD", "Smart 7 HD", "Smart 7"
    ),
    "Tecno" to listOf(
        "Phantom V Fold 2", "Phantom V Flip 2", "Phantom V Fold", "Phantom V Flip", "Phantom X2 Pro", "Phantom X2",
        "Camon 30 Premier 5G", "Camon 30 Pro 5G", "Camon 30 5G", "Camon 30", "Camon 30S Pro",
        "Camon 20 Premier 5G", "Camon 20 Pro 5G", "Camon 20",
        "Camon 19 Pro", "Camon 19",
        "Pova 6 Pro 5G", "Pova 6", "Pova 6 Neo", "Pova 5 Pro 5G", "Pova 5", "Pova Neo 3",
        "Spark 30 Pro", "Spark 20 Pro+", "Spark 20 Pro", "Spark 20 4G", "Spark 20C", "Spark 10 Pro", "Spark 10", "Spark Go 2024", "Spark Go 1",
        "Pop 8", "Pop 7 Pro", "Pop 7"
    ),
    "Nothing" to listOf(
        "(2a) Plus", "(2a)", "(2)", "(1)", "CMF Phone 1"
    ),
    "Sony" to listOf(
        "Xperia 1 VI", "Xperia 1 V", "Xperia 1 IV", "Xperia 1 III", "Xperia 1 II", "Xperia 1",
        "Xperia 5 V", "Xperia 5 IV", "Xperia 5 III", "Xperia 5 II", "Xperia 5",
        "Xperia 10 VI", "Xperia 10 V", "Xperia 10 IV", "Xperia 10 III", "Xperia 10 II",
        "Xperia Pro-I", "Xperia Pro"
    )
)

val COLOR_OPTIONS = listOf(
    "Black",
    "Space Gray",
    "Silver",
    "White",
    "Gold",
    "Deep Purple",
    "Natural Titanium",
    "Black Titanium",
    "Desert Titanium",
    "Blue",
    "Green",
    "Midnight",
    "Starlight",
    "Red",
    "Yellow",
    "Purple",
    "Custom..."
)

val APPLE_STORAGE_OPTIONS = listOf(
    "16GB",
    "32GB",
    "64GB",
    "128GB",
    "256GB",
    "512GB",
    "1TB",
    "2TB",
    "Custom..."
)

val DEFAULT_STORAGE_OPTIONS = listOf(
    "12-128GB",
    "12-256GB",
    "12-512GB",
    "12-1TB",
    "16-128GB",
    "16-256GB",
    "16-512GB",
    "16-1TB",
    "8-128GB",
    "8-256GB",
    "6-128GB",
    "4-64GB",
    "Custom..."
)

val STORAGE_OPTIONS = DEFAULT_STORAGE_OPTIONS

fun isAppleBrand(brand: String): Boolean {
    val b = brand.trim().lowercase()
    return b == "apple" || b == "iphone"
}

fun getModelsForBrand(brand: String): List<String> {
    val clean = brand.trim()
    val rawList = COMMON_PHONE_MODELS[clean]
        ?: COMMON_PHONE_MODELS.entries.find { it.key.equals(clean, ignoreCase = true) }?.value
        ?: emptyList()
    return rawList.map { cleanModelForBrand(clean, it) }.distinct()
}

fun cleanModelForBrand(brand: String, model: String): String {
    val b = brand.trim()
    var m = model.trim()
    if (b.isBlank() || m.isBlank()) return m

    // 1. Google Pixel / Pixel / Google
    // Preserves "Pixel" prefix in models like "Pixel 8", "Pixel 2XL", "Pixel 7A"
    if (b.equals("Google Pixel", ignoreCase = true) || b.equals("Pixel", ignoreCase = true) || b.equals("Google", ignoreCase = true)) {
        if (m.startsWith("Google Pixel ", ignoreCase = true)) {
            m = m.substring("Google ".length).trim()
        } else if (m.startsWith("Google ", ignoreCase = true)) {
            val rest = m.substring("Google ".length).trim()
            m = if (rest.startsWith("Pixel", ignoreCase = true)) rest else "Pixel $rest"
        }
        return m
    }

    // 2. Apple / iPhone
    // Preserves "iPhone" prefix in models like "iPhone 12", "iPhone 13 Pro Max"
    if (b.equals("Apple", ignoreCase = true) || b.equals("iPhone", ignoreCase = true)) {
        if (m.startsWith("Apple iPhone ", ignoreCase = true)) {
            m = m.substring("Apple ".length).trim()
        } else if (m.startsWith("Apple ", ignoreCase = true)) {
            val rest = m.substring("Apple ".length).trim()
            m = if (rest.startsWith("iPhone", ignoreCase = true) || rest.startsWith("iPad", ignoreCase = true)) rest else "iPhone $rest"
        }
        return m
    }

    // 3. Samsung
    // Preserves "Galaxy" prefix in models like "Galaxy S24 Ultra", "Galaxy S25"
    if (b.equals("Samsung", ignoreCase = true) || b.equals("Galaxy", ignoreCase = true)) {
        if (m.startsWith("Samsung Galaxy ", ignoreCase = true)) {
            m = m.substring("Samsung ".length).trim()
        } else if (m.startsWith("Samsung ", ignoreCase = true)) {
            val rest = m.substring("Samsung ".length).trim()
            m = if (rest.startsWith("Galaxy", ignoreCase = true)) rest else "Galaxy $rest"
        }
        return m
    }

    // 4. Motorola
    if (b.equals("Motorola", ignoreCase = true) || b.equals("Moto", ignoreCase = true)) {
        val prefixes = listOf("Motorola Moto ", "Motorola ", "Moto ")
        for (prefix in prefixes) {
            if (m.startsWith(prefix, ignoreCase = true)) {
                m = m.substring(prefix.length).trim()
            }
        }
        return m
    }

    // 5. Nothing
    if (b.equals("Nothing", ignoreCase = true)) {
        val prefixes = listOf("Nothing Phone ", "Phone ", "Nothing ")
        for (prefix in prefixes) {
            if (m.startsWith(prefix, ignoreCase = true)) {
                m = m.substring(prefix.length).trim()
            }
        }
        return m
    }

    // 6. Generic brands where model starts with the brand name itself
    val standardBrands = listOf(
        "OnePlus", "Xiaomi", "Redmi", "Vivo", "Oppo", "Realme",
        "Huawei", "Honor", "Infinix", "Tecno", "Sony"
    )
    for (sb in standardBrands) {
        if (b.equals(sb, ignoreCase = true)) {
            if (m.startsWith("$sb ", ignoreCase = true)) {
                m = m.substring(sb.length).trim()
            }
        }
    }

    return m
}

fun formatDeviceDisplayName(brand: String, model: String): String {
    val b = brand.trim()
    val m = cleanModelForBrand(b, model).trim()
    if (b.isBlank()) return m.ifBlank { "Phone" }
    if (m.isBlank()) return b

    // 1. Apple / iPhone: If model has "iPhone" or "iPad", display model directly (e.g. "iPhone 13 Pro Max")
    if (isAppleBrand(b)) {
        return if (m.startsWith("iPhone", ignoreCase = true) || m.startsWith("iPad", ignoreCase = true)) {
            m
        } else {
            "iPhone $m"
        }
    }

    // 2. Google / Google Pixel: If brand is Google and model is "Pixel 8", show "Google Pixel 8"
    if (b.equals("Google", ignoreCase = true) || b.equals("Google Pixel", ignoreCase = true) || b.equals("Pixel", ignoreCase = true)) {
        return if (m.startsWith("Google", ignoreCase = true)) {
            m
        } else if (m.startsWith("Pixel", ignoreCase = true)) {
            "Google $m"
        } else {
            "Google Pixel $m"
        }
    }

    // 3. Samsung: If model starts with Samsung, display as is, otherwise prefix with Samsung
    if (b.equals("Samsung", ignoreCase = true)) {
        return if (m.startsWith("Samsung", ignoreCase = true)) {
            m
        } else {
            "Samsung $m"
        }
    }

    // Generic: If model already starts with brand name (e.g. "OnePlus 12"), avoid duplication
    if (m.startsWith(b, ignoreCase = true)) return m

    return "$b $m"
}

fun getStorageOptionsForBrand(brand: String): List<String> {
    return if (isAppleBrand(brand)) APPLE_STORAGE_OPTIONS else DEFAULT_STORAGE_OPTIONS
}

fun getDefaultStorageForBrand(brand: String): String {
    return if (isAppleBrand(brand)) "128GB" else "12-128GB"
}

val VARIANT_OPTIONS = listOf(
    "Global",
    "Chinese",
    "USA",
    "Hongkong",
    "Europe",
    "Japan",
    "Singapore",
    "Australia",
    "Korea",
    "Custom..."
)

val PHONE_GRADE_OPTIONS = listOf(
    "Grade A+",
    "Grade A",
    "Grade A-",
    "Grade B",
    "Grade C",
    "Custom..."
)

val ITEM_CONDITION_OPTIONS = listOf(
    "Used",
    "Intact",
    "Brand New",
    "Inactive",
    "Custom..."
)

val PHONE_CONDITION_STATUS_OPTIONS = ITEM_CONDITION_OPTIONS

val PACKAGING_OPTIONS = listOf(
    "Full Box",
    "With Box",
    "Only Set",
    "Set & Charger",
    "Set & Cover",
    "Custom..."
)

val BATTERY_HEALTH_OPTIONS = listOf("Good") + (100 downTo 50).map { "$it%" } + listOf("Custom...")

val PAYMENT_METHODS_EN = listOf(
    "Cash",
    "bKash",
    "Nagad",
    "Rocket",
    "Bank Transfer",
    "Card Payment",
    "Custom..."
)

val PAYMENT_METHODS = PAYMENT_METHODS_EN

val EXPENSE_CATEGORIES_EN = listOf(
    "Shop Rent",
    "Service Charge",
    "Electricity Bill",
    "Internet Bill",
    "Shop Cleaner",
    "Courier Bill",
    "Salary",
    "Commission",
    "Guest",
    "Parts & Repairs",
    "Accessories",
    "Brac Bank Deposit",
    "City Bank Deposit",
    "UCB Bank Deposit",
    "Custom..."
)

val EXPENSE_CATEGORIES = EXPENSE_CATEGORIES_EN
