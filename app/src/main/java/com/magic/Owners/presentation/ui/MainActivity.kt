package com.magic.Owners.presentation.ui

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.magic.Owners.R
import com.magic.Owners.presentation.ui.create_post.CreatePostFragment
import com.magic.Owners.presentation.ui.feed.FeedFragment
import com.magic.Owners.presentation.ui.profile.ProfileActivity
import com.magic.Owners.presentation.ui.services.ServicesFragment

class MainActivity : AppCompatActivity(), CreatePostFragment.OnFragmentInteractionListener,
    FeedFragment.OnFragmentInteractionListener, ServicesFragment.OnFragmentInteractionListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.my_nav_host_fragment) as NavHostFragment? ?: return
        val navController = host.navController
        setUpBottomNav(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            val dest: String = try {
                resources.getResourceName(destination.id)
            } catch (e: ClassNotFoundException) {
                Integer.toString(destination.id)
            }
            Log.d("NavigationActivity", "Navigated to $dest")
        }
        initUI()
    }

    private fun initUI() {
        val profile = findViewById<ImageView>(R.id.profile)
        profile.setOnClickListener {
            val intent = ProfileActivity.newIntent(this)
            startActivity(intent)
        }
    }

    private fun setUpBottomNav(navController: NavController) {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav_view)
        bottomNav?.setupWithNavController(navController)
    }

    override fun onFragmentInteraction(uri: Uri) {

    }
}
