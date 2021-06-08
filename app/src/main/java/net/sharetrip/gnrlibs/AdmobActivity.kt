package net.sharetrip.gnrlibs

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdView
import net.sharetrip.gnrlibs.databinding.ActivityAdmobBinding

class AdmobActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bindingView = ActivityAdmobBinding.inflate(layoutInflater)
        setContentView(bindingView.root)

        val adRequest = AdRequest.Builder().build()
        bindingView.adViewSmartBanner.loadAd(adRequest)
        bindingView.adViewBanner.loadAd(adRequest)
        bindingView.adViewFullBanner.loadAd(adRequest)
        bindingView.adViewLargeBanner.loadAd(adRequest)
        bindingView.adViewMediumBanner.loadAd(adRequest)

        val builder = AdLoader.Builder(this, "ca-app-pub-3940256099942544/2247696110")
            .forNativeAd { nativeAd ->
                // Assumes that your ad layout is in a file call native_ad_layout.xml
                // in the res/layout folder
                val adView = layoutInflater
                    .inflate(R.layout.native_ad_layout, null) as NativeAdView
                // This method sets the text, images and the native ad, etc into the ad
                // view.
                populateNativeAdView(nativeAd, adView)
                // Assumes you have a placeholder FrameLayout in your View layout
                // (with id ad_frame) where the ad is to be placed.
                bindingView.adFrame.removeAllViews()
                bindingView.adFrame.addView(adView)
            }
    }

    private fun populateNativeAdView(nativeAd: NativeAd, adView: NativeAdView) {
        val headlineView = adView.findViewById<TextView>(R.id.ad_headline)
        headlineView.text = nativeAd.headline
    }
}