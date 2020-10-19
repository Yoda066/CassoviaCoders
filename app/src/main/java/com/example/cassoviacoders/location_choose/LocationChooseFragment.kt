package com.example.cassoviacoders.location_choose

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.cassoviacoders.MainActivityViewModel
import com.example.cassoviacoders.databinding.FragmentLocationChooseBinding

class LocationChooseFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val activityViewModel: MainActivityViewModel by activityViewModels();

        return FragmentLocationChooseBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewmodel = activityViewModel

            val adapter = LocationAdapter(activityViewModel)
            locationsRecyclerView.adapter = adapter
            activityViewModel.filteredLocations.observe(viewLifecycleOwner, Observer {
                adapter.submitList(it)
            })

        }.root
    }
}