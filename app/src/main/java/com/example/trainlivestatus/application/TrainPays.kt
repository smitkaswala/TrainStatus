package com.example.trainlivestatus.application

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.example.trainlivestatus.utils.CommonUtil
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class TrainPays : Application() {

    init {

        mInstance = this
    }

    companion object {

        private var mInstance: TrainPays? = null
        private const val BASE_URL = CommonUtil.MAIN_BASE_URL

        fun getContext(): Context? {
            return mInstance
        }

        @Synchronized
        fun getInstance(): TrainPays? {
            return mInstance
        }

        fun getClient(): Retrofit {

            val client = OkHttpClient().newBuilder()
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS).build();
            return Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }

        fun isNetConnectionAvailable(): Boolean {
            return try {
                val connectivityManager =
                    getContext()?.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
                val activeNetworkInfo = connectivityManager.activeNetworkInfo
                activeNetworkInfo != null && activeNetworkInfo.isConnected
            } catch (e: Exception) {

                false
            }
        }


    }


}