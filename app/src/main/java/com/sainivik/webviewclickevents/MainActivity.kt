package com.sainivik.webviewclickevents


import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.WebChromeClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.sainivik.webviewclickevents.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val data = """
    <script language="javascript" type="text/javascript">
        function buttonClick1() {
       
            androidButton.onCapturedButtonClicked1();
        }
        function buttonClick2() {
       
            androidButton.onCapturedButtonClicked2();
        }
    </script>
        
    <button type='button' id='button1' onclick='buttonClick1();'>Click Button 1</button>
    <button type='button' id='button2' onclick='buttonClick2();'>Click Button 2</button>
    """

    lateinit var binding: ActivityMainBinding

    @SuppressLint("JavascriptInterface")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.webChromeClient = WebChromeClient()
        binding.webView.addJavascriptInterface(CaptureClickJavascriptInterface1(), "androidButton")
        binding.webView.loadData(data, "text/html", "UTF-8")
    }


    private inner class CaptureClickJavascriptInterface1 {
        @JavascriptInterface
        fun onCapturedButtonClicked1() {
            Toast.makeText(
                this@MainActivity,
                "Button1 is clicked.",
                Toast.LENGTH_SHORT
            ).show()
        }

        @JavascriptInterface
        fun onCapturedButtonClicked2() {
            Toast.makeText(
                this@MainActivity,
                "Button2 is clicked.",
                Toast.LENGTH_SHORT
            ).show()
        }

    }


}
