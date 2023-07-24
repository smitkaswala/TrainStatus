package com.example.trainlivestatus.model

import com.google.gson.annotations.SerializedName

data class NameOrCodeModel(

	@field:SerializedName("NameOrCodeModel")
	val nameOrCodeModel: List<NameOrCodeModelItem?>? = null
)

data class NameOrCodeModelItem(

	@field:SerializedName("p")
	val P: String? = null,

	@field:SerializedName("ct")
	val ct: String? = null,

	@field:SerializedName("c")
	val C: String? = null,

	@field:SerializedName("s")
	val S: String? = null,

	@field:SerializedName("d")
	val D: String? = null,

	@field:SerializedName("origin")
	val origin: String? = null,

	@field:SerializedName("destinationName")
	val destinationName: String? = null,

	@field:SerializedName("destination")
	val destination: String? = null,

	@field:SerializedName("n")
	val N: String? = null,

	@field:SerializedName("originName")
	val originName: String? = null,

	@field:SerializedName("cn")
	val cn: String? = null,

	@field:SerializedName("allString")
	var allString: String? = null


) {
	override fun toString(): String {
		return "NameOrCodeModelItem(P=$P, ct=$ct, C=$C, S=$S, D=$D, origin=$origin, destinationName=$destinationName, destination=$destination, N=$N, originName=$originName, cn=$cn)"
	}
}
