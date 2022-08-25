package com.tuantq.questlocation

interface GuestLocationImpl {
    fun isLocationVietNam(isLocation: Boolean) //hàm kiểm tra location có phải ở việt nam hay là không
    fun onErrorNotLocation(err: String) //hàm hiển thị lỗi
}