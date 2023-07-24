package com.example.trainlivestatus.model

import com.google.gson.annotations.SerializedName

data class RouteStationModel(

	@field:SerializedName("fromCache")
	val fromCache: Int? = null,

	@field:SerializedName("trainBtwnStnsList")
	val trainBtwnStnsList: List<TrainBtwnStnsListItem?>? = null,

	@field:SerializedName("DtWinOpens")
	val dtWinOpens: String? = null,

	@field:SerializedName("errorMessage")
	val errorMessage: Any? = null,

	@field:SerializedName("quotaList")
	val quotaList: List<String?>? = null,

	@field:SerializedName("errorCode")
	val errorCode: Int? = null,

	@field:SerializedName("serverId")
	val serverId: String? = null,

	@field:SerializedName("vikalpInSpecialTrainsAccomFlag")
	val vikalpInSpecialTrainsAccomFlag: Boolean? = null,

	@field:SerializedName("timeStamp")
	val timeStamp: String? = null,

	@field:SerializedName("maxNoOfDays")
	val maxNoOfDays: Int? = null,

	@field:SerializedName("maxNoOfVikalpTrains")
	val maxNoOfVikalpTrains: Int? = null,

	@field:SerializedName("nearbyAlternates")
	val nearbyAlternates: Any? = null,

	@field:SerializedName("arpDays")
	val arpDays: Int? = null
)

data class JsonMember2A(

	@field:SerializedName("Availability")
	val availability: String? = null,

	@field:SerializedName("Destination")
	val destination: String? = null,

	@field:SerializedName("Quota")
	val quota: String? = null,

	@field:SerializedName("TrainNo")
	val trainNo: String? = null,

	@field:SerializedName("TravelClass")
	val travelClass: String? = null,

	@field:SerializedName("Prediction")
	val prediction: String? = null,

	@field:SerializedName("Id")
	val id: Int? = null,

	@field:SerializedName("CacheTime")
	val cacheTime: String? = null,

	@field:SerializedName("ConfirmTktStatus")
	val confirmTktStatus: String? = null,

	@field:SerializedName("Date")
	val date: String? = null,

	@field:SerializedName("Source")
	val source: String? = null,

	@field:SerializedName("Fare")
	val fare: String? = null
)

data class AvlClasses(

	@field:SerializedName("Array")
	val array: List<String?>? = null
)

data class AvaiblitycacheTq(

	@field:SerializedName("2S")
	val jsonMember2S: JsonMember2S? = null,

	@field:SerializedName("2A")
	val jsonMember2A: JsonMember2A? = null,

	@field:SerializedName("1A")
	val jsonMember1A: JsonMember1A? = null,

	@field:SerializedName("SL")
	val sL: SL? = null,

	@field:SerializedName("3A")
	val jsonMember3A: JsonMember3A? = null,

	@field:SerializedName("CC")
	val cC: CC? = null,

	@field:SerializedName("EA")
	val eA: EA? = null,

	@field:SerializedName("EC")
	val eC: EC? = null,

	@field:SerializedName("3E")
	val jsonMember3E: JsonMember3E? = null
)

data class JsonMember3A(

	@field:SerializedName("Availability")
	val availability: String? = null,

	@field:SerializedName("Destination")
	val destination: String? = null,

	@field:SerializedName("Quota")
	val quota: String? = null,

	@field:SerializedName("TrainNo")
	val trainNo: String? = null,

	@field:SerializedName("TravelClass")
	val travelClass: String? = null,

	@field:SerializedName("Prediction")
	val prediction: String? = null,

	@field:SerializedName("Id")
	val id: Int? = null,

	@field:SerializedName("CacheTime")
	val cacheTime: String? = null,

	@field:SerializedName("ConfirmTktStatus")
	val confirmTktStatus: String? = null,

	@field:SerializedName("Date")
	val date: String? = null,

	@field:SerializedName("Source")
	val source: String? = null,

	@field:SerializedName("Fare")
	val fare: String? = null
)

data class EC(

	@field:SerializedName("Availability")
	val availability: String? = null,

	@field:SerializedName("Destination")
	val destination: String? = null,

	@field:SerializedName("Quota")
	val quota: String? = null,

	@field:SerializedName("TrainNo")
	val trainNo: String? = null,

	@field:SerializedName("TravelClass")
	val travelClass: String? = null,

	@field:SerializedName("Prediction")
	val prediction: String? = null,

	@field:SerializedName("Id")
	val id: Int? = null,

	@field:SerializedName("CacheTime")
	val cacheTime: String? = null,

	@field:SerializedName("ConfirmTktStatus")
	val confirmTktStatus: String? = null,

	@field:SerializedName("Date")
	val date: String? = null,

	@field:SerializedName("Source")
	val source: String? = null,

	@field:SerializedName("Fare")
	val fare: String? = null
)

data class JsonMember1A(

	@field:SerializedName("Availability")
	val availability: String? = null,

	@field:SerializedName("Destination")
	val destination: String? = null,

	@field:SerializedName("Quota")
	val quota: String? = null,

	@field:SerializedName("TrainNo")
	val trainNo: String? = null,

	@field:SerializedName("TravelClass")
	val travelClass: String? = null,

	@field:SerializedName("Prediction")
	val prediction: String? = null,

	@field:SerializedName("Id")
	val id: Int? = null,

	@field:SerializedName("CacheTime")
	val cacheTime: String? = null,

	@field:SerializedName("ConfirmTktStatus")
	val confirmTktStatus: String? = null,

	@field:SerializedName("Date")
	val date: String? = null,

	@field:SerializedName("Source")
	val source: String? = null,

	@field:SerializedName("Fare")
	val fare: String? = null
)

data class JsonMember3E(

	@field:SerializedName("Availability")
	val availability: String? = null,

	@field:SerializedName("Destination")
	val destination: String? = null,

	@field:SerializedName("Quota")
	val quota: String? = null,

	@field:SerializedName("TrainNo")
	val trainNo: String? = null,

	@field:SerializedName("TravelClass")
	val travelClass: String? = null,

	@field:SerializedName("Prediction")
	val prediction: String? = null,

	@field:SerializedName("Id")
	val id: Int? = null,

	@field:SerializedName("CacheTime")
	val cacheTime: String? = null,

	@field:SerializedName("ConfirmTktStatus")
	val confirmTktStatus: String? = null,

	@field:SerializedName("Date")
	val date: String? = null,

	@field:SerializedName("Source")
	val source: String? = null,

	@field:SerializedName("Fare")
	val fare: String? = null
)

data class SL(

	@field:SerializedName("Availability")
	val availability: String? = null,

	@field:SerializedName("Destination")
	val destination: String? = null,

	@field:SerializedName("Quota")
	val quota: String? = null,

	@field:SerializedName("TrainNo")
	val trainNo: String? = null,

	@field:SerializedName("TravelClass")
	val travelClass: String? = null,

	@field:SerializedName("Prediction")
	val prediction: String? = null,

	@field:SerializedName("Id")
	val id: Int? = null,

	@field:SerializedName("CacheTime")
	val cacheTime: String? = null,

	@field:SerializedName("ConfirmTktStatus")
	val confirmTktStatus: String? = null,

	@field:SerializedName("Date")
	val date: String? = null,

	@field:SerializedName("Source")
	val source: String? = null,

	@field:SerializedName("Fare")
	val fare: String? = null
)

data class TrainBtwnStnsListItem(

	@field:SerializedName("departureTime")
	val departureTime: String? = null,

	@field:SerializedName("runningWed")
	val runningWed: String? = null,

	@field:SerializedName("distance")
	val distance: Int? = null,

	@field:SerializedName("trainType")
	val trainType: List<String?>? = null,

	@field:SerializedName("runningFri")
	val runningFri: String? = null,

	@field:SerializedName("duration")
	val duration: String? = null,

	@field:SerializedName("runningTue")
	val runningTue: String? = null,

	@field:SerializedName("IsTejas")
	val isTejas: Boolean? = null,

	@field:SerializedName("atasOpted")
	val atasOpted: Boolean? = null,

	@field:SerializedName("HasPantry")
	val hasPantry: Boolean? = null,

	@field:SerializedName("fromStnCode")
	val fromStnCode: String? = null,

	@field:SerializedName("toStnCode")
	val toStnCode: String? = null,

	@field:SerializedName("sNo")
	val sNo: Int? = null,

	@field:SerializedName("arrivalTime")
	val arrivalTime: String? = null,

	@field:SerializedName("runningThu")
	val runningThu: String? = null,

	@field:SerializedName("trainName")
	val trainName: String? = null,

	@field:SerializedName("runningMon")
	val runningMon: String? = null,

	@field:SerializedName("runningSun")
	val runningSun: String? = null,

	@field:SerializedName("flexiFlag")
	val flexiFlag: Boolean? = null,

	@field:SerializedName("departureDate")
	val departureDate: Any? = null,

	@field:SerializedName("noOfDaysRun")
	val noOfDaysRun: Int? = null,

	@field:SerializedName("fromStnName")
	val fromStnName: Any? = null,

	@field:SerializedName("avaiblitycache")
	val avaiblitycache: Avaiblitycache? = null,

	@field:SerializedName("toStnName")
	val toStnName: Any? = null,

	@field:SerializedName("runningSat")
	val runningSat: String? = null,

	@field:SerializedName("avaiblitycacheTq")
	val avaiblitycacheTq: AvaiblitycacheTq? = null,

	@field:SerializedName("avlClasses")
	val avlClasses: AvlClasses? = null,

	@field:SerializedName("trainNumber")
	val trainNumber: String? = null
)

data class EA(

	@field:SerializedName("Availability")
	val availability: String? = null,

	@field:SerializedName("Destination")
	val destination: String? = null,

	@field:SerializedName("Quota")
	val quota: String? = null,

	@field:SerializedName("TrainNo")
	val trainNo: String? = null,

	@field:SerializedName("TravelClass")
	val travelClass: String? = null,

	@field:SerializedName("Prediction")
	val prediction: String? = null,

	@field:SerializedName("Id")
	val id: Int? = null,

	@field:SerializedName("CacheTime")
	val cacheTime: String? = null,

	@field:SerializedName("ConfirmTktStatus")
	val confirmTktStatus: String? = null,

	@field:SerializedName("Date")
	val date: String? = null,

	@field:SerializedName("Source")
	val source: String? = null,

	@field:SerializedName("Fare")
	val fare: String? = null
)

data class Avaiblitycache(

	@field:SerializedName("2S")
	val jsonMember2S: JsonMember2S? = null,

	@field:SerializedName("SL")
	val sL: SL? = null,

	@field:SerializedName("3A")
	val jsonMember3A: JsonMember3A? = null,

	@field:SerializedName("2A")
	val jsonMember2A: JsonMember2A? = null,

	@field:SerializedName("1A")
	val jsonMember1A: JsonMember1A? = null,

	@field:SerializedName("3E")
	val jsonMember3E: JsonMember3E? = null,

	@field:SerializedName("CC")
	val cC: CC? = null,

	@field:SerializedName("EA")
	val eA: EA? = null,

	@field:SerializedName("EC")
	val eC: EC? = null
)

data class CC(

	@field:SerializedName("Availability")
	val availability: String? = null,

	@field:SerializedName("Destination")
	val destination: String? = null,

	@field:SerializedName("Quota")
	val quota: String? = null,

	@field:SerializedName("TrainNo")
	val trainNo: String? = null,

	@field:SerializedName("TravelClass")
	val travelClass: String? = null,

	@field:SerializedName("Prediction")
	val prediction: String? = null,

	@field:SerializedName("Id")
	val id: Int? = null,

	@field:SerializedName("CacheTime")
	val cacheTime: String? = null,

	@field:SerializedName("ConfirmTktStatus")
	val confirmTktStatus: String? = null,

	@field:SerializedName("Date")
	val date: String? = null,

	@field:SerializedName("Source")
	val source: String? = null,

	@field:SerializedName("Fare")
	val fare: String? = null
)

data class JsonMember2S(

	@field:SerializedName("Availability")
	val availability: String? = null,

	@field:SerializedName("Destination")
	val destination: String? = null,

	@field:SerializedName("Quota")
	val quota: String? = null,

	@field:SerializedName("TrainNo")
	val trainNo: String? = null,

	@field:SerializedName("TravelClass")
	val travelClass: String? = null,

	@field:SerializedName("Prediction")
	val prediction: String? = null,

	@field:SerializedName("Id")
	val id: Int? = null,

	@field:SerializedName("CacheTime")
	val cacheTime: String? = null,

	@field:SerializedName("ConfirmTktStatus")
	val confirmTktStatus: String? = null,

	@field:SerializedName("Date")
	val date: String? = null,

	@field:SerializedName("Source")
	val source: String? = null,

	@field:SerializedName("Fare")
	val fare: String? = null
)
