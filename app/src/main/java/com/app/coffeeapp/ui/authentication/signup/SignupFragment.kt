package com.app.coffeeapp.ui.authentication.signup

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.app.coffeeapp.R
import com.app.coffeeapp.databinding.FragmentSignupBinding
import com.app.coffeeapp.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupFragment : Fragment() {

    private var _binding: FragmentSignupBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SignupViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
        click()
        setupPasswordValidation()
    }

    private fun click() = with(binding) {
        btnSignup.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()
            val birth = etBirth.text.toString().trim()

            viewModel.callRegisterService(email, password, birth)
        }

        etBirth.setOnClickListener {
            showDatePicker()
        }

        etPassword.addTextChangedListener { updateSignupButton() }
        etAgainPassword.addTextChangedListener { updateSignupButton() }
        etEmail.addTextChangedListener { updateSignupButton() }
    }

    private fun showDatePicker() {
        val datePicker = DatePickerDialog(
            requireContext(),
            { _, year, month, dayOfMonth ->
                val birth = "$dayOfMonth/${month + 1}/$year"
                binding.etBirth.setText(birth)
            },
            2000, 0, 1
        )
        datePicker.show()
    }

    private fun observe() = with(viewModel) {
        registerState.observe(viewLifecycleOwner) { res ->
            when (res) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    setRegisterState(Resource.Idle())
                }

                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE

                    findNavController().navigate(
                        R.id.action_signupFragment_to_home_nav_graph
                    )
                }

                is Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Log.e("REGISTER_ERROR", res.message.toString())
                    
                    android.widget.Toast.makeText(
                        requireContext(),
                        res.message ?: "Kayıt işlemi sırasında bir hata oluştu",
                        android.widget.Toast.LENGTH_LONG
                    ).show()
                    
                    setRegisterState(Resource.Idle())
                }

                else -> Unit
            }

        }
    }

    private fun setupPasswordValidation() = with(binding) {
        //Şifre şartları kontrolü
        etPassword.addTextChangedListener {
            val password = it.toString()

            if (!viewModel.isValidPassword(password)) {
                tilPassword.error = "Şifre en az 8 karakter olmalı, harf ve rakam içermeli"
            } else {
                tilPassword.error = null
            }

            updateSignupButton()
        }

        //İkinci şifre eşleşme kontrolü
        etAgainPassword.addTextChangedListener {
            val pass1 = etPassword.text.toString()
            val pass2 = it.toString()

            if (pass1 != pass2) {
                tilPasswordConfirm.error = "Şifreler eşleşmiyor."
            } else {
                tilPasswordConfirm.error = null
            }

            updateSignupButton()
        }
    }

    //Buton aktif/pasif kontrol
    private fun updateSignupButton() = with(binding) {
        val email = etEmail.text.toString().trim()
        val pass = etPassword.text.toString()
        val passC = etAgainPassword.text.toString()

        btnSignup.isEnabled =
            email.isNotEmpty() &&
                    viewModel.isValidPassword(pass) &&
                    pass == passC
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}