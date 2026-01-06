package com.app.coffeeapp.ui.home.dashboard.campaigns

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.coffeeapp.R
import com.app.coffeeapp.databinding.FragmentCampaignsListBinding
import com.app.coffeeapp.ui.common.UiDisplayMode
import com.app.coffeeapp.ui.home.dashboard.adapter.CampaignAdapter
import com.app.coffeeapp.util.VerticalItemSpacingDecoration
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CampaignsListFragment : Fragment() {

    private var _binding: FragmentCampaignsListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CampaignsListViewModel by viewModels()

    private val campaignAdapter by lazy {
        CampaignAdapter(
            uiMode = UiDisplayMode.LIST,
            onItemClick = ::onCampaignClicked
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCampaignsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler()
        observeData()
        click()
    }

    private fun click() = with(binding) {
        icBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setupRecycler() = binding.rvCampaignList.apply {
        layoutManager = LinearLayoutManager(requireContext())
        adapter = campaignAdapter

        if (itemDecorationCount == 0) {
            addItemDecoration(
                VerticalItemSpacingDecoration(
                    resources.getDimensionPixelSize(R.dimen.margin_32dp)
                )
            )
        }
    }

    private fun observeData() = with(viewModel) {
        campaigns.observe(viewLifecycleOwner) {
            campaignAdapter.submitList(it)
        }
    }

    private fun onCampaignClicked(id: Int) {
//        val action =
//            CampaignsListFragmentDirections
//                .actionCampaignsListFragmentToCampaignDetailFragment(id)
//
//        findNavController().navigate(action)
        Toast.makeText(requireContext(), "Campaign id: $id", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}