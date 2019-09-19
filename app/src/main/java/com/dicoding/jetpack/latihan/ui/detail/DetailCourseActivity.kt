package com.dicoding.jetpack.latihan.ui.detail

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.jetpack.latihan.R
import com.dicoding.jetpack.latihan.data.CourseEntity
import com.dicoding.jetpack.latihan.ui.detail.adapter.DetailCourseAdapter
import com.dicoding.jetpack.latihan.ui.detail.viewmodel.DetailCourseViewModel
import com.dicoding.jetpack.latihan.ui.reader.CourseReaderActivity
import kotlinx.android.synthetic.main.activity_detail_course.*
import kotlinx.android.synthetic.main.content_detail_course.*

class DetailCourseActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_COURSE = "extra_course"
    }

    private var detailCourseViewModel: DetailCourseViewModel? = null
    private var detailCourseAdapter: DetailCourseAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_course)

        detailCourseViewModel = ViewModelProviders.of(this).get(DetailCourseViewModel()::class.java)

        val extras = intent.extras
        if (extras != null) {
            val courseId = extras.getString(EXTRA_COURSE)
            if (courseId != null) {
                detailCourseViewModel?.courseId = courseId
                detailCourseAdapter = detailCourseViewModel?.getModules()?.let { DetailCourseAdapter(it) }

                //populateCourse(courseId)
            }
        }

        if (detailCourseViewModel?.getCourse() != null) {
            populateCourse(detailCourseViewModel?.getCourse())
        }

        rvModule.isNestedScrollingEnabled = false
        rvModule.layoutManager = LinearLayoutManager(this)
        rvModule.setHasFixedSize(true)
        rvModule.adapter = detailCourseAdapter
        val dividerItemDecoration = DividerItemDecoration(rvModule.context, DividerItemDecoration.VERTICAL)
        rvModule.addItemDecoration(dividerItemDecoration)
    }

    private fun populateCourse(course: CourseEntity?) {
        textTitle.text = course?.title
        textDescription.text = course?.description
        textDate.text = String.format("Deadline %s", course?.deadline)

        Glide.with(applicationContext)
                .load(course?.imagePath)
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                .into(imagePoster)

        btnStart.setOnClickListener {
            val intent = Intent(this, CourseReaderActivity::class.java)
            intent.putExtra(CourseReaderActivity.EXTRA_COURSE_ID, detailCourseViewModel?.courseId)
            it.context.startActivity(intent)
        }
    }

    /*private fun populateCourse(courseId: String) {
        val courseEntity = getCourse(courseId)
        textTitle.text = courseEntity?.title
        textDescription.text = courseEntity?.description
        textDate.text = String.format("Deadline %s", courseEntity?.deadline)

        Glide.with(applicationContext)
                .load(courseEntity?.imagePath)
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                .into(imagePoster)

        btnStart.setOnClickListener {
            val intent = Intent(this, CourseReaderActivity::class.java)
            intent.putExtra(CourseReaderActivity.EXTRA_COURSE_ID, courseId)
            it.context.startActivity(intent)
        }
    }*/
}