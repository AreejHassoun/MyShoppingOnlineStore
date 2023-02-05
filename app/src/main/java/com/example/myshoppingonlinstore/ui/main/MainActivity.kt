package com.example.myshoppingonlinstore.ui.main

import android.app.Activity
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.myshoppingonlinstore.R
import com.example.myshoppingonlinstore.databinding.ActivityMainBinding
import com.example.myshoppingonlinstore.utils.UserUtils
import com.example.myshoppingonlinstore.utils.extensions.*
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityMainBinding

    //region Lifecycle methods
    override fun onSupportNavigateUp(): Boolean = getNavController().navigateUp()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        // Revert back to app theme to override splash theme in manifest.
        //TODO setTheme(R.style.)

        //Initialize Navigation Drawer
        val navController = findNavController(R.id.nav_host_fragment)

    }

    private fun showBottomNavigationContent() {
        _binding.bottomNavigationView.makeVisible()
    }

    private fun hideBottomNavigationContent() {
        _binding.bottomNavigationView.makeGone()
    }
}