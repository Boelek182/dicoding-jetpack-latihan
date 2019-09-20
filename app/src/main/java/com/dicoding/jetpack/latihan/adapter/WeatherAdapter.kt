package com.dicoding.jetpack.latihan.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.jetpack.latihan.R
import com.dicoding.jetpack.latihan.data.WeatherItems
import kotlinx.android.synthetic.main.weather_items.view.*

class WeatherAdapter : RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    private val mData = ArrayList<WeatherItems>()

    fun setData(items: MutableList<WeatherItems>) {
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val mView: View = LayoutInflater.from(parent.context).inflate(R.layout.weather_items, parent, false)
        return WeatherViewHolder(mView)
    }

    override fun getItemCount(): Int = mData.size

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(mData[position])
    }

    class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(weatherItems: WeatherItems) {
            itemView.textKota.text = weatherItems.name
            itemView.textTemp.text = weatherItems.temperature
            itemView.textDesc.text = weatherItems.description
        }
    }
}