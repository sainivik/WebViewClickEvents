# WebViewClickEvents
This project is an example that shows a way to handle multiple clicks in a JavaScript button placed inside a `WebView`

### Screenshot

<img src="https://github.com/sainivik/WebViewClickEvents/blob/master/app/screenshots/webview_click.gif" width="225px" height="474px"/>

##  Here we are providing the HTML code that will be displayed in the `WebView`

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
As you see, we are referring an object called `androidButton`. To make this object exist inside the `WebView`, we need to add a `@JavascriptInterface`

      binding.webView.settings.javaScriptEnabled = true
          binding.webView.webChromeClient = WebChromeClient()
          binding.webView.addJavascriptInterface(CaptureClickJavascriptInterface(), "androidButton")
          binding.webView.loadData(data, "text/html", "UTF-8")


    private inner class CaptureClickJavascriptInterface {
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

Once this is executed, we can refer to `androidButton` and its `onCapturedButtonClicked1` and `onCapturedButtonClicked2` method inside the JavaScript code
