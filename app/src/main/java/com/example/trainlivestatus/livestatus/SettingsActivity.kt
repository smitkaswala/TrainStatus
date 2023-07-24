package com.example.trainlivestatus.livestatus

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.trainlivestatus.BuildConfig
import com.example.trainlivestatus.R
import com.example.trainlivestatus.databinding.ActivitySettingsBinding
import com.thefinestartist.finestwebview.FinestWebView

class SettingsActivity : BaseClass() {


    lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_settings)

        binding.rvToolbar.setNavigationOnClickListener {

            onBackPressed()
        }

        binding.ivfedd.setOnClickListener {

            sendEmail()
        }

        binding.ivrate.setOnClickListener {

            rate()
        }

        binding.ivshare.setOnClickListener {

            shareapp()
        }

        binding.ivpri.setOnClickListener {

            FinestWebView.Builder(this@SettingsActivity)
                .theme(R.style.FinestWebViewTheme_Fullscreen)
                .showSwipeRefreshLayout(true)
                .webViewBuiltInZoomControls(true)
                .webViewDisplayZoomControls(true)
                .showUrl(false)
                .show("https://kotlinlang.org/")
        }
    }


    fun sendEmail() {

        sendmail()
    }
}