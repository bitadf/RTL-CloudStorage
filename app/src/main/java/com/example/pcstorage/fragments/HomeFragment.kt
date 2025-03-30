package com.example.pcstorage.fragments

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pcstorage.ChangeFragments
import com.example.pcstorage.Dialogs
import com.example.pcstorage.FolderViewModel
import com.example.pcstorage.R
import com.example.pcstorage.ToggleArrows
import com.example.pcstorage.adapter.HomeGridAdapter
import com.example.pcstorage.adapter.HomeRecyclerAdapter
import com.example.pcstorage.data.HomeGridItem
import com.example.pcstorage.data.RecentUploadsItem
import com.example.pcstorage.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding

    private lateinit var foldersRecyclerView : RecyclerView
    private lateinit var gridAdapter: HomeGridAdapter

    private lateinit var recentRecycler : RecyclerView
    private lateinit var recentAdapter: HomeRecyclerAdapter

    private lateinit var folderViewModel: FolderViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater , container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ////////////initialize
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
        folderViewModel = ViewModelProvider(requireActivity()).get(FolderViewModel::class.java)

        /////////////////////////folders
        val folders = mutableListOf<HomeGridItem>()
        val counter = 0
        foldersRecyclerView = binding.homeFoldersRecycler
        foldersRecyclerView.isNestedScrollingEnabled = false
        foldersRecyclerView.layoutManager = GridLayoutManager(requireContext() , 2)
        gridAdapter = HomeGridAdapter(requireContext() , folders){
                position, item ->

            Toast.makeText(requireContext() , "clicked on ${item.title}" , Toast.LENGTH_SHORT).show()
            ChangeFragments.changeFragmentPassString(requireActivity() , ShowFileFragment() , "show_file" , item.title)
        }
        foldersRecyclerView.adapter = gridAdapter

        folderViewModel.folderName.observe(viewLifecycleOwner) { items ->
            folders.clear() // Clear existing items to avoid duplicates
            items.forEachIndexed { index, item ->
                folders.add(
                    HomeGridItem(
                        item,
                        "20 گیک",
                        bgColors[index % 4],
                        colors[index % 4]
                    )
                )
            }
            gridAdapter.notifyDataSetChanged() // Notify adapter once after all items are added
        }

        ///////////////////////////////////////////////

        recentRecycler = binding.homeRecentUploadRecycler
        recentRecycler.isNestedScrollingEnabled = false
        recentRecycler.layoutManager = LinearLayoutManager(requireContext())

        val recent = listOf(
            RecentUploadsItem("fuckdickshit.docx" , "22/22/11" , "20 گیک"  ,0),
            RecentUploadsItem("fuckdickshit.docx" , "22/22/11" , "20 گیک"  ,0),
            RecentUploadsItem("fuckdickshit.docx" , "22/22/11" , "20 گیک"  ,0),
            RecentUploadsItem("fuckdickshit.docx" , "22/22/11" , "20 گیک"  ,0),
            RecentUploadsItem("fuckdickshit.docx" , "22/22/11" , "20 گیک"  ,0),
            RecentUploadsItem("fuckdickshit.docx" , "22/22/11" , "20 گیک"  ,0),
            RecentUploadsItem("fuckdickshit.docx" , "22/22/11" , "20 گیک"  ,0),
            RecentUploadsItem("fuckdickshit.docx" , "22/22/11" , "20 گیک"  ,0)
        )
        recentAdapter = HomeRecyclerAdapter(recent)
        recentRecycler.adapter = recentAdapter



        ////handle arrows
        val folderArrow = binding.homeFoldersArrowIcon
        ToggleArrows.setUpToggle(foldersRecyclerView , folderArrow)

        val recentArrow = binding.homeRecentArrowIcon
        ToggleArrows.setUpToggle(recentRecycler , recentArrow)
        ///////////////////////////////////////////////////////

        binding.homeAddFolder.setOnClickListener{
            Dialogs.addNewFolder(requireContext() , this , folderViewModel)

        }
    }






}