package com.app.coffeeapp.ui.authentication.authenticationmain

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.app.coffeeapp.R
import com.app.coffeeapp.databinding.FragmentAuthenticationMainBinding
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthenticationMainFragment : Fragment() {

    private var _binding: FragmentAuthenticationMainBinding? = null
    private val binding get() = _binding!!
    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAuthenticationMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        autoLoginCheck()
        clicks()
    }

    private fun autoLoginCheck() {
        val currentUser = auth.currentUser

        if (currentUser != null) {
            // Kullanıcı daha önce giriş yapmış → direkt HomeMain'e git
            //findNavController().navigate(R.id.action_authenticationMainFragment_to_homeMainFragment)
        }
    }

    private fun clicks() = with(binding) {
        btnLogin.setOnClickListener {
            findNavController().navigate(R.id.action_authenticationMainFragment_to_loginFragment)
        }

        btnSignup.setOnClickListener {
            findNavController().navigate(R.id.action_authenticationMainFragment_to_signupFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}