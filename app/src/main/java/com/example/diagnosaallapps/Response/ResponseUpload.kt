package com.example.diagnosaallapps.Response

import com.google.gson.annotations.SerializedName

data class ResponseUpload(

	@field:SerializedName("result")
	val result: String? = null,

	@field:SerializedName("patient")
	val patient: String? = null
)
