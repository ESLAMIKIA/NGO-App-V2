package com.example.ngo

import androidx.fragment.app.Fragment
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController


class ResumeFormFragment : Fragment() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_resume_form, container, false)
        sharedPreferences = requireActivity().getSharedPreferences("MyResumePrefs", Context.MODE_PRIVATE)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.show()
        view.findViewById<Button>(R.id.btnSave).setOnClickListener {
            saveData(view)
            findNavController().navigateUp()
        }
    }

    private fun saveData(view: View) {
        val editor = sharedPreferences.edit()

        val etName = view.findViewById<EditText>(R.id.etFullName)
        val etEmail = view.findViewById<EditText>(R.id.etEmail)
        val etPhone = view.findViewById<EditText>(R.id.etPhone)
        val jobtitle = view.findViewById<EditText>(R.id.jobSpinner)

        editor.putString("full_name", etName.text.toString())
        editor.putString("email", etEmail.text.toString())
        editor.putString("phone", etPhone.text.toString())
        editor.putString("job_title", jobtitle.text.toString())

        editor.apply()

        Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show()
    }


}