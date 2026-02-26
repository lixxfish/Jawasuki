package com.lixx.kece

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
  private lateinit var web: WebView

  @SuppressLint("SetJavaScriptEnabled")
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    web = findViewById(R.id.web)

    web.webViewClient = WebViewClient()
    web.webChromeClient = WebChromeClient()

    val s = web.settings
    s.javaScriptEnabled = true
    s.domStorageEnabled = true
    s.cacheMode = WebSettings.LOAD_DEFAULT
    s.useWideViewPort = true
    s.loadWithOverviewMode = true
    s.mediaPlaybackRequiresUserGesture = false
    s.allowFileAccess = false
    s.allowContentAccess = false
    s.setSupportMultipleWindows(false)

    web.loadUrl("http://143.198.85.93:2254")

    onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
      override fun handleOnBackPressed() {
        if (web.canGoBack()) web.goBack()
        else finish()
      }
    })
  }

  override fun onDestroy() {
    try { web.destroy() } catch (_: Throwable) {}
    super.onDestroy()
  }
}
