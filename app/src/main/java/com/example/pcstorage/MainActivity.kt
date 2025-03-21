package com.example.pcstorage

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.collection.emptyLongSet
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.pcstorage.databinding.ActivityMainBinding
import com.example.pcstorage.fragments.AddFragment
import com.example.pcstorage.fragments.HomeFragment
import com.example.pcstorage.fragments.ProfileFragment
import com.example.pcstorage.fragments.StorageFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var toolbar: Toolbar
    private lateinit var toolbarTitle: TextView
    private lateinit var bottomNavigationView: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toolbar = findViewById(R.id.toolbar) as Toolbar
        toolbarTitle = findViewById(R.id.toolbarTitle)
        setSupportActionBar(toolbar)
        supportActionBar?.title = ""


        bottomNavigationView = binding.bottomNav
        if (savedInstanceState == null) {
            changeFragment(HomeFragment())
            bottomNavigationView.selectedItemId = R.id.bottom_nav_home
        }


        setUpBottomNavigation(bottomNavigationView)

        supportFragmentManager.addOnBackStackChangedListener {
            updateToolbarText()
        }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                val currentFragment = supportFragmentManager.findFragmentById(R.id.main_frame)
                if (currentFragment !is HomeFragment) {
                    bottomNavigationView.setOnItemSelectedListener(null)
                    changeFragment(HomeFragment())
                    bottomNavigationView.selectedItemId = R.id.bottom_nav_home
                    setUpBottomNavigation(bottomNavigationView)
                    }
                    return true

                }
            }

            return super.onOptionsItemSelected(item)
        }
    override fun onBackPressed() {


        val currentFragment = supportFragmentManager.findFragmentById(R.id.main_frame)
        if(currentFragment !is HomeFragment){
            bottomNavigationView.setOnItemSelectedListener(null)
            changeFragment(HomeFragment())
            binding.bottomNav.selectedItemId = R.id.bottom_nav_home
            setUpBottomNavigation(bottomNavigationView)

        }
        else onBackPressedDispatcher.onBackPressed()

    }

        fun setUpBottomNavigation(bottomnav : BottomNavigationView){

            bottomnav.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.bottom_nav_home -> {
                        changeFragment(HomeFragment())

                        true

                    }

                    R.id.bottom_nav_add -> {
                        changeFragment(AddFragment())

                        true
                    }

                    R.id.bottom_nav_cloud -> {
                        changeFragment(StorageFragment())
                        true
                    }

                    R.id.bottom_nav_person -> {
                        changeFragment(ProfileFragment())
                        true
                    }

                    else -> {

                        false
                    }
                }
            }
         }
        fun updateToolbarText() {
            val currentFragment = supportFragmentManager.findFragmentById(R.id.main_frame)

            when (currentFragment) {
                is HomeFragment -> {
                    toolbarTitle.text = getString(R.string.Home)
                    supportActionBar?.setDisplayHomeAsUpEnabled(false)

                }

                is AddFragment -> {
                    toolbarTitle.text = getString(R.string.Add)
                    supportActionBar?.setDisplayHomeAsUpEnabled(true)

                }

                is StorageFragment -> {
                    toolbarTitle.text = getString(R.string.Storage)
                    supportActionBar?.setDisplayHomeAsUpEnabled(true)
                }

                is ProfileFragment -> {
                    toolbarTitle.text = getString(R.string.Profile)
                    supportActionBar?.setDisplayHomeAsUpEnabled(true)
                }

            }
        }

        fun changeFragment(fragment: Fragment) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_frame, fragment)
                .addToBackStack(null)
                .commit()
        }
    }
