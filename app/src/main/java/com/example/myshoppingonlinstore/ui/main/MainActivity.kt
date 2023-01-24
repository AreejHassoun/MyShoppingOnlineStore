package com.example.myshoppingonlinstore.ui.main

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import androidx.appcompat.app.AppCompatActivity
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.Gravity
import android.view.MotionEvent
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.myshoppingonlinstore.R
import com.example.myshoppingonlinstore.databinding.ActivityMainBinding
import com.example.myshoppingonlinstore.utils.NAV_KEY_OPEN_NAV_DRAWER
import com.example.myshoppingonlinstore.utils.NAV_KEY_SAVE_BADGET
import com.example.myshoppingonlinstore.utils.extensions.*

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