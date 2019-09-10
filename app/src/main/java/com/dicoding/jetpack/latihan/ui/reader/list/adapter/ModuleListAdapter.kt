package com.dicoding.jetpack.latihan.ui.reader.list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.jetpack.latihan.R
import com.dicoding.jetpack.latihan.data.ModuleEntity
import com.dicoding.jetpack.latihan.ui.reader.list.view.MyAdapterClickListener
import kotlinx.android.synthetic.main.items_module_list_custom.view.*

class ModuleListAdapter(private val listener: MyAdapterClickListener, private val mModules: MutableList<ModuleEntity>) : RecyclerView.Adapter<ModuleListAdapter.ModuleViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModuleViewHolder {
        return ModuleViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.items_module_list_custom, parent, false))
    }

    override fun getItemCount(): Int = mModules.size

    override fun onBindViewHolder(holder: ModuleViewHolder, position: Int) {
        val module = mModules[position]
        holder.bind(module.mTitle)
        holder.itemView.setOnClickListener {
            listener.onItemClicked(holder.adapterPosition, mModules[holder.adapterPosition].mModuleId)
        }
    }

    class ModuleViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(title: String?) {
            itemView.textModuleTitleCustom.text = title
        }
    }
}