package net.sharetrip.gnrlibrary.ad

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.webkit.WebSettings
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import net.sharetrip.gnrlibrary.R
import net.sharetrip.gnrlibrary.databinding.BannerAdBinding


class BannerAd : LinearLayout {

    lateinit var bindingView: BannerAdBinding

    constructor(context: Context?) : super(context) {
        initUi()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        initUi()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    ) {
        initUi()
    }

    private fun initUi() {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        bindingView = DataBindingUtil.inflate(inflater, R.layout.banner_ad, this, true)

        bindingView.tvClose.setOnClickListener {
            visibility = GONE
        }
        //  loadAdd()
    }

    @SuppressLint("ClickableViewAccessibility")
    fun loadAdd() {
        val webSettings: WebSettings = bindingView.webView.settings
        webSettings.javaScriptEnabled = true
        bindingView.webView.setOnTouchListener { _, event -> event.action == MotionEvent.ACTION_MOVE }

        Handler(Looper.getMainLooper()).postDelayed(
            {
                val webViewData = AdRequestHandler.script
                val a = context.getString(R.string.ad_script, webViewData)

                bindingView.webView.loadDataWithBaseURL(
                    null,
                    a,
                    "text/html",
                    "utf-8",
                    null
                )
            },
            3000 // value in milliseconds
        )
    }

}
