package com.example.newsexpress;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;

public class NewsFullActivity extends AppCompatActivity {

    WebView webView;
    ImageButton ibtn1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_news_full);
        ibtn1= findViewById(R.id.home);



        String url =getIntent().getStringExtra("url");
        webView = findViewById(R.id.web_view);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);


        ibtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMainActivity();
            }
        });
//
//        ibtn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                goToVideoActivity();
////                Toast.makeText(getApplicationContext(), "settings", Toast.LENGTH_SHORT).show();
//
//            }
//        });
    }
    @Override
    public void onBackPressed() {

        if(webView.canGoBack())
            webView.goBack();
        else
            super.onBackPressed();
    }

//
    private void goToMainActivity() {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
//
//    private void goToVideoActivity() {
//        Intent intent = new Intent(this, VideoActivity.class);
//        startActivity(intent);
//    }


}