package com.izo.fatless.data.request

import com.google.gson.annotations.SerializedName


class RequestPredict : ArrayList<RequestPredict.RequestPredictItem>(){
	data class RequestPredictItem(
		@field:SerializedName("Abdomen")
		var Abdomen: Double,
		@field:SerializedName("Age")
		var Age: Int,
		@field:SerializedName("Biceps")
		var Biceps: Double,
		@field:SerializedName("Chest")
		var Chest: Double,
		@field:SerializedName("Density")
		var Density: Double,
		@field:SerializedName("Height")
		var Height: Double,
		@field:SerializedName("Hip")
		var Hip: Double,
		@field:SerializedName("Thigh")
		var Thigh: Double,
		@field:SerializedName("Weight")
		var Weight: Double
	)
}