package net.sharetrip.gnrads.model

data class AdRequest(
    val browser: String = "Chrome",
    val browserVersion: String = "102.38.84.1",
    val cookie: String = "83LSI-IL388-FFX70-XX158",
    val displayHeight: Int = 1920,
    val displayWidth: Int = 1080,
    val isMobileBrowser: Boolean = false,
    val placeId: String = "6b2207eb-4e1b-386a-8ca3-6e29d4f39fc7",
    val platform: String = "Windows",
    val platformVersion: String = "Windows 10",
    val propertyId: String = "0e3d70b9-c527-36c1-9594-242e7b6f68c0"
)