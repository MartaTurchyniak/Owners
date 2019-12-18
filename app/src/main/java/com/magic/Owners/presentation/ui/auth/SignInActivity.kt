package com.magic.Owners.presentation.ui.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.magic.Owners.R

/**
 * Created by Marta Turchyniak on 2019-12-01.
 */
class SignInActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.login_navigation) as NavHostFragment? ?: return
        val navController = host.navController

    }

}