package com.example.trainlivestatus.model

import com.google.gson.annotations.SerializedName

data class SearchStations(

	@field:SerializedName("SearchStations")
	val searchStations: List<SearchStationsItem?>? = null
)

data class SearchStationsItem(

	@field:SerializedName("a")
	val A: String? = null,

	@field:SerializedName("xid")
	val xid: String? = null,

	@field:SerializedName("c")
	val C: String? = null,

	@field:SerializedName("e")
	val E: String? = null,

	@field:SerializedName("lon")
	val lon: String? = null,

	@field:SerializedName("lat")
	val lat: String? = null
)
