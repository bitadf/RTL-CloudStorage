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
import com.example.pcstorage.adapter.UploadRecyclerAdapter
import com.example.pcstorage.data.AddUploadItem
import com.example.pcstorage.databinding.FragmentAddBinding
import com.example.pcstorage.databinding.FragmentHomeBinding

class AddFragment : Fragment() {

    private lateinit var binding : FragmentAddBinding
    private lateinit var uploadAdapter : UploadRecyclerAdapter
    private lateinit var uploadRecycler : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding  = FragmentAddBinding.inflate(layoutInflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        uploadRecycler = binding.addUploadRecycler
        uploadRecycler.layoutManager = LinearLayoutManager(requireContext())

        val bgColors = listOf(
            ContextCompat.getColor(requireContext(),R.color.bridesmaid),
            ContextCompat.getColor(requireContext(),R.color.solitude),
            ContextCompat.getColor(requireContext(),R.color.early_down),
            ContextCompat.getColor(requireContext(),R.color.twilight_blue)

        )

        val colors = listOf(
            ContextCompat.getColor(requireContext(),R.color.carnation),
            ContextCompat.getColor(requireContext(),R.color.cornflower_blue),
            ContextCompat.getColor(requireContext(),R.color.saffron),
            ContextCompat.getColor(requireContext(),R.color.turquoise_blue)

        )
        val titles = listOf(
            "title 1",
            "title 2",
            "title 3",
        )
        val dates = listOf(
            "date 1",
            "date 2",
            "date 3"
        )
        val items = mutableListOf<AddUploadItem>()
        for(i in titles.indices){
            items.add(AddUploadItem(titles[i] , dates[i] , bgColors[i] , colors[i] , true , 69))
        }
        uploadAdapter = UploadRecyclerAdapter(items)
        uploadRecycler.adapter = uploadAdapter

    }
}