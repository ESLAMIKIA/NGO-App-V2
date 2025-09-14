package com.example.ngo.ui.aboutus

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.ngo.R


class AboutUs : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // اول ویو رو inflate کن
        val view = inflater.inflate(R.layout.aboutus, container, false)

        (requireActivity() as AppCompatActivity).supportActionBar?.show()

        // بعد از روی اون view دکمه رو پیدا کن
        val button = view.findViewById<Button>(R.id.btnContactUs)

        button.setOnClickListener {
            val url = "https://www.eslamikia.ir"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }
       val buttonGithub = view.findViewById<ImageButton>(R.id.btnGithub)

        buttonGithub.setOnClickListener {
            val url = "https://github.com/ESLAMIKIA"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }
        val buttonYoutube = view.findViewById<ImageButton>(R.id.btnYoube)

        buttonYoutube.setOnClickListener {
            val url = "https://youtube.com/@eslamikia?si=bF3B-2qVP1ob66wN"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }
        val buttonLinkdin = view.findViewById<ImageButton>(R.id.btnLinkin)

        buttonLinkdin.setOnClickListener {
            val url = "https://www.linkedin.com/in/amir-erfan-eslamikia-816354326?utm_source=share&utm_campaign=share_via&utm_content=profile&utm_medium=android_app"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }

        return view
    }
}
