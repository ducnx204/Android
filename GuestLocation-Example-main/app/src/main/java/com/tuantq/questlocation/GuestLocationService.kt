package com.tuantq.questlocation

import retrofit2.Call
import retrofit2.http.GET


interface GuestLocationService {

    @GET("json")
    fun checkLocation() : Call<GuestLocationDto> //hàm check location

}