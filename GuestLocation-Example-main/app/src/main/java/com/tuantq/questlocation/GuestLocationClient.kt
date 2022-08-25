package com.tuantq.questlocation

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class GuestLocationClient { //sử dụng client server thông qua retrofit
    companion object {
        private var retrofit: Retrofit? = null
        private var baseUrl = "http://ip-api.com/" //check location thông qua ip-api
        var countryCodeVN = "VN" //định danh country là ở Việt Nam

        fun getClient(): Retrofit? { //get client thực hiện việc call api thông qua base Url
            if (retrofit == null) {
                synchronized(GuestLocationClient::class.java)
                {
                    retrofit = Retrofit.Builder()
                        .addConverterFactory(MoshiConverterFactory.create())
                        .baseUrl(baseUrl)
                        .build()
                }
            }
            return retrofit
        }
    }
}