package com.example.trainlivestatus.test

import com.google.gson.annotations.SerializedName

data class TestModel(

	@field:SerializedName("cities")
	val cities: List<CitiesItem?>? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class Images(

	@field:SerializedName("small")
	val small: String? = null,

	@field:SerializedName("large")
	val large: String? = null,

	@field:SerializedName("medium")
	val medium: String? = null
)

data class CitiesItem(

	@field:SerializedName("images")
	val images: Images? = null,

	@field:SerializedName("latitude")
	val latitude: Double? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("longitude")
	val longitude: Double? = null
)
