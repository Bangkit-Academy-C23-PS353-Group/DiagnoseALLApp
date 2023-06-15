package com.example.diagnosaallapps.Api


import com.example.diagnosaallapps.Response.ResponseLogin
import com.example.diagnosaallapps.Response.ResponseRegister
import com.example.diagnosaallapps.Response.ResponseUpload
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @FormUrlEncoded
    @POST("login")
    fun Forlogin(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<ResponseLogin>

    @FormUrlEncoded
    @POST("register")
    fun ForRegister(
        @Field("username") username: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<ResponseRegister>

    @Multipart
    @POST("upload")
    fun Forupload(
        @Part  file: MultipartBody.Part,
        @Part("patient") patient: RequestBody,
        @Header("Authorization") Authorization: String,


        ): Call<ResponseUpload>
}