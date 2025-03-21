package com.example.pcstorage.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pcstorage.R
import com.example.pcstorage.adapter.HomeGridAdapter
import com.example.pcstorage.adapter.HomeRecyclerAdapter
import com.example.pcstorage.data.HomeGridItem
import com.example.pcstorage.data.RecentUploadsItem
import com.example.pcstorage.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding

    private lateinit var gridRecyclerView : RecyclerView
    private lateinit var gridAdapter: HomeGridAdapter

    private lateinit var recentRecycler : RecyclerView
    private lateinit var recentAdapter: HomeRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater , container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gridRecyclerView = binding.recyclerView
        gridRecyclerView.layoutManager = GridLayoutManager(requireContext() , 2)

        val cards = listOf(
            HomeGridItem( getString(R.string.Images) , "20Gb" , ContextCompat.getColor(requireContext(),R.color.bridesmaid),ContextCompat.getColor(requireContext() , R.color.carnation)),
            HomeGridItem( getString(R.string.Documents) , "20Gb" , ContextCompat.getColor(requireContext() , R.color.early_down) , ContextCompat.getColor(requireContext() , R.color.saffron)),
            HomeGridItem( getString(R.string.Videos) , "20Gb" , ContextCompat.getColor(requireContext(),R.color.twilight_blue) , ContextCompat.getColor(requireContext() , R.color.turquoise_blue)),
            HomeGridItem( getString(R.string.All) , "20Gb" , ContextCompat.getColor(requireContext() , R.color.solitude) , ContextCompat.getColor(requireContext() , R.color.cornflower_blue))
        )

        gridAdapter = HomeGridAdapter(cards)
        gridRecyclerView.adapter = gridAdapter


        recentRecycler = binding.uploadRecyclerView
        recentRecycler.isNestedScrollingEnabled = false
        recentRecycler.layoutManager = LinearLayoutManager(requireContext())

        val recent = listOf(
            RecentUploadsItem("fuckdickshit.docx" , "November 3,2003" , "230Kb"  ,0),
            RecentUploadsItem("fuckdickshit.docx" , "November 3,2003" , "230Kb"  ,0),
            RecentUploadsItem("fuckdickshit.docx" , "November 3,2003" , "230Kb"  ,0),
            RecentUploadsItem("fuckdickshit.docx" , "November 3,2003" , "230Kb"  ,0),
            RecentUploadsItem("fuckdickshit.docx" , "November 3,2003" , "230Kb"  ,0),
            RecentUploadsItem("fuckdickshit.docx" , "November 3,2003" , "230Kb"  ,0),
            RecentUploadsItem("fuckdickshit.docx" , "November 3,2003" , "230Kb"  ,0),
            RecentUploadsItem("fuckdickshit.docx" , "November 3,2003" , "230Kb"  ,0),
            RecentUploadsItem("fuckdickshit.docx" , "November 3,2003" , "230Kb"  ,0),
            RecentUploadsItem("fuckdickshit.docx" , "November 3,2003" , "230Kb"  ,0)
        )
        recentAdapter = HomeRecyclerAdapter(recent)
        recentRecycler.adapter = recentAdapter

    }


}