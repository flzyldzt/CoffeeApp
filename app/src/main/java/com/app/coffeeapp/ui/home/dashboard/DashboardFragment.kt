package com.app.coffeeapp.ui.home.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.app.coffeeapp.R
import com.app.coffeeapp.databinding.FragmentDashboardBinding
import com.app.coffeeapp.ui.common.UiDisplayMode
import com.app.coffeeapp.ui.home.dashboard.adapter.AnnouncementAdapter
import com.app.coffeeapp.ui.home.dashboard.adapter.CampaignAdapter
import com.app.coffeeapp.ui.home.dashboard.adapter.FeaturedProductsAdapter
import com.app.coffeeapp.util.HorizontalItemSpacingDecoration
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    //private val viewModel: DashboardViewModel by viewModels() -> Bu DashboardFragment'e özel bir viewmodel üretir.
    private val viewModel: DashboardViewModel by activityViewModels() //aynı Activity içindeki tüm fragmentler aynı ViewModel'i paylaşır.


    private val featuredProductsAdapter by lazy {
        FeaturedProductsAdapter()
    }

    private val campaignAdapter by lazy {
        CampaignAdapter(
            uiMode = UiDisplayMode.DASHBOARD,
            onItemClick = ::onCampaignClicked
        )
    }

    private val announcementAdapter by lazy {
        AnnouncementAdapter(
            uiDisplayMode = UiDisplayMode.DASHBOARD,
            onItemClick = ::onAnnouncementClick
        )
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
        setupAnnouncementsRecyclerView()
        setupSeeAllClicks()
    }

    private fun setupSeeAllClicks() = with(binding) {
        tvSeeAllCampaigns.setOnClickListener {
            navigateToCampaignList()
        }

        tvSeeAllAnnouncement.setOnClickListener {
            navigateToAnnouncementList()
        }
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

        if (itemDecorationCount == 0) {
            addItemDecoration(
                HorizontalItemSpacingDecoration(
                    resources.getDimensionPixelSize(R.dimen.margin_12dp)
                )
            )
        }
    }

    private fun setupAnnouncementsRecyclerView() = binding.rvAnnouncements.apply {
        layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        adapter = announcementAdapter

        PagerSnapHelper().attachToRecyclerView(this)

        if (itemDecorationCount == 0) {
            addItemDecoration(
                HorizontalItemSpacingDecoration(
                    resources.getDimensionPixelSize(R.dimen.margin_12dp)
                )
            )
        }
    }

    private fun observeViewModel() = with(viewModel) {
        storlyProducts.observe(viewLifecycleOwner) {
            featuredProductsAdapter.submitList(it)
        }

        campaigns.observe(viewLifecycleOwner) {
            campaignAdapter.submitList(it.take(3))
            viewModel.updateCampaignCount(it.size)
        }

        announcements.observe(viewLifecycleOwner) {
            announcementAdapter.submitList(it.take(3))
            viewModel.updateAnnouncementCount(it.size)
        }

        campaignCount.observe(viewLifecycleOwner) { count ->
            binding.tvSeeAllCampaigns.text =
                getString(R.string.tv_see_all, count)
        }

        announcementCount.observe(viewLifecycleOwner) { count ->
            binding.tvSeeAllAnnouncement.text =
                getString(R.string.tv_see_all, count)
        }
    }

    private fun onCampaignClicked(id: Int) {
        Toast.makeText(
            requireContext(),
            "Campaign id: $id",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun onAnnouncementClick(id: Int) {
        Toast.makeText(
            requireContext(),
            "Announcement id: $id",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun navigateToCampaignList() {
        findNavController().navigate(
            R.id.action_dashboardFragment_to_campaignsListFragment
        )
    }

    private fun navigateToAnnouncementList() {
        findNavController().navigate(
            R.id.action_dashboardFragment_to_announcementsListFragment
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}