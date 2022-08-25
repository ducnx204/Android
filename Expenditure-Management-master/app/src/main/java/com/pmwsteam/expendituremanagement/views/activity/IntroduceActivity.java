package com.pmwsteam.expendituremanagement.views.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.pmwsteam.expendituremanagement.R;
import com.pmwsteam.expendituremanagement.controllers.IntroAdapter;
import com.pmwsteam.expendituremanagement.models.Introduce;

import java.util.ArrayList;
import java.util.List;

public class IntroduceActivity extends AppCompatActivity implements View.OnClickListener{
    TextView tvSkip, tvNext;
    Animation btnAnim;
    Button btnGetStarted;
    TabLayout tabIndicator;
    private ViewPager screenPager;
    IntroAdapter introAdapter;
    int position = 0;
    List<Introduce> mList = new ArrayList<>();


    @Override
    protected void onStart() {
        if (restorePreData()) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduce);
        initView();
        addContentAdapter();
        setAdapterPager();
        setOnClickView();
    }
    private void initView() {
        tvNext = findViewById(R.id.tv_next);
        screenPager = findViewById(R.id.screen_viewpager);
        btnGetStarted = findViewById(R.id.btnGetStarted);
        tabIndicator = findViewById(R.id.tab_indicator);
        tvSkip = findViewById(R.id.tv_skip);
        btnAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.buton_animation);
    }

    private void addContentAdapter() {
        mList.add(new Introduce("Quản lý chi tiêu", "Ghi chép khoản thu, chi, vay, nợ theo ngày, tháng, năm", R.drawable.img1));
        mList.add(new Introduce("Báo cáo tự động", "Biểu đồ trưc quan, phân loại theo thời gian, chuyên mục", R.drawable.img2));
        mList.add(new Introduce("Lập kế hoạch", "Tạo mục tiết kiệm, giao dịch định kỳ hoặc theo dõi hoá đơn", R.drawable.img3));
    }
    private void setAdapterPager() {
        introAdapter = new IntroAdapter(getApplicationContext(), mList);
        screenPager.setAdapter(introAdapter);
        tabIndicator.setupWithViewPager(screenPager);
    }

    private void setOnClickView() {
        tvNext.setOnClickListener(this);
        btnGetStarted.setOnClickListener(this);
        tvSkip.setOnClickListener(this);
        tabIndicator.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == mList.size() - 1) {
                    loadLastScreen();
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private boolean restorePreData() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        return pref.getBoolean("isIntroOpened", false);
    }

    private void loadLastScreen() {
        tvNext.setVisibility(View.INVISIBLE);
        btnGetStarted.setVisibility(View.VISIBLE);
        tvSkip.setVisibility(View.INVISIBLE);
        tabIndicator.setVisibility(View.INVISIBLE);
        btnGetStarted.setAnimation(btnAnim);
    }
    private void savePrefData() {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isIntroOpened", true);
        editor.apply();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnGetStarted:
                setEventClickGetStarted();
                break;
            case R.id.tv_next:
                setEventClickTextViewNext();
                break;
            case R.id.tv_skip:
                screenPager.setCurrentItem(mList.size());
                break;
            default:
                Log.d("ssssss", "ssssss");
        }
    }

     void setEventClickTextViewNext() {
         position = screenPager.getCurrentItem();
         if (position < mList.size()) {
             position++;
             screenPager.setCurrentItem(position);
         }
         if (position == mList.size() - 1) {
             loadLastScreen();
         }
    }

    void setEventClickGetStarted() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        savePrefData();
        finish();
    }
}
