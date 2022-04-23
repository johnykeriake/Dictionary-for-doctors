package com.z.doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class translate extends AppCompatActivity {
WebView web;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate1);


        web=findViewById(R.id.webView);
        web.loadUrl("https://translate.google.com/?sl=en&tl=ar&op=translate");
        web.setWebViewClient(new WebViewClient());

        WebSettings webSettings=web.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setDisplayZoomControls(true);
        webSettings.setSupportZoom(true);


    }
}