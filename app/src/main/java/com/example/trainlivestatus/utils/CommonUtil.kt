package com.example.trainlivestatus.utils

import android.content.Context
import java.io.IOException

class CommonUtil {

    companion object {

        const val isClick = false
        const val isMoreApps = false
        const val moreAppPackageName = ""
        const val easy = "easy"
        const val medium = "medium"
        const val hard = "hard"
        const val OK = "ok"
        const val TYPE = "type"
        const val CLICK = "Click"
        const val MAIN_BASE_URL = "https://api.confirmtkt.com/"
        const val BASE_URL = "http://www.hgapis.com/railapi/v1/"
        const val FIND_TRAIN_NUMBER="https://www.ixigo.com/"
        const val BASE_URL_FARE = "https://securedapi.confirmtkt.com"
        const val EMAIL = "4c266f54-988a-477d-bd6c-4981c124a80a"
        const val api_key = "191c93ed6e28945e7267e84db1031314"
        const val app_version = "1.0"
        const val SEARCH_STATION_BASE_URL = "https://www.ixigo.com/action/"
        const val tokan = "AE07DF23B773F913C5470EC3454568B641391A532B93A77BED83FB1329DF244F"
        const val appVersion = "315"
        const val PAYTM = "paytm"
        const val PAYPAL = "paypal"
        const val PAGE = "page"
        const val BROADCAST = "broadcast"
        const val fromStnCode = "fromStnCode"
        const val destStnCode = "destStnCode"
        const val quota = "GN"
        const val locale = "en"
        const val API_URL1 = "https://gosmarttrip.com/"
        const val API_URL2 = "https://enquiry.indianrail.gov.in"
        var errormessage="errormessage"



        fun getTrainList(context: Context): String? {

            val json: String?

            try {
                val `is` = context.assets.open("trains.json")
                var size = 0
                size = `is`.available()
                val buffer = ByteArray(size)
                `is`.read(buffer)
                `is`.close()
                json = String(buffer, Charsets.UTF_8)
            } catch (e: IOException) {
                e.printStackTrace()
                return null
            }

            return json

        }

    }

}