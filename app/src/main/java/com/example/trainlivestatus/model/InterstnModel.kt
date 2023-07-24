package com.example.trainlivestatus.model

import com.google.gson.annotations.SerializedName

data class InterstnModel(

	@field:SerializedName("PunctualityRating")
	val punctualityRating: Double? = null,

	@field:SerializedName("Destination")
	val destination: String? = null,

	@field:SerializedName("SourceCode")
	val sourceCode: String? = null,

	@field:SerializedName("TrainName")
	val trainName: String? = null,

	@field:SerializedName("DestinationCode")
	val destinationCode: String? = null,

	@field:SerializedName("IsUnreserved")
	val isUnreserved: Boolean? = null,

	@field:SerializedName("Rating")
	val rating: Double? = null,

	@field:SerializedName("DaysOfRun")
	val daysOfRun: DaysOfRun? = null,

	@field:SerializedName("TrainNumberString")
	val trainNumberString: Any? = null,

	@field:SerializedName("Source")
	val source: String? = null,

	@field:SerializedName("TotalDuration")
	val totalDuration: String? = null,

	@field:SerializedName("HasPantry")
	val hasPantry: Boolean? = null,

	@field:SerializedName("TrainNo")
	val trainNo: Int? = null,

	@field:SerializedName("RatingCount")
	val ratingCount: Int? = null,

	@field:SerializedName("ToCity")
	val toCity: String? = null,

	@field:SerializedName("FooterText")
	val footerText: Any? = null,

	@field:SerializedName("FoodRating")
	val foodRating: Double? = null,

	@field:SerializedName("Schedule")
	val schedule: List<ScheduleItem?>? = null,

	@field:SerializedName("FromCity")
	val fromCity: Any? = null,

	@field:SerializedName("CoachPosition")
	val coachPosition: String? = null,

	@field:SerializedName("TrainType")
	val trainType: Any? = null,

	@field:SerializedName("CleanlinessRating")
	val cleanlinessRating: Double? = null,

	@field:SerializedName("CacheTime")
	val cacheTime: String? = null,

	@field:SerializedName("Classes")
	val classes: List<String?>? = null
)

data class ScheduleItem(

	@field:SerializedName("DepartureTime")
	val departureTime: String? = null,

	@field:SerializedName("HaltMinutes")
	val haltMinutes: String? = null,

	@field:SerializedName("DataChanged")
	val dataChanged: Boolean? = null,

	@field:SerializedName("RouteNo")
	val routeNo: Any? = null,

	@field:SerializedName("StopNumber")
	val stopNumber: Int? = null,

	@field:SerializedName("arrivalDelay")
	val arrivalDelay: String? = null,

	@field:SerializedName("Latitude")
	val latitude: Any? = null,

	@field:SerializedName("StationCode")
	val stationCode: String? = null,

	@field:SerializedName("HasWifi")
	val hasWifi: Boolean? = null,

	@field:SerializedName("departureDelay")
	val departureDelay: String? = null,

	@field:SerializedName("Longitude")
	val longitude: Any? = null,

	@field:SerializedName("TrainMappingKey")
	val trainMappingKey: Any? = null,

	@field:SerializedName("StationName")
	val stationName: String? = null,

	@field:SerializedName("TrainNo")
	val trainNo: Int? = null,

	@field:SerializedName("ExpectedPlatformNo")
	val expectedPlatformNo: Any? = null,

	@field:SerializedName("intermediateStations")
	val intermediateStations: List<IntermediateStationsItem?>? = null,

	@field:SerializedName("stopNumberDisplay")
	val stopNumberDisplay: Double? = null,

	@field:SerializedName("Day")
	val day: Int? = null,

	@field:SerializedName("Distance")
	val distance: String? = null,

	@field:SerializedName("ArrivalTime")
	val arrivalTime: String? = null
)

data class DaysOfRun(

	@field:SerializedName("Thu")
	val thu: Boolean? = null,

	@field:SerializedName("Tue")
	val tue: Boolean? = null,

	@field:SerializedName("Wed")
	val wed: Boolean? = null,

	@field:SerializedName("Sat")
	val sat: Boolean? = null,

	@field:SerializedName("Fri")
	val fri: Boolean? = null,

	@field:SerializedName("Sun")
	val sun: Boolean? = null,

	@field:SerializedName("Mon")
	val mon: Boolean? = null
)

data class IntermediateStationsItem(

	@field:SerializedName("StationName")
	val stationName: String? = null,

	@field:SerializedName("DepartureTime")
	val departureTime: String? = null,

	@field:SerializedName("HaltMinutes")
	val haltMinutes: String? = null,

	@field:SerializedName("stopNumberDisplay")
	val stopNumberDisplay: String? = null,

	@field:SerializedName("Latitude")
	val latitude: Double? = null,

	@field:SerializedName("StationCode")
	val stationCode: String? = null,

	@field:SerializedName("Day")
	val day: Int? = null,

	@field:SerializedName("Longitude")
	val longitude: Double? = null,

	@field:SerializedName("Distance")
	val distance: String? = null,

	@field:SerializedName("ArrivalTime")
	val arrivalTime: String? = null
)
