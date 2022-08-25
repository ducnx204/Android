package com.example.assignment_android_pd05266;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment_android_pd05266.course.SubjectActivity;
import com.example.assignment_android_pd05266.map.MapsActivity;
import com.example.assignment_android_pd05266.news.NewsActivity;
import com.example.assignment_android_pd05266.social.SocialsActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    GridView gridView;
    ArrayList<Item> gridArray = new ArrayList<>();
    CustomView customView;
    Bitmap studentIcon, mapsIcon, newsIcon, facebookIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //tạo icon trên giao diện màn hình chính
        studentIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.courseitem);
        mapsIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.compass);
        newsIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.antenna);
        facebookIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.calendar);
        //thiết lập tên title cho từng ảnh
        gridArray.add(new Item(studentIcon, "Student"));
        gridArray.add(new Item(mapsIcon, "Maps"));
        gridArray.add(new Item(newsIcon, "News"));
        gridArray.add(new Item(facebookIcon, "Social"));
        //mapping
        gridView = findViewById(R.id.gridView);
        customView = new CustomView(this, R.layout.row_img, gridArray);
        //truyền dữ liệu vào gridview
        gridView.setAdapter(customView);
        //thiết lập chọn
        gridView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0:
                Intent intent1 = new Intent(MainActivity.this, SubjectActivity.class);
                startActivity(intent1);
                break;
            case 1:
                Intent intent2 = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent2);
                break;
            case 2:
                Intent intent3 = new Intent(MainActivity.this, NewsActivity.class);
                startActivity(intent3);
                break;
            case 3:
                Intent intent4 = new Intent(MainActivity.this, SocialsActivity.class);
                startActivity(intent4);
                break;
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
