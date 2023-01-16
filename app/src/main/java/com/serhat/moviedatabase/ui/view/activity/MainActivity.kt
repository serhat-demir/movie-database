package com.serhat.moviedatabase.ui.view.activity

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.serhat.moviedatabase.R
import com.serhat.moviedatabase.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initBottomMenu()
    }

    private fun initBottomMenu() {
        val navHostFragment: NavHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        setupWithNavController(binding.bottomNavigationView, navHostFragment.navController)
    }

    companion object {
        fun ImageView.loadImage(url: String) {
            Picasso.get().load("https://image.tmdb.org/t/p/w500/$url").into(this)
        }
    }
}