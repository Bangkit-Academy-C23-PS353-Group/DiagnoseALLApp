package com.example.diagnosaallapps.Response

import com.google.gson.annotations.SerializedName

data class ResponseLogin(

	@field:SerializedName("access_token")
	val accessToken: String? = null,

	@field:SerializedName("message")
	val message: String? = null
)
