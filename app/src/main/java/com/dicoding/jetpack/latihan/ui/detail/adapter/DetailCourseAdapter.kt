package com.dicoding.jetpack.latihan.ui.detail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.jetpack.latihan.R
import com.dicoding.jetpack.latihan.data.ModuleEntity
import kotlinx.android.synthetic.main.items_module_list.view.*

class DetailCourseAdapter(private val mModules: MutableList<ModuleEntity>) : RecyclerView.Adapter<DetailCourseAdapter.ModuleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModuleViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.items_module_list, parent, false)
        return ModuleViewHolder(view)
    }

    override fun getItemCount(): Int = mModules.size

    override fun onBindViewHolder(holder: ModuleViewHolder, position: Int) {
        holder.bind(mModules[position].mTitle)
    }

    class ModuleViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(title: String?) {
            itemView.textModuleTitle.text = title
        }
    }
}