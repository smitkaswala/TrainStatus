package com.example.trainlivestatus.livestatus

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.trainlivestatus.R
import com.example.trainlivestatus.utils.SharedPref


@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)

        val flag: Boolean = SharedPref.getsacrenn(SharedPref.user_id)

        handler.postDelayed({

            if (!flag) {

                startActivity(Intent(this@SplashActivity, IntroActivity::class.java))
                finish()

            } else {

                startActivity(Intent(this@SplashActivity, CategoryActivity::class.java))
                finish()

            }

        }, 2000)

    }
}