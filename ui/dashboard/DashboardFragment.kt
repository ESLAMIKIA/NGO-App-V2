package com.example.ngo.ui.dashboard
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.ngo.R

class DashboardFragment : Fragment() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)

        sharedPreferences = requireActivity().getSharedPreferences("MyResumePrefs", Context.MODE_PRIVATE)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
        // دکمه باز کردن فرم
        view.findViewById<Button>(R.id.btnOpenForm).setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_resumeFormFragment)
        }
        view.findViewById<Button>(R.id.btnAboutUs).setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_aboutUs)
        }

        // نمایش اطلاعات ذخیره شده
        displaySavedData(view)
    }

    private fun displaySavedData(view: View) {
        val fullName = sharedPreferences.getString("full_name", "")
        val email = sharedPreferences.getString("email", "")
        val phone = sharedPreferences.getString("phone", "")
        val jobTitle = sharedPreferences.getString("job_title","")

        val tvName = view.findViewById<TextView>(R.id.tvSavedName)
        val tvEmail = view.findViewById<TextView>(R.id.tvSavedEmail)
        val tvPhone = view.findViewById<TextView>(R.id.tvSavedPhone)
        val tvJobTitle = view.findViewById<TextView>(R.id.tvjob)

        if (!fullName.isNullOrEmpty()) {
            tvName.text = "Name: $fullName"
            tvEmail.text = "Email: $email"
            tvPhone.text = "Phone: $phone"
            tvJobTitle.text = "Job: $jobTitle"
        } else {
            tvName.text = "Please Enter your info"
            tvEmail.text = ""
            tvPhone.text = ""
            tvJobTitle.text = "Job:"
        }
    }
}