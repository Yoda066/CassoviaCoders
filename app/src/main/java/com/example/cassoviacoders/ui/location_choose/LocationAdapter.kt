package com.example.cassoviacoders.ui.location_choose

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cassoviacoders.db.Location
import com.example.cassoviacoders.ui.MainActivityViewModel
import com.example.cassoviacoders.databinding.LocationSearchItemBinding

class LocationAdapter(val viewModel: MainActivityViewModel) : ListAdapter<Location, LocationViewHolder>(LocationDiff) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        return LocationViewHolder(
            LocationSearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            viewModel
        )
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class LocationViewHolder(
    private val binding: LocationSearchItemBinding,
    private val viewModel: MainActivityViewModel
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(location: Location) {
        binding.location = location
        binding.viewmodel = viewModel
    }
}

object LocationDiff : DiffUtil.ItemCallback<Location>() {
    override fun areItemsTheSame(oldItem: Location, newItem: Location): Boolean =
        (oldItem.locId == newItem.locId)

    override fun areContentsTheSame(oldItem: Location, newItem: Location): Boolean =
        (oldItem == newItem)
}