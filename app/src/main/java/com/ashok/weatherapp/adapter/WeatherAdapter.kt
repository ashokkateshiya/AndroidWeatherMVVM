package com.ashok.weatherapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ashok.weatherapp.model.ListItem
import com.ashok.weatherapp.R
import com.ashok.weatherapp.databinding.ItemForecastBinding

class WeatherAdapter(private val clickListener: WeatherListener) :
    RecyclerView.Adapter<WeatherAdapter.ItemViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<ListItem>() {
        override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
            return oldItem.dtTxt == newItem.dtTxt
        }
        override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
            return oldItem == newItem
        }
    }

    internal val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_forecast,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.binding.listItem = differ.currentList[position]
        holder.binding.clickListener = clickListener
    }

    class ItemViewHolder(val binding: ItemForecastBinding) :
        RecyclerView.ViewHolder(binding.root)
}

class WeatherListener(val clickListener: (listItem: ListItem) -> Unit) {
    fun onClick(listItem: ListItem) = clickListener(listItem)
}