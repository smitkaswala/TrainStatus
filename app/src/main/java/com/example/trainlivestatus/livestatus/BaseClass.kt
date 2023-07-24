package com.example.trainlivestatus.livestatus

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.trainlivestatus.BuildConfig
import com.example.trainlivestatus.R
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

open class BaseClass : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    protected fun setFragment(containerId: FrameLayout, fragment: Fragment?) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(containerId.id, fragment!!)
        fragmentTransaction.commit()
    }

    fun openActivity(context: Context?, aClass: Class<*>?) {
        startActivity(
            Intent(
                context,
                aClass
            ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        )
    }

    fun moveNextActivity(context: Context?, aClass: Class<*>?) {
        startActivity(Intent(context, aClass))
    }

    fun getclient(url: String): Retrofit {

        val client = OkHttpClient().newBuilder()
            .connectTimeout(100, TimeUnit.SECONDS)
            .readTimeout(100, TimeUnit.SECONDS).build();
        return Retrofit.Builder().baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    fun TextInputEditText.getQueryTextChangeStateFlow(): StateFlow<String> {
        val query = MutableStateFlow("")

        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // beforeTextChanged
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {


                query.value = p0.toString()
            }

            override fun afterTextChanged(p0: Editable?) {
                // afterTextChanged
            }
        })

        return query
    }

    suspend fun ProgressBar.setVisible() {
        withContext(Dispatchers.Main) {
            visibility = View.VISIBLE

        }
    }

    suspend fun ProgressBar.setInvisible(scope: (() -> Unit)? = null) {
        withContext(Dispatchers.Main) {

            visibility = View.GONE
            scope?.invoke()
        }
    }

    fun rate() {

        try {
            val uri = Uri.parse("market://details?id=$packageName")
            val goMarket = Intent(Intent.ACTION_VIEW, uri)
            startActivity(goMarket)
        } catch (e: ActivityNotFoundException) {
            val uri = Uri.parse("https://play.google.com/store/apps/details?id=$packageName")
            val goMarket = Intent(Intent.ACTION_VIEW, uri)
            startActivity(goMarket)
        }
    }
    fun shareapp(){

        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name))
        val share = "https://play.google.com/store/apps/details?id=${BuildConfig.APPLICATION_ID + "\n\n"}"
        shareIntent.putExtra(Intent.EXTRA_TEXT, share)
        startActivity(Intent.createChooser(shareIntent, "choose one"))
    }

    fun sendmail(){

        val emailIntent = Intent(
            Intent.ACTION_SENDTO,
            Uri.parse("mailto:" + Uri.encode(resources.getString(R.string.mail_id)))
        )
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Feedback")
        try {
            startActivity(Intent.createChooser(emailIntent, "Send email via..."))
        } catch (ex: ActivityNotFoundException) {
            Toast.makeText(
                applicationContext,
                "There are no email clients installed.", Toast.LENGTH_SHORT
            )
                .show()
        }
    }


}