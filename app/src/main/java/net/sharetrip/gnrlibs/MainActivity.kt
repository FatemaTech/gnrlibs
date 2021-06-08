package net.sharetrip.gnrlibs

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import net.sharetrip.gnrlibrary.ad.AdRequestHandler
import net.sharetrip.gnrlibrary.ad.FullscreenAd
import net.sharetrip.gnrlibs.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bindingView = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingView.root)

        bindingView.buttonAdmob.setOnClickListener {
            val intent = Intent(this, AdmobActivity::class.java)
            startActivity(intent)
        }
        AdRequestHandler.initialize()

        FullscreenAd().show(
            supportFragmentManager, "TAG"
        )

        bindingView.bannerAdView.loadAdd()
    }
}