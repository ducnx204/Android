package com.tuantq.questlocation

import com.tuantq.questlocation.GuestLocationClient.Companion.countryCodeVN
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GuestLocationAction(private val impl: GuestLocationImpl) {

    //thực hiện khai báo sử dụng client
    private val service = GuestLocationClient.getClient()?.create(GuestLocationService::class.java)

    //hàm get location hiện tại thông qua server
    fun getLocationNow() = service?.checkLocation()?.enqueue(
        object : Callback<GuestLocationDto> {
            override fun onResponse(
                call: Call<GuestLocationDto>,
                response: Response<GuestLocationDto>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        when (it.countryCode) {
                            countryCodeVN -> impl.isLocationVietNam(true) //nếu là VN thì trả về true
                            else -> impl.isLocationVietNam(false) //nếu các nước khác trả về false
                        }
                    } ?: impl.onErrorNotLocation("Can't find location") //lỗi nếu không thực hiện request thành công
                }
            }

            override fun onFailure(call: Call<GuestLocationDto>, t: Throwable) {
                //trả về lỗi client
                impl.onErrorNotLocation("Can't find location")
            }
        })

}