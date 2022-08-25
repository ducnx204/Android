package com.tuantq.questlocation

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

class GuestLocationView : AppCompatActivity(), View.OnClickListener, GuestLocationImpl {

    lateinit var clickUrl: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView() //khởi tạo view lần đầu
        initOnClick() //thực hiện sự kiện on click
    }

    private fun initView() {
        clickUrl = findViewById(R.id.btnLink) //ánh xạ tơi button tham chiếu trong view xml
    }

    private fun initOnClick() {
        clickUrl.setOnClickListener(this) //thực hiện click button
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnLink -> GuestLocationAction(this).getLocationNow() //thực hiện check location xem đang ở đâu
        }
    }

    override fun isLocationVietNam(isLocation: Boolean) {
        when (isLocation) {
            true -> { //nếu true là đang ở việt nam
                openUrlWebView("https://www.24h.com.vn/")
            }
            false -> { //nếu false là đang ở nước khác
                openUrlWebView("https://www.google.com/")
            }
        }
    }

    override fun onErrorNotLocation(err: String) {
        //hiển thị lỗi nếu không thể check được
        Toast.makeText(this, err, Toast.LENGTH_LONG).show()
    }

    //hàm xử lý mở url trên website
    private fun openUrlWebView(url: String) = startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
}

