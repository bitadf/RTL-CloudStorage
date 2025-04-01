package com.example.pcstorage.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.MemoryFile
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pcstorage.R
import com.example.pcstorage.StartActivity
import com.example.pcstorage.ToggleArrows
import com.example.pcstorage.adapter.HomeGridAdapter
import com.example.pcstorage.adapter.HomeRecyclerAdapter
import com.example.pcstorage.data.HomeGridItem
import com.example.pcstorage.data.RecentUploadsItem
import com.example.pcstorage.databinding.FragmentProfileBinding
import com.example.pcstorage.databinding.FragmentStorageBinding


class ProfileFragment : Fragment() {


    private lateinit var binding: FragmentProfileBinding

    private lateinit var folderAdapter : HomeGridAdapter
    private lateinit var folderRecycler : RecyclerView

    private lateinit var fileAdapter: HomeRecyclerAdapter
    private lateinit var fileRecycler : RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
        val folderTitles = listOf(
            "فولدر یک" ,
            "فولدر دو"
        )
        folderRecycler = binding.profileSharedFolderRecycler
        folderRecycler.isNestedScrollingEnabled = false
        folderRecycler.layoutManager = GridLayoutManager(requireContext() , 2)
        val folderItem = mutableListOf<HomeGridItem>()
        for(i in folderTitles.indices){
            folderItem.add(HomeGridItem(folderTitles[i] , "220 گیگ" , bgColors[i] , colors[i]))
        }
        folderAdapter = HomeGridAdapter(requireContext() , folderItem){
                position, item ->
            Toast.makeText(requireContext() , "clicked on ${item.title}" , Toast.LENGTH_SHORT).show()
        }
        folderRecycler.adapter = folderAdapter

        val sharedFileItem = listOf(
            RecentUploadsItem("dick.txt" , "22/22/22" , "220 گیگ" , 1) ,
            RecentUploadsItem("dick.txt" , "22/22/22" , "220 گیگ" , 1) ,
            RecentUploadsItem("dick.txt" , "22/22/22" , "220 گیگ" , 1) ,
            RecentUploadsItem("dick.txt" , "22/22/22" , "220 گیگ" , 1) ,
            RecentUploadsItem("dick.txt" , "22/22/22" , "220 گیگ" , 1) ,
            RecentUploadsItem("dick.txt" , "22/22/22" , "220 گیگ" , 1) ,
        )
        fileRecycler = binding.profileSharedFilesRecycler
        fileRecycler.isNestedScrollingEnabled = false
        fileRecycler.layoutManager = LinearLayoutManager(requireContext())
        fileAdapter = HomeRecyclerAdapter(sharedFileItem)
        fileRecycler.adapter = fileAdapter


        ///set up arrow toggle
        val folderArrow = binding.profileFoldersArrowIcon
        ToggleArrows.setUpToggle(folderRecycler , folderArrow)

        val fileArrow = binding.profileFilesArrowIcon
        ToggleArrows.setUpToggle(fileRecycler , fileArrow)


        ////handle logout
        val sharedPref = requireActivity().getSharedPreferences("AppPrefs" , Context.MODE_PRIVATE)
        binding.profileLogoutIcon.setOnClickListener{
            sharedPref.edit().putBoolean("LoggedIn" , false).apply()
            startActivity(Intent(requireContext() , StartActivity::class.java))
        }

        //setupProfile
        binding.profileEmailText.text = sharedPref.getString("Email" , "No email found")
    }


}