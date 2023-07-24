package com.example.trainlivestatus.trainavaimodel

import com.google.gson.annotations.SerializedName

data class TopCalModel(

	@field:SerializedName("Trains")
	val trains: List<TrainsItem?>? = null,

	@field:SerializedName("TrainAlternativeAdPosition")
	val trainAlternativeAdPosition: Int? = null,

	@field:SerializedName("WebsiteAds")
	val websiteAds: WebsiteAds? = null,

	@field:SerializedName("RelatedTrainsFromDestination")
	val relatedTrainsFromDestination: Any? = null,

	@field:SerializedName("RelatedTrainsFromSource")
	val relatedTrainsFromSource: Any? = null,

	@field:SerializedName("Ads")
	val ads: List<Any?>? = null,

	@field:SerializedName("ShowBlaBlaAd")
	val showBlaBlaAd: Boolean? = null,

	@field:SerializedName("WebsiteAdsNew")
	val websiteAdsNew: List<Any?>? = null,

	@field:SerializedName("FooterText")
	val footerText: Any? = null,

	@field:SerializedName("WebsiteEvents")
	val websiteEvents: List<Any?>? = null,

	@field:SerializedName("NotRunningTrains")
	val notRunningTrains: List<Any?>? = null,

	@field:SerializedName("SEOData")
	val sEOData: Any? = null,

	@field:SerializedName("TrainListAdPosition")
	val trainListAdPosition: Int? = null
)

data class WebsiteAds(

	@field:SerializedName("TR")
	val tR: TR? = null
)

data class TR(

	@field:SerializedName("HtmlCode")
	val htmlCode: String? = null,

	@field:SerializedName("CtaEnabled")
	val ctaEnabled: Boolean? = null,

	@field:SerializedName("Inapp")
	val inapp: Boolean? = null,

	@field:SerializedName("Position")
	val position: Int? = null,

	@field:SerializedName("AdUnitId")
	val adUnitId: Any? = null,

	@field:SerializedName("ImageUrl")
	val imageUrl: String? = null,

	@field:SerializedName("Text")
	val text: String? = null,

	@field:SerializedName("Text2")
	val text2: Any? = null,

	@field:SerializedName("WebsitePosition")
	val websitePosition: String? = null,

	@field:SerializedName("IsNew")
	val isNew: Boolean? = null,

	@field:SerializedName("Url")
	val url: String? = null,

	@field:SerializedName("CtaText")
	val ctaText: Any? = null,

	@field:SerializedName("CtaTitle")
	val ctaTitle: Any? = null,

	@field:SerializedName("ActionName")
	val actionName: Any? = null,

	@field:SerializedName("Type")
	val type: Any? = null,

	@field:SerializedName("AdHeight")
	val adHeight: Int? = null,

	@field:SerializedName("CtaDescription")
	val ctaDescription: Any? = null,

	@field:SerializedName("AdWidth")
	val adWidth: Int? = null,

	@field:SerializedName("CtaType")
	val ctaType: Any? = null,

	@field:SerializedName("Id")
	val id: String? = null,

	@field:SerializedName("CtaUrl")
	val ctaUrl: Any? = null,

	@field:SerializedName("AltText")
	val altText: Any? = null,

	@field:SerializedName("FullScreen")
	val fullScreen: Boolean? = null
)

data class TrainsItem(

	@field:SerializedName("PunctualityRating")
	val punctualityRating: Double? = null,

	@field:SerializedName("DepartureTime")
	val departureTime: String? = null,

	@field:SerializedName("Destination")
	val destination: String? = null,

	@field:SerializedName("TrainName")
	val trainName: String? = null,

	@field:SerializedName("CurrentStatus")
	val currentStatus: Any? = null,

	@field:SerializedName("Rating")
	val rating: Double? = null,

	@field:SerializedName("Prediction")
	val prediction: Any? = null,

	@field:SerializedName("SourceName")
	val sourceName: String? = null,

	@field:SerializedName("DaysOfRun")
	val daysOfRun: DaysOfRun? = null,

	@field:SerializedName("Duration")
	val duration: String? = null,

	@field:SerializedName("ConfirmTktStatus")
	val confirmTktStatus: Any? = null,

	@field:SerializedName("Source")
	val source: String? = null,

	@field:SerializedName("AvaiblityCache")
	val avaiblityCache: Any? = null,

	@field:SerializedName("DepartureInt")
	val departureInt: Int? = null,

	@field:SerializedName("TrainNo")
	val trainNo: String? = null,

	@field:SerializedName("RatingCount")
	val ratingCount: Int? = null,

	@field:SerializedName("FoodRating")
	val foodRating: Double? = null,

	@field:SerializedName("DestinationName")
	val destinationName: String? = null,

	@field:SerializedName("CleanlinessRating")
	val cleanlinessRating: Double? = null,

	@field:SerializedName("TrainType")
	val trainType: Any? = null,

	@field:SerializedName("CacheTime")
	val cacheTime: Any? = null,

	@field:SerializedName("Classes")
	val classes: List<String?>? = null,

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
	val mon: Boolean? = null)
