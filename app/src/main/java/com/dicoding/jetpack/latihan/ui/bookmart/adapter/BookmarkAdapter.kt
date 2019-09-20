package com.dicoding.jetpack.latihan.ui.bookmart.adapter

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
import com.dicoding.jetpack.latihan.ui.bookmart.view.BookmarkFragmentCallback
import com.dicoding.jetpack.latihan.ui.detail.DetailCourseActivity
import kotlinx.android.synthetic.main.items_bookmark.view.*

class BookmarkAdapter(private val activity: Activity?, private val callback: BookmarkFragmentCallback, var mCourses: MutableList<CourseEntity>?) : RecyclerView.Adapter<BookmarkAdapter.BookmarViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.items_bookmark, parent, false)
        return BookmarViewHolder(view)
    }

    override fun getItemCount(): Int = mCourses?.size ?: 0

    override fun onBindViewHolder(holder: BookmarViewHolder, position: Int) {
        val course = mCourses?.get(position)

        holder.itemView.tvItemTitleBookmark.text = course?.title
        holder.itemView.tvItemDescriptionBookmark.text = course?.description
        holder.itemView.tvItemDateBookmark.text = String.format("Deadline %s", course?.deadline)
        holder.itemView.setOnClickListener {
            val intent = Intent(activity, DetailCourseActivity::class.java)
            intent.putExtra(DetailCourseActivity.EXTRA_COURSE, course?.courseId)
            activity?.startActivity(intent)
        }

        holder.itemView.imgShareBookmark.setOnClickListener {
            callback.onShareClick(mCourses?.get(holder.adapterPosition))
        }

        Glide.with(holder.itemView.context)
                .load(course?.imagePath)
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                .into(holder.itemView.imgPosterBookmark)
    }

    class BookmarViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView)
}