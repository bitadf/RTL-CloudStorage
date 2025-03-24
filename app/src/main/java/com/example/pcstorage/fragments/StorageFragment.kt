package com.example.pcstorage.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pcstorage.R
import com.example.pcstorage.adapter.StorageTrackAdapter
import com.example.pcstorage.data.StorageTrackItem
import com.example.pcstorage.databinding.FragmentStorageBinding


class StorageFragment : Fragment() {
    private lateinit var binding: FragmentStorageBinding

    private lateinit var storageAdapter: StorageTrackAdapter
    private lateinit var storageRecycler: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStorageBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        storageRecycler = binding.storageRecycler
        storageRecycler.layoutManager = LinearLayoutManager(requireContext())

        val colors = listOf(
            ContextCompat.getColor(requireContext(), R.color.rhino),
            ContextCompat.getColor(requireContext(), R.color.supernova),
            ContextCompat.getColor(requireContext(), R.color.pastel_green),
            ContextCompat.getColor(requireContext(), R.color.cornflower_blue),
            ContextCompat.getColor(requireContext(), R.color.crusta)
        )
        val titles = listOf(
            getString(R.string.filled_space),
            getString(R.string.Images),
            getString(R.string.Videos),
            getString(R.string.Documents),
            getString(R.string.other)
        )
        val progress = listOf(25, 35, 80, 60, 10, 40)
        var items = mutableListOf<StorageTrackItem>()
        for(i in titles.indices){
            items.add(StorageTrackItem(colors[i] , titles[i] , 100 , progress[i]))
        }

        storageAdapter = StorageTrackAdapter(items)
        storageRecycler.adapter = storageAdapter
    }


}