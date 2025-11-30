package com.app.coffeeapp.ui.authentication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.app.coffeeapp.R
import com.app.coffeeapp.databinding.FragmentOpeningBinding

class OpeningFragment : Fragment() {

    private var _binding: FragmentOpeningBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOpeningBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        click()

    }

    private fun click() = with(binding) {
        btnLogin.setOnClickListener {
            findNavController().navigate(R.id.action_openingFragment_to_loginFragment)
        }

        btnSignup.setOnClickListener {
            findNavController().navigate(R.id.action_openingFragment_to_signupFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}