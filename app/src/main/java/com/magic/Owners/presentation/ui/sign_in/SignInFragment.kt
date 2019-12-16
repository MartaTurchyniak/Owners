package com.magic.Owners.presentation.ui.sign_in

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.magic.Owners.R
import kotlinx.android.synthetic.main.login_frg.*

/**
 * Created by Marta Turchyniak on 2019-12-01.
 */
class SignInFragment : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.login_frg, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnSignin.setOnClickListener {
            it.findNavController().navigate(R.id.mainActivity)
        }
        navigate_signup.setOnClickListener {
            it.findNavController().navigate(R.id.signUpFragment)
        }
    }
}