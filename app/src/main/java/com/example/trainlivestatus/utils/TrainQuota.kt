package com.example.trainlivestatus.utils

import java.util.*

class TrainQuota {

    companion object {

        private const val SECOND_MILLIS = 1000
        private const val MINUTE_MILLIS = 60 * SECOND_MILLIS
        private const val HOUR_MILLIS = 60 * MINUTE_MILLIS
        private const val DAY_MILLIS = 24 * HOUR_MILLIS

        var categoriesListName = arrayOf(
            "General Quota",
            "Ladies Quota",
            "Head quarters/high official Quota",
            "Defence Quota",
            "Parliament house Quota",
            "Foreign Tourist Quota",
            "Duty Pass Quota",
            "Tatkal Quota",
            "Premium Tatkal Quota",
            "Female(above 45 Year)/Senior Citizen/Travelling alone",
            "Physically Handicapped Quota",
            "Railway Employee Staff on Duty for the trains",
            "General Quota Road Side",
            "Out Station",
            "Pooled Quota",
            "Reservation Against Cancellation",
            "Road Side",
            "Yuva",
            "Lower Berth"
        )

        val qutosName = arrayOf(
            "GN",
            "LD",
            "HO",
            "DF",
            "PH",
            "FT",
            "DP",
            "tq",
            "PT",
            "SS",
            "HP",
            "RE",
            "GNRS",
            "OS",
            "PQ",
            "RC",
            "YU",
            "Yuva",
            "LB"
        )


        @JvmName("getQutosName1")
        fun getQutosName(): Array<String> {
            return categoriesListName
        }

        fun getQutoscode(i: Int): String {
            return qutosName[i]
        }


        fun getCountry(): String {
            val locale = Locale.getDefault()
            val country = locale.country.toString()
            return country.lowercase(Locale.getDefault())
        }

        fun getLanguage(): String {
            val locale = Locale.getDefault()
            val country = locale.language.toString()
            return country.lowercase(Locale.getDefault())
        }

    }
}