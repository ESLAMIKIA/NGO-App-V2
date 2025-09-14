package com.example.ngo.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.ngo.CourseRepository
import com.example.ngo.CourseRepository.courseList2
import com.example.ngo.R
import CourseAdapter

class HomeFragment : Fragment() {

    private lateinit var recyclerView1: RecyclerView
    private lateinit var recyclerView2: RecyclerView
    private lateinit var recyclerView3: RecyclerView

    private lateinit var adapter1: CourseAdapter
    private lateinit var adapter2: CourseAdapter
    private lateinit var adapter3: CourseAdapter

    private lateinit var viewPager: ViewPager2
    private lateinit var sliderAdapter: SliderAdapter
    private val handler = Handler(Looper.getMainLooper())
    private var currentPage = 0
    private lateinit var runnable: Runnable

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // RecyclerViews
        recyclerView1 = view.findViewById(R.id.recyclerViewCourses)
        recyclerView2 = view.findViewById(R.id.recyclerViewCourses2)
        recyclerView3 = view.findViewById(R.id.recyclerViewCourses3)

        recyclerView1.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true)
        recyclerView2.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true)
        recyclerView3.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true)

        // SlideShow
        viewPager = view.findViewById(R.id.viewPagerSpecial)
        val sliderImages = listOf(R.drawable.arduino_workshop, R.drawable.database_course, R.drawable.git_workshop)
        sliderAdapter = SliderAdapter(sliderImages)
        viewPager.adapter = sliderAdapter

        // اسلاید خودکار
        runnable = object : Runnable {
            override fun run() {
                if (!isAdded) return
                viewPager.currentItem = currentPage
                currentPage = (currentPage + 1) % sliderImages.size
                handler.postDelayed(this, 4000)
            }
        }
        handler.post(runnable)

        (requireActivity() as AppCompatActivity).supportActionBar?.hide()

        CourseRepository.initializeDefaults()

        return view
    }

    override fun onResume() {
        super.onResume()

        val courseList = CourseRepository.courseList

        adapter1 = CourseAdapter(courseList) {
            Toast.makeText(requireContext(), "Course selected in RecyclerView 1!", Toast.LENGTH_SHORT).show()
        }
        recyclerView1.adapter = adapter1

        adapter2 = CourseAdapter(courseList2) {
            Toast.makeText(requireContext(), "Course selected in RecyclerView 2!", Toast.LENGTH_SHORT).show()
        }
        recyclerView2.adapter = adapter2

        adapter3 = CourseAdapter(courseList) {
            Toast.makeText(requireContext(), "Course selected in RecyclerView 3!", Toast.LENGTH_SHORT).show()
        }
        recyclerView3.adapter = adapter3
    }

    override fun onDestroyView() {
        super.onDestroyView()
        handler.removeCallbacks(runnable)
    }
}

// Adapter برای SlideShow
class SliderAdapter(private val images: List<Int>) :
    RecyclerView.Adapter<SliderAdapter.SliderViewHolder>() {

    inner class SliderViewHolder(val imageView: ImageView) : RecyclerView.ViewHolder(imageView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        val imageView = ImageView(parent.context)
        imageView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        return SliderViewHolder(imageView)
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        holder.imageView.setImageResource(images[position])
    }

    override fun getItemCount(): Int = images.size
}
