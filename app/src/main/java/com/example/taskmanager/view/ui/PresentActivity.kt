package com.example.taskmanager.view.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.taskmanager.R
import com.example.taskmanager.databinding.ActivityPresentBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class PresentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPresentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPresentBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        val navView:BottomNavigationView = findViewById(R.id.bottomNavigationView)
        navView.setupWithNavController(navController )
    }
 
    override fun onSupportNavigateUp(): Boolean {
        Log.i("TAG", "onSupportNavigateUp: ")
        val navController = this.findNavController(R.id.fragmentContainerView)
        return navController.navigateUp()
    }

}