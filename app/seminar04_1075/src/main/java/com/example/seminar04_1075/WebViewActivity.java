package com.example.seminar04_1075;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;

public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        WebView webView = findViewById(R.id.webView);

        // Activăm JavaScript și navigarea în aplicație
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());

        // Preluăm link-ul trimis din Galerie
        String linkWeb = getIntent().getStringExtra("LINK_WEB");
        if (linkWeb != null && !linkWeb.isEmpty()) {
            webView.loadUrl(linkWeb);
        }
    }
}