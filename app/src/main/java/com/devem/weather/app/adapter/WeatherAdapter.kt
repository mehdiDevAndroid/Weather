package com.devem.weather.app.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.devem.weather.app.view.WeatherView
import com.devem.weather.databinding.ItemWeatherBinding
import javax.inject.Inject

class WeatherAdapter @Inject constructor() : Adapter<WeatherAdapter.WeatherHolder>() {

    private var data : ArrayList<WeatherView.Data.Timelines.Intervals>? = null

    class WeatherHolder(var binding: ItemWeatherBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherHolder {
        val binding = ItemWeatherBinding.inflate(LayoutInflater.from(parent.context))
        return WeatherHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherHolder, position: Int) {
        with(data?.getOrNull(0)) {
            holder.binding.tvValueSpeed.text = this?.values?.windSpeed.toString()
            holder.binding.tvValueTemperature.text = this?.values?.temperature.toString()
            holder.binding.tvValueTemperatureApparent.text = this?.values?.temperatureApparent.toString()
        }
    }

    override fun getItemCount(): Int = data?.size ?: 0

    fun updateItems(data : ArrayList<WeatherView.Data.Timelines.Intervals>?){
        this.data = data
    }
}