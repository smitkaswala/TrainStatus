package com.example.trainlivestatus.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.trainlivestatus.R
import com.example.trainlivestatus.application.TrainPays

class SharedPref {

    companion object {

        private val sharedPrefenceName =
            TrainPays.getContext()?.resources?.getString(R.string.app_name)

        private fun getSharedPref(): SharedPreferences? {
            return TrainPays.getContext()
                ?.getSharedPreferences(sharedPrefenceName, Context.MODE_PRIVATE)
        }

        var user_id = "user_id"
        var user_phone_number = "user_phone_number"
        var user_referral_code = "user_referral_code"
        var user_device_id = "user_device_id"
        var user_imei_number = "user_imei_number"
        var user_status = "user_status"
        var user_create_date = "user_create_date"
        var total_points = "total_points"
        var day_count = "day_count"
        var is_date = "is_date"
        var package_name = "package_name"
        var facebook_url = "facebook_url"
        var twitter_url = "twitter_url"
        var instagram_url = "instagram_url"
        var youtube_url = "youtube_url"
        var telegram_url = "telegram_url"
        var whatsapp_number = "whatsapp_number"
        var wifi = "wifi"
        var seatAvaTo = "seatAvaTo"
        var seatAvaFrom = "seatAvaFrom"
        var seatAvaDate = "seatAvaDate"
        var scheduledTo = "scheduledTo"
        var scheduledFrom = "scheduledFrom"
        var scheduledDate = "scheduledDate"
        var json = "json"
        var json1 = "json1"
        var city_from_st = "city_from_st"
        var city_to_st = "city_to_st"


        fun putString(key: String?, value: String?) {
            getSharedPref()?.edit()?.putString(key, value)?.apply()
        }

        fun getString(key: String?): String? {
            return getSharedPref()?.getString(key, "")
        }

        fun putInteger(key: String?, value: Int) {
            getSharedPref()?.edit()?.putInt(key, value)?.apply()
        }

        fun getInteger(key: String?): Int? {
            return getSharedPref()?.getInt(key, 0)
        }

        fun removeKey(key: String?) {
            getSharedPref()?.edit()?.remove(key)?.apply()
        }

        fun setPoints(value: Int) {
            getSharedPref()?.edit()?.putInt(total_points, value)?.apply()
        }

        fun getPoints(): Int? {
            return getSharedPref()?.getInt(total_points, 0)
        }

        fun screensave(key: String?, value: Boolean) {

            getSharedPref()?.edit()?.putBoolean(key, value)?.apply()
        }

        fun getsacrenn(key: String?): Boolean {
            return getSharedPref()!!.getBoolean(key, false)
        }

        fun getAdsTime(key: String?): Long? {
            return getSharedPref()?.getLong(wifi, 0)
        }

        fun saveAdsTime(key: String?, time: Long) {
            getSharedPref()?.edit()?.putLong(key, time)?.apply()
        }
    }


}