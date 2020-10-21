package com.example.cassoviacoders.ui.location_choose

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cassoviacoders.databinding.LocationSearchItemBinding
import com.example.cassoviacoders.db.LocationPojo
import com.example.cassoviacoders.ui.MainActivityViewModel

class LocationAdapter(val viewModel: MainActivityViewModel) :
    ListAdapter<LocationPojo, LocationViewHolder>(LocationDiff) {
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
    fun bind(location: LocationPojo) {
        binding.location = location
        binding.viewmodel = viewModel
    }
}

object LocationDiff : DiffUtil.ItemCallback<LocationPojo>() {
    override fun areItemsTheSame(oldItem: LocationPojo, newItem: LocationPojo): Boolean =
        (oldItem.location.locId == newItem.location.locId)

    override fun areContentsTheSame(oldItem: LocationPojo, newItem: LocationPojo): Boolean =
        (oldItem == newItem)
}