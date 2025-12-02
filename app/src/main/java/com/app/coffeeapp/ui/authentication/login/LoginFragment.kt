package com.app.coffeeapp.ui.authentication.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.app.coffeeapp.R
import com.app.coffeeapp.databinding.FragmentLoginBinding
import com.app.coffeeapp.util.Resource
import com.app.coffeeapp.util.showToast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
        click()
    }

    private fun click() = with(binding) {
        btnLogIn.setOnClickListener {
            viewModel.login(
                email = etEmail.text.toString(),
                password = etPassword.text.toString()
            )
        }

        tvSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signupFragment)
        }

        tvForgetPassword.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_passwordFragment)
        }
    }

    private fun observe() = with(viewModel) {
        loginResult.observe(viewLifecycleOwner) { state ->
            when (state) {
                is Resource.Loading -> showLoadingState()
                is Resource.Success -> showSuccessState(state.data)
                is Resource.Error -> showErrorState(state.message)
            }
        }
    }

    private fun showLoadingState() = with(binding) {
        btnLogIn.isEnabled = false
        progressBar.visibility = View.VISIBLE
    }

    private fun showSuccessState(user: FirebaseUser) = with(binding) {
        btnLogIn.isEnabled = true
        progressBar.visibility = View.GONE
        if (user.isEmailVerified) {
            //findNavController().setGraph(R.navigation.nav_graph_home)
        } else {
            requireContext().showToast(R.string.toast_message_verification)
            FirebaseAuth.getInstance().signOut()
        }
    }

    private fun showErrorState(message: String) = with(binding) {
        btnLogIn.isEnabled = true
        progressBar.visibility = View.GONE //gizle
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}