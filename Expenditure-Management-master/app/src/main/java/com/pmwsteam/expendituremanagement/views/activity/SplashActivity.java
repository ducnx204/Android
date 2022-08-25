package com.pmwsteam.expendituremanagement.views.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.pmwsteam.expendituremanagement.R;

public class SplashActivity extends AppCompatActivity {
    ImageView imgLoading;
    Thread thread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
        loadAnimation();
        intentActivity();
    } private void intentActivity() {
        thread = new Thread(){
            @Override
            public void run() {
                super.run();
                int waited = 0;
                while (waited < 3000){
                    try {
                        sleep(100);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    waited +=100;
                }
                Intent intent = new Intent(getApplicationContext(), IntroduceActivity.class).setFlags(Intent.FLAG_RECEIVER_REGISTERED_ONLY);
                startActivity(intent);
                finish();
            }
        };
        thread.start();
    }

    private void loadAnimation() {
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotation);
        imgLoading.startAnimation(animation);
    }

    private void initView() {
        imgLoading = findViewById(R.id.imgLoading);
    }
}
