package com.example.pcstorage.fragments

import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pcstorage.Dialogs
import com.example.pcstorage.FolderViewModel
import com.example.pcstorage.R
import com.example.pcstorage.ToggleArrows
import com.example.pcstorage.adapter.HomeGridAdapter
import com.example.pcstorage.adapter.UploadRecyclerAdapter
import com.example.pcstorage.data.AddUploadItem
import com.example.pcstorage.data.HomeGridItem
import com.example.pcstorage.databinding.FragmentAddBinding
import com.example.pcstorage.databinding.FragmentHomeBinding

class AddFragment : Fragment() {

    private lateinit var binding : FragmentAddBinding

    private lateinit var uploadAdapter : UploadRecyclerAdapter
    private lateinit var uploadRecycler : RecyclerView

    private lateinit var recentAdapter: HomeGridAdapter
    private lateinit var recentRecycler  :RecyclerView

    private lateinit var folderViewModel : FolderViewModel

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
        uploadRecycler.isNestedScrollingEnabled = false

        val bgColors = listOf(
            ContextCompat.getColor(requireContext(), R.color.bridesmaid),
            ContextCompat.getColor(requireContext(), R.color.solitude),
            ContextCompat.getColor(requireContext(), R.color.early_down),
            ContextCompat.getColor(requireContext(), R.color.twilight_blue)

        )

        val colors = listOf(
            ContextCompat.getColor(requireContext(), R.color.carnation),
            ContextCompat.getColor(requireContext(), R.color.cornflower_blue),
            ContextCompat.getColor(requireContext(), R.color.saffron),
            ContextCompat.getColor(requireContext(), R.color.turquoise_blue)

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
        for (i in titles.indices) {
            items.add(AddUploadItem(titles[i], dates[i], bgColors[i], colors[i], true, 69))
        }
        uploadAdapter = UploadRecyclerAdapter(requireContext(), items) { position, item ->
            Toast.makeText(requireContext(), "item ${item.title}", Toast.LENGTH_SHORT).show()
        }
        uploadRecycler.adapter = uploadAdapter

        recentRecycler = binding.addGridRecycler
        recentRecycler.layoutManager = GridLayoutManager(requireContext(), 2)
        recentRecycler.isNestedScrollingEnabled = false

        val recentItems = mutableListOf<HomeGridItem>()
        for (i in 0..2) {
            recentItems.add(HomeGridItem(titles[i], "220 گیگ", bgColors[i], colors[i]))
        }
        recentAdapter = HomeGridAdapter(requireContext(), recentItems) { position, item ->
            Toast.makeText(requireContext(), "clicked on ${item.title}", Toast.LENGTH_SHORT).show()
        }
        recentRecycler.adapter = recentAdapter

        folderViewModel = ViewModelProvider(requireActivity()).get(FolderViewModel::class.java)


        //set up arrow toggle
        val uploadArrow = binding.addUploadArrowIcon
        ToggleArrows.setUpToggle(uploadRecycler, uploadArrow)

        val recentArrow = binding.addRecentUploadArrowIcon
        ToggleArrows.setUpToggle(recentRecycler, recentArrow)
        ///////////////////////////////////////////////////

        val pickFileLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result ->
            if(result.resultCode == RESULT_OK){
                result.data?.data?.let { uri ->
                    // Handle the selected file URI here
                    Toast.makeText(requireContext(), "File selected: $uri", Toast.LENGTH_SHORT).show()
                    // You can now use the URI to access the file
                }
            } else {
                Toast.makeText(requireContext(), "File selection canceled", Toast.LENGTH_SHORT).show()
            }
        }
        //add
        var selectedFolder: String? = null
        binding.addButton.setOnClickListener {
            Dialogs.selectFolder(
                requireContext(),
                this,
                folderViewModel
            ) { selectedFolder -> // Lambda callback

                Toast.makeText(requireContext(), "Folder: $selectedFolder", Toast.LENGTH_SHORT).show()
                if (selectedFolder != null) {
                    val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
                        addCategory(Intent.CATEGORY_OPENABLE)
                        type = "*/*"
                    }
                    pickFileLauncher.launch(intent)
                } else {
                    Toast.makeText(requireContext(), "No folder selected", Toast.LENGTH_SHORT).show()
                }

            }
        }
    }
}