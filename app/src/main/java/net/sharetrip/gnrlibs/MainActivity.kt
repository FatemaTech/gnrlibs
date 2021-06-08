package net.sharetrip.gnrlibs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import net.sharetrip.gnrlibrary.ad.AdRequestHandler
import net.sharetrip.gnrlibrary.ad.FullscreenAd
import net.sharetrip.gnrlibs.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bindingView = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingView.root)


           AdRequestHandler.initialize()

           FullscreenAd().show(
               supportFragmentManager, "TAG"
           )

           bindingView.bannerAdView.loadAdd()
    }
}