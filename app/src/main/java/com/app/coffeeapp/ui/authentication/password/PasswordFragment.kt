package com.app.coffeeapp.ui.authentication.password

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.app.coffeeapp.R
import com.app.coffeeapp.databinding.FragmentPasswordBinding
import com.app.coffeeapp.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PasswordFragment : Fragment() {

    private var _binding: FragmentPasswordBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PasswordViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
        click()
    }

    private fun click() = with(binding) {
        btnUpdate.setOnClickListener {
            val email = etEmail.text.toString().trim()
            
            if (email.isEmpty()) {
                Toast.makeText(
                    requireContext(),
                    "Lütfen e-posta adresinizi girin",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(
                    requireContext(),
                    "Lütfen geçerli bir e-posta adresi girin",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            
            viewModel.sendPasswordResetEmail(email)
        }
    }

    private fun observe() = with(viewModel) {
        passwordState.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.btnUpdate.isEnabled = false
                }

                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.btnUpdate.isEnabled = true

                    Toast.makeText(
                        requireContext(),
                        "Şifre sıfırlama e-postası gönderildi. Lütfen e-posta kutunuzu kontrol edin.",
                        Toast.LENGTH_LONG
                    ).show()

                    findNavController().popBackStack()
                }

                is Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                    binding.btnUpdate.isEnabled = true
                    
                    Toast.makeText(
                        requireContext(),
                        result.message ?: "Bir hata oluştu",
                        Toast.LENGTH_LONG
                    ).show()
                }

                else -> Unit
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}