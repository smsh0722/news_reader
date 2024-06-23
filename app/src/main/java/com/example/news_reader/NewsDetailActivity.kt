package com.example.news_reader

import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class NewsDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_news_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val webView = findViewById<WebView>(R.id.news_webView)
        val news = intent.getParcelableExtra<News>("news") ?: return
        val btn_save = findViewById<ImageButton>(R.id.btn_save_ND)
        val btn_memo = findViewById<ImageButton>(R.id.btn_edit)

        webView.webViewClient = WebViewClient()
        webView.webChromeClient = WebChromeClient()
        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.settings.allowFileAccess = true

        val htmlContent = """
            <html>
                <head>
                    <style>
                        .highlight { background-color: yellow; }
                        .note { color: blue; font-style: italic; }
                    </style>
                    <script>
                        function highlight() {
                            document.addEventListener('mouseup', function() {
                                var selection = window.getSelection();
                                if (selection.rangeCount) {
                                    var range = selection.getRangeAt(0);
                                    var span = document.createElement("span");
                                    span.className = "highlight";
                                    range.surroundContents(span);
                                    selection.removeAllRanges();
                                }
                            });
                        }

                        function addNote(note) {
                            var selection = window.getSelection();
                            if (selection.rangeCount && note) {
                                var range = selection.getRangeAt(0);
                                var span = document.createElement("span");
                                span.className = "note";
                                span.textContent = note;
                                range.insertNode(span);
                            }
                        }
                    </script>
                </head>
                <body>
                    <h1>${news.title}</h1>
                    <p>${news.description}</p>
                    <iframe src="${news.link}" width="100%" height="80%" style="border:none;"></iframe>
                </body>
            </html>
        """


        webView.loadDataWithBaseURL(null, htmlContent, "text/html", "UTF-8", null)

        btn_memo.setOnClickListener{
            webView.evaluateJavascript("highlight();", null)
        }
        /*
        btn_save.setOnClickListener {

        }*/
    }
}