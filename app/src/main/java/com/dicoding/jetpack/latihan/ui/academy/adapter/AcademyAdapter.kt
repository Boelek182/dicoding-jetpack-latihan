package com.dicoding.jetpack.latihan.ui.academy.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.jetpack.latihan.R
import com.dicoding.jetpack.latihan.data.CourseEntity
import com.dicoding.jetpack.latihan.ui.detail.DetailCourseActivity
import kotlinx.android.synthetic.main.items_academy.view.*

class AcademyAdapter(private val activity: Activity?, var mCourses: MutableList<CourseEntity>?) : RecyclerView.Adapter<AcademyAdapter.AcademyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AcademyViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.items_academy, parent, false)
        return AcademyViewHolder(view)
    }

    override fun getItemCount(): Int = mCourses?.size ?: 0

    override fun onBindViewHolder(holder: AcademyViewHolder, position: Int) {
        holder.itemView.tvItemTitleAcademy.text = mCourses?.get(position)?.title
        holder.itemView.tvItemDescriptionAcademy.text = mCourses?.get(position)?.description
        holder.itemView.tvItemDateAcademy.text = String.format("Deadline %s", mCourses?.get(position)?.deadline)
        holder.itemView.setOnClickListener {
            val intent = Intent(activity, DetailCourseActivity::class.java)
            intent.putExtra(DetailCourseActivity.EXTRA_COURSE, mCourses?.get(position)?.courseId)
            activity?.startActivity(intent)
        }
        Glide.with(holder.itemView.context)
                .load(mCourses?.get(position)?.imagePath)
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                .into(holder.itemView.imgPosterAcademy)
    }

    class AcademyViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView)
}