package com.example.rss5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

public class MainActivity3 extends AppCompatActivity {
    ImageView m3_imgBack;
    WebView m3_webView;
    private void AnhXa(){
        m3_imgBack=(ImageView) findViewById(R.id.m3_imgBack);
        m3_webView=(WebView) findViewById(R.id.m3_webView);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        AnhXa();
        Intent intent = getIntent();
        String link = intent.getStringExtra("Link");
        m3_webView.loadUrl(link);
        m3_webView.setWebViewClient(new WebViewClient());
    }
}