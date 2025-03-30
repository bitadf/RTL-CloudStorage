package com.example.pcstorage.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pcstorage.R
import com.example.pcstorage.adapter.FileDownloadAdapter
import com.example.pcstorage.data.FileDownloadItem
import com.example.pcstorage.databinding.FragmentAddBinding
import com.example.pcstorage.databinding.FragmentShowFileBinding

class ShowFileFragment : Fragment() {

    private lateinit var binding : FragmentShowFileBinding
    private lateinit var recycler : RecyclerView
    private lateinit var adapter: FileDownloadAdapter
    private  var folderName : String? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShowFileBinding.inflate(layoutInflater , container , false)
        folderName = arguments?.getString("show_file").toString()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(!folderName.isNullOrEmpty()){
            val toolbar = requireActivity().findViewById<TextView>(R.id.toolbarTitle)
            val name = "$folderName / ${getString(R.string.Home)}"
            toolbar.text = name

        }
        recycler = binding.fileFragmentRecycler
        recycler.layoutManager = LinearLayoutManager(requireContext())
        val files = listOf(
            FileDownloadItem("file1" , "333Gb" , false),
            FileDownloadItem("file1" , "333Gb" , false),
            FileDownloadItem("file1" , "333Gb" , false),
            FileDownloadItem("file1" , "333Gb" , false),
            FileDownloadItem("file1" , "333Gb" , false),
        )
        adapter = FileDownloadAdapter(requireContext() , files)
        recycler.adapter = adapter
    }


}