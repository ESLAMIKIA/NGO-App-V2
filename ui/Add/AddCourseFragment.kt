package com.example.ngo.ui.Add // ← مسیر پکیج خودتو بذار

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.ngo.R
import com.example.ngo.Course
import com.example.ngo.CourseRepository

class AddCourseFragment : Fragment() {

    private lateinit var edtTitle: EditText
    private lateinit var edtDesc: EditText
    private lateinit var btnAdd: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_course, container, false)

        (requireActivity() as AppCompatActivity).supportActionBar?.hide()

        edtTitle = view.findViewById(R.id.edtCourseTitle)
        edtDesc = view.findViewById(R.id.edtCourseDesc)
        btnAdd = view.findViewById(R.id.btnAddCourse)

        btnAdd.setOnClickListener {
            val title = edtTitle.text.toString().trim()
            val desc = edtDesc.text.toString().trim()

            if (title.isNotEmpty() && desc.isNotEmpty()) {
                val newCourse = Course(
                    title = title,
                    description = desc,
                    imageResId = R.drawable.ai // ← باید این drawable رو داشته باشی
                )

                CourseRepository.courseList.add(newCourse)

                Toast.makeText(requireContext(), "Course added!", Toast.LENGTH_SHORT).show()

                // برگشت به صفحه Home
                findNavController().navigate(R.id.navigation_home)

                // پاک کردن فیلدها (اختیاری)
                edtTitle.text.clear()
                edtDesc.text.clear()
            } else {
                Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }

        return view

    }


}
