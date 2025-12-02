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
import com.app.coffeeapp.util.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PasswordFragment : Fragment() {

    companion object {
        private const val EMAIL = "@"
    }

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

            if (email.isNotEmpty() && email.contains(EMAIL)) {
                viewModel.resetPassword(email)
            } else {
                requireContext().showToast(R.string.toast_message_email)
            }
        }
    }

    private fun observe() = with(viewModel) {
        resetResult.observe(viewLifecycleOwner) { state ->
            when (state) {
                is Resource.Loading -> showLoadingState()
                is Resource.Success -> showSuccessState()
                is Resource.Error -> showErrorState(state.message)
            }
        }
    }

    private fun showLoadingState() = with(binding) {
        btnUpdate.isEnabled = false
        btnUpdate.text = getString(R.string.btn_update)
        progressBar.visibility = View.VISIBLE
    }

    private fun showSuccessState() = with(binding) {
        btnUpdate.isEnabled = true
        btnUpdate.text = getString(R.string.btn_update)
        progressBar.visibility = View.GONE
        requireContext().showToast(R.string.toast_message_sent)
        findNavController().popBackStack()
    }

    private fun showErrorState(message: String) = with(binding) {
        btnUpdate.isEnabled = true
        btnUpdate.text = getString(R.string.btn_update)
        progressBar.visibility = View.GONE
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}