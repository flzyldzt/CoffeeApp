package com.app.coffeeapp.ui.home.stores.storesdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.app.coffeeapp.databinding.FragmentStoresDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StoresDetailFragment : Fragment() {

    private var _binding: FragmentStoresDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStoresDetailBinding.inflate(inflater, container, false)
        return binding.root
    }
}