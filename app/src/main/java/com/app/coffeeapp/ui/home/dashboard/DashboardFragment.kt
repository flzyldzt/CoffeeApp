package com.app.coffeeapp.ui.home.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.coffeeapp.databinding.FragmentDashboardBinding
import com.app.coffeeapp.ui.home.dashboard.adapter.CampaignAdapter
import com.app.coffeeapp.ui.home.dashboard.adapter.FeaturedProductsAdapter
import com.app.coffeeapp.ui.home.dashboard.model.Campaign
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DashboardViewModel by viewModels()

    private val featuredProductsAdapter by lazy {
        FeaturedProductsAdapter()
    }

    private val campaignAdapter by lazy {
        CampaignAdapter(::onCampaignClicked)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()
        observeViewModel()

        viewModel.loadData()
    }

    private fun setupUi() {
        setupFeaturedProductsRecyclerView()
        setupCampaignsRecyclerView()
    }

    private fun setupFeaturedProductsRecyclerView() = binding.rvFeaturedProducts.apply {
        layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        adapter = featuredProductsAdapter
    }

    private fun setupCampaignsRecyclerView() = binding.rvCampaigns.apply {
        layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        adapter = campaignAdapter
        }

    private fun observeViewModel() = with(viewModel) {
        featuredProducts.observe(viewLifecycleOwner) {
            featuredProductsAdapter.submitList(it)
        }

        campaigns.observe(viewLifecycleOwner) {
            campaignAdapter.submitList(it)
        }
    }

    private fun onCampaignClicked(campaign: Campaign) {
        Toast.makeText(
            requireContext(),
            "Campaign id: ${campaign.id}",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}