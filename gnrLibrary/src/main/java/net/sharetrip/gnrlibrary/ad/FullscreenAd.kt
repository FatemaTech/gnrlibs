package net.sharetrip.gnrlibrary.ad

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.fragment.app.DialogFragment
import net.sharetrip.gnrads.listener.AdEventListener
import net.sharetrip.gnrlibrary.R
import net.sharetrip.gnrlibrary.databinding.FragmentFullscreenAdBinding

class FullscreenAd : DialogFragment() {
    var eventListener: AdEventListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, android.R.style.Theme_Material_Light_NoActionBar_Fullscreen)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val bindingView = FragmentFullscreenAdBinding.inflate(inflater, container, false)

        loadAd(bindingView.webView)

        bindingView.tvClose.setOnClickListener {
            dismiss()
        }
        return bindingView.root
    }


    private fun loadAd(webView: WebView) {
        val webSettings: WebSettings = webView.settings
        webSettings.javaScriptEnabled = true
        webSettings.domStorageEnabled = true
        webSettings.loadWithOverviewMode = true
        webSettings.useWideViewPort = true

        webView.setInitialScale(1)
        Handler(Looper.getMainLooper()).postDelayed(
            {
                val webViewData = AdRequestHandler.script
                val a = getString(R.string.ad_script, webViewData)

                webView.loadDataWithBaseURL(
                    null,
                    a,
                    "text/html",
                    "utf-8",
                    null
                )
            },
            2000 // value in milliseconds
        )
    }

    fun setAdEventListener(adEventListener: AdEventListener) {
        eventListener = adEventListener
    }

}