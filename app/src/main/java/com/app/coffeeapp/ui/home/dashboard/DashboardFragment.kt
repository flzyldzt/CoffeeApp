package com.app.coffeeapp.ui.home.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.coffeeapp.databinding.FragmentDashboardBinding
import com.app.coffeeapp.ui.home.dashboard.adapter.FeaturedProductsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DashboardViewModel by viewModels()
    private val featuredProductsAdapter = FeaturedProductsAdapter()
    //private lateinit var campaignAdapter: CampaignAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        observeData()

        viewModel.loadFetauredProducts()
    }

    private fun setupRecyclerView() = with(binding) {
        rvFeaturedProducts.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
            adapter = featuredProductsAdapter
        }
    }

    private fun observeData() = with(viewModel) {
        featuredProducts.observe(viewLifecycleOwner) {
            featuredProductsAdapter.submitList(it)
        }
    }
}