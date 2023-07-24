package com.example.trainlivestatus.model

import com.google.gson.annotations.SerializedName

data class LiveStatusModel(

	@field:SerializedName("distanceCovered")
	val distanceCovered: Int? = null,

	@field:SerializedName("startDayDiff")
	val startDayDiff: String? = null,

	@field:SerializedName("departed")
	val departed: Boolean? = null,

	@field:SerializedName("curStnName")
	val curStnName: String? = null,

	@field:SerializedName("totalJourney")
	val totalJourney: Any? = null,

	@field:SerializedName("isRunningDataAvailable")
	val isRunningDataAvailable: Boolean? = null,

	@field:SerializedName("Error")
	val error: Any? = null,

	@field:SerializedName("trainNo")
	val trainNo: String? = null,

	@field:SerializedName("CancelledRoutes")
	val cancelledRoutes: List<Any?>? = null,

	@field:SerializedName("stations")
	val stations: List<StationsItem?>? = null,

	@field:SerializedName("cncldFrmStn")
	val cncldFrmStn: Any? = null,

	@field:SerializedName("idMsg")
	val idMsg: Any? = null,

	@field:SerializedName("lastUpdated")
	val lastUpdated: String? = null,

	@field:SerializedName("HasPantry")
	val hasPantry: Boolean? = null,

	@field:SerializedName("trainName")
	val trainName: String? = null,

	@field:SerializedName("curStn")
	val curStn: String? = null,

	@field:SerializedName("cncldToStn")
	val cncldToStn: Any? = null,

	@field:SerializedName("trainDataFound")
	val trainDataFound: String? = null,

	@field:SerializedName("CacheTime")
	val cacheTime: String? = null,

	@field:SerializedName("startDate")
	val startDate: String? = null,

	@field:SerializedName("terminated")
	val terminated: Boolean? = null,

	@field:SerializedName("totalLateMins")
	val totalLateMins: Int? = null,

	@field:SerializedName("DivertedRoutes")
	val divertedRoutes: List<Any?>? = null
)

data class StationsItem(

	@field:SerializedName("actArr")
	val actArr: String? = null,

	@field:SerializedName("stoppingStn")
	val stoppingStn: Boolean? = null,

	@field:SerializedName("distance")
	val distance: Int? = null,

	@field:SerializedName("schDepTime")
	val schDepTime: String? = null,

	@field:SerializedName("actArrDate")
	val actArrDate: String? = null,

	@field:SerializedName("haltMinutes")
	val haltMinutes: Int? = null,

	@field:SerializedName("updWaitngArr")
	val updWaitngArr: Boolean? = null,

	@field:SerializedName("schArrTime")
	val schArrTime: String? = null,

	@field:SerializedName("dep")
	val dep: Boolean? = null,

	@field:SerializedName("dvrtdStn")
	val dvrtdStn: Boolean? = null,

	@field:SerializedName("travelled")
	val travelled: Boolean? = null,

	@field:SerializedName("schDayCnt")
	val schDayCnt: Int? = null,

	@field:SerializedName("dayCnt")
	val dayCnt: Int? = null,

	@field:SerializedName("arr")
	val arr: Boolean? = null,

	@field:SerializedName("stnCodeName")
	val stnCodeName: String? = null,

	@field:SerializedName("pfNo")
	val pfNo: Int? = null,

	@field:SerializedName("actDepDate1")
	val actDepDate1: String? = null,

	@field:SerializedName("delayDep")
	val delayDep: Int,

	@field:SerializedName("journeyDate")
	val journeyDate: String? = null,

	@field:SerializedName("HasWifi")
	val hasWifi: Boolean? = null,

	@field:SerializedName("actArrDate1")
	val actArrDate1: String? = null,

	@field:SerializedName("actDep")
	val actDep: String? = null,

	@field:SerializedName("actDepDate")
	val actDepDate: String? = null,

	@field:SerializedName("ExpectedPlatformNo")
	val expectedPlatformNo: String? = null,

	@field:SerializedName("stnCode")
	val stnCode: String? = null,

	@field:SerializedName("updWaitngDep")
	val updWaitngDep: Boolean? = null,

	@field:SerializedName("delayArr")
	val delayArr: Int? = null,

	@field:SerializedName("dayDiff")
	val dayDiff: String? = null
)
