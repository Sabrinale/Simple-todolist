package com.example.thao.sqlite2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class Instructions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);
        WebView helpWebView;

        //HAETAAN VIITTAUS XML LEISKAN WEBVIEW ELEMENTTIIN
        helpWebView = (WebView)findViewById(R.id.WebView01);
        //HTML-SISÄLLÖN LATAAMINEN ASSETS TIEDOSTOSTA WEBVIEW ELEMENTTIIN
        helpWebView.loadUrl("file:///android_asset/ohje.html");
    }
}
