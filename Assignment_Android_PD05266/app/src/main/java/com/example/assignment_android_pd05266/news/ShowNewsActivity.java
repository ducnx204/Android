package com.example.assignment_android_pd05266.news;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.assignment_android_pd05266.R;

public class ShowNewsActivity extends AppCompatActivity {
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_news);

        webView = findViewById(R.id.webview);

        Intent intent = getIntent();
        String link = intent.getStringExtra("linkTT");

        webView.loadUrl(link);
        webView.setWebViewClient(new WebViewClient());
    }
}