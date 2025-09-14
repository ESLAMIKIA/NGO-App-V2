import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.ngo.R

class WebFragment : Fragment() {
    @SuppressLint("MissingInflatedId", "SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {
        val view = inflater.inflate(R.layout.fragment_web, container, false)

        val webView = view.findViewById<WebView>(R.id.webView)
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)

        (requireActivity() as AppCompatActivity).supportActionBar?.hide()

        webView.settings.javaScriptEnabled = true

        webView.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: android.graphics.Bitmap?) {
                progressBar.visibility = View.VISIBLE  // شروع لود → نمایش لودینگ
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                progressBar.visibility = View.GONE     // پایان لود → مخفی‌سازی لودینگ
            }
        }

        webView.loadUrl("https://www.funraise.org/techreport/") // آدرس سایت

        return view
    }
}
