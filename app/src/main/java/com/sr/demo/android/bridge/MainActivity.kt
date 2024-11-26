package com.sr.demo.android.bridge

import android.os.Bundle
import android.webkit.JsResult
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import android.webkit.WebViewClient
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import org.json.JSONObject
import top.sunhy.component.jsbridge.IBridgeHandler
import top.sunhy.component.jsbridge.JsBridgeHandler

class MainActivity : AppCompatActivity(), IBridgeHandler by JsBridgeHandler() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val webView = findViewById<WebView>(R.id.webView)

        attach(webView)
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = DefaultWebClient()
        webView.webChromeClient = DefaultWebChromeClient()
        webView.loadUrl("https://demo-nuxt-bridge.vercel.app/") // your url

        registerBridger("nativeHandler") { data, callback ->
            AlertDialog.Builder(this)
                .setTitle("Data From WebView")
                .setMessage(data)
                .create()
                .show()
            if (callback != null) {
                callback("native response")
            }
        }

        val btnCallToWebview = findViewById<Button>(R.id.btnCallToWebview)
        btnCallToWebview.setOnClickListener {

            callBridger("jsHandler", "Testing") { response ->
                AlertDialog.Builder(this)
                    .setTitle("Native Alert")
                    .setMessage("Received Js Response.\n $response")
                    .create()
                    .show()
            }
        }

        val btnReloadWebview = findViewById<Button>(R.id.btnReloadWebview)
        btnReloadWebview.setOnClickListener {
            webView.reload()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        detach()
        val webView = findViewById<WebView>(R.id.webView)
        webView.settings.javaScriptEnabled = false
    }

    private inner class DefaultWebClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
            return super.shouldOverrideUrlLoading(view, request)
        }
    }

    private inner class DefaultWebChromeClient : WebChromeClient() {
        override fun onJsAlert(view: WebView?, url: String?, message: String?, result: JsResult?): Boolean {
            return false
        }
    }
}