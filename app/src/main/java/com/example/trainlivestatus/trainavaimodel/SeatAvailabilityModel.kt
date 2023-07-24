package com.example.trainlivestatus.trainavaimodel

import com.google.gson.annotations.SerializedName

data class SeatAvailabilityModel(

    @field:SerializedName("1A")
    val jsonMember1A: JsonMember1A? = null,

    @field:SerializedName("2S")
    val jsonMember2S: JsonMember2S? = null,

    @field:SerializedName("SL")
    val sL: SL? = null,

    @field:SerializedName("3A")
    val jsonMember3A: JsonMember3A? = null,

    @field:SerializedName("2A")
    val jsonMember2A: JsonMember2A? = null

)

data class JsonMember1A(

    @field:SerializedName("Destination")
    val destination: String? = null,

    @field:SerializedName("TravelClass")
    val travelClass: String? = null,

    @field:SerializedName("CurrentStatus")
    val currentStatus: String? = null,

    @field:SerializedName("Prediction")
    val prediction: String? = null,

    @field:SerializedName("Error")
    val error: Any? = null,

    @field:SerializedName("ConfirmTktStatus")
    val confirmTktStatus: String? = null,

    @field:SerializedName("alternates")
    val alternates: Any? = null,

    @field:SerializedName("Source")
    val source: String? = null,

    @field:SerializedName("IsRac")
    val isRac: Boolean? = null,

    @field:SerializedName("TrainNo")
    val trainNo: Int? = null,

    @field:SerializedName("BookingUrl")
    val bookingUrl: Any? = null,

    @field:SerializedName("PredictionPercentage")
    val predictionPercentage: Any? = null,

    @field:SerializedName("PredictionStrings")
    val predictionStrings: Any? = null,

    @field:SerializedName("CacheTime")
    val cacheTime: String? = null,

    @field:SerializedName("Doj")
    val doj: String? = null
)

data class JsonMember2S(

    @field:SerializedName("Destination")
    val destination: String? = null,

    @field:SerializedName("TravelClass")
    val travelClass: String? = null,

    @field:SerializedName("CurrentStatus")
    val currentStatus: String? = null,

    @field:SerializedName("Prediction")
    val prediction: String? = null,

    @field:SerializedName("Error")
    val error: Any? = null,

    @field:SerializedName("ConfirmTktStatus")
    val confirmTktStatus: String? = null,

    @field:SerializedName("alternates")
    val alternates: Any? = null,

    @field:SerializedName("Source")
    val source: String? = null,

    @field:SerializedName("IsRac")
    val isRac: Boolean? = null,

    @field:SerializedName("TrainNo")
    val trainNo: Int? = null,

    @field:SerializedName("BookingUrl")
    val bookingUrl: Any? = null,

    @field:SerializedName("PredictionPercentage")
    val predictionPercentage: Any? = null,

    @field:SerializedName("PredictionStrings")
    val predictionStrings: Any? = null,

    @field:SerializedName("CacheTime")
    val cacheTime: String? = null,

    @field:SerializedName("Doj")
    val doj: String? = null
)


data class JsonMember2A(

    @field:SerializedName("Destination")
    val destination: String? = null,

    @field:SerializedName("TravelClass")
    val travelClass: String? = null,

    @field:SerializedName("CurrentStatus")
    val currentStatus: String? = null,

    @field:SerializedName("Prediction")
    val prediction: String? = null,

    @field:SerializedName("Error")
    val error: Any? = null,

    @field:SerializedName("ConfirmTktStatus")
    val confirmTktStatus: String? = null,

    @field:SerializedName("alternates")
    val alternates: Any? = null,

    @field:SerializedName("Source")
    val source: String? = null,

    @field:SerializedName("IsRac")
    val isRac: Boolean? = null,

    @field:SerializedName("TrainNo")
    val trainNo: Int? = null,

    @field:SerializedName("BookingUrl")
    val bookingUrl: Any? = null,

    @field:SerializedName("PredictionPercentage")
    val predictionPercentage: Any? = null,

    @field:SerializedName("PredictionStrings")
    val predictionStrings: Any? = null,

    @field:SerializedName("CacheTime")
    val cacheTime: String? = null,

    @field:SerializedName("Doj")
    val doj: String? = null
)

data class JsonMember3A(

    @field:SerializedName("Destination")
    val destination: String? = null,

    @field:SerializedName("TravelClass")
    val travelClass: String? = null,

    @field:SerializedName("CurrentStatus")
    val currentStatus: String? = null,

    @field:SerializedName("Prediction")
    val prediction: String? = null,

    @field:SerializedName("Error")
    val error: Any? = null,

    @field:SerializedName("ConfirmTktStatus")
    val confirmTktStatus: String? = null,

    @field:SerializedName("alternates")
    val alternates: Any? = null,

    @field:SerializedName("Source")
    val source: String? = null,

    @field:SerializedName("IsRac")
    val isRac: Boolean? = null,

    @field:SerializedName("TrainNo")
    val trainNo: Int? = null,

    @field:SerializedName("BookingUrl")
    val bookingUrl: Any? = null,

    @field:SerializedName("PredictionPercentage")
    val predictionPercentage: Any? = null,

    @field:SerializedName("PredictionStrings")
    val predictionStrings: Any? = null,

    @field:SerializedName("CacheTime")
    val cacheTime: String? = null,

    @field:SerializedName("Doj")
    val doj: String? = null
)

data class SL(

    @field:SerializedName("Destination")
    val destination: String? = null,

    @field:SerializedName("TravelClass")
    val travelClass: String? = null,

    @field:SerializedName("CurrentStatus")
    val currentStatus: String? = null,

    @field:SerializedName("Prediction")
    val prediction: String? = null,

    @field:SerializedName("Error")
    val error: Any? = null,

    @field:SerializedName("ConfirmTktStatus")
    val confirmTktStatus: String? = null,

    @field:SerializedName("alternates")
    val alternates: Any? = null,

    @field:SerializedName("Source")
    val source: String? = null,

    @field:SerializedName("IsRac")
    val isRac: Boolean? = null,

    @field:SerializedName("TrainNo")
    val trainNo: Int? = null,

    @field:SerializedName("BookingUrl")
    val bookingUrl: Any? = null,

    @field:SerializedName("PredictionPercentage")
    val predictionPercentage: Any? = null,

    @field:SerializedName("PredictionStrings")
    val predictionStrings: Any? = null,

    @field:SerializedName("CacheTime")
    val cacheTime: String? = null,

    @field:SerializedName("Doj")
    val doj: String? = null
)
