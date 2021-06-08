package net.sharetrip.gnrads.listener

interface AdEventListener {
    fun onAdLoad()
    fun onAdFailedToLoad(error: String?)
    fun onAdOpened()
    fun onAdClicked()
    fun onAdSkipped()
    fun onAdClosed()
}