package com.tuantq.questlocation


import com.squareup.moshi.Json

data class GuestLocationDto( //đây là model chứa các thông tin json trả về
    @Json(name = "as")
    var asX: String = "",
    @Json(name = "city")
    var city: String = "",
    @Json(name = "country")
    var country: String = "",
    @Json(name = "countryCode")
    var countryCode: String = "",
    @Json(name = "isp")
    var isp: String = "",
    @Json(name = "lat")
    var lat: Double = 0.0,
    @Json(name = "lon")
    var lon: Double = 0.0,
    @Json(name = "org")
    var org: String = "",
    @Json(name = "query")
    var query: String = "",
    @Json(name = "region")
    var region: String = "",
    @Json(name = "regionName")
    var regionName: String = "",
    @Json(name = "status")
    var status: String = "",
    @Json(name = "timezone")
    var timezone: String = "",
    @Json(name = "zip")
    var zip: String = ""
)