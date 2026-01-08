package com.app.coffeeapp.ui.home.dashboard.announcements

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.app.coffeeapp.R
import com.app.coffeeapp.databinding.FragmentAnnouncementsListBinding
import com.app.coffeeapp.ui.common.UiDisplayMode
import com.app.coffeeapp.ui.home.dashboard.adapter.AnnouncementAdapter
import com.app.coffeeapp.util.GridSpacingItemDecoration
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnnouncementsListFragment : Fragment() {

    private var _binding: FragmentAnnouncementsListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AnnouncementsListViewModel by viewModels()

    private val announcementAdapter by lazy {
        AnnouncementAdapter(
            uiDisplayMode = UiDisplayMode.LIST,
            onItemClick = ::onAnnouncementClick
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAnnouncementsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() = binding.rvAnnouncementsList.apply {
        layoutManager = GridLayoutManager(requireContext(), 2)
        adapter = announcementAdapter

        if (itemDecorationCount == 0) {
            addItemDecoration(
                GridSpacingItemDecoration(
                    spanCount = 2,
                    spacing = resources.getDimensionPixelSize(R.dimen.margin_10dp)
                )
            )
        }
    }

    private fun observeViewModel() = with(viewModel) {
        announcements.observe(viewLifecycleOwner) {
            announcementAdapter.submitList(it)
        }
    }

    private fun onAnnouncementClick(id: Int) {
//        val action =
//            AnnouncementsListFragmentDirections
//                .actionAnnouncementsListFragmentToAnnouncementDetailFragment(id)
//
//        findNavController().navigate(action)
        Toast.makeText(
            requireContext(),
            "Announcement id: $id",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}