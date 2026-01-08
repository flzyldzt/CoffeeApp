package com.app.coffeeapp.ui.home.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.coffeeapp.databinding.FragmentFavoritesBinding
import com.app.coffeeapp.ui.home.favorites.adapter.FavoriteProductAdapter
import com.app.coffeeapp.ui.home.favorites.adapter.FavoriteStoreAdapter
import com.app.coffeeapp.util.showToast
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FavoritesViewModel by viewModels()

    private val favoriteStoreAdapter by lazy {
        FavoriteStoreAdapter(::onStoreClick, ::onStoreFavoriteClick)
    }

    private val favoriteProductAdapter by lazy {
        FavoriteProductAdapter(::onProductClick, ::onProductFavoriteClick)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerViews()
        setupTabs()
        observeViewModel()
    }

    private fun setupRecyclerViews() {
        binding.rvFavorites.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = favoriteStoreAdapter
        }
    }
    
    override fun onResume() {
        super.onResume()
        checkEmptyStateForCurrentTab()
    }
    
    private fun checkEmptyStateForCurrentTab() {
        val tabPosition = binding.tabLayoutFavorites.selectedTabPosition
        val isEmpty = when (tabPosition) {
            0 -> viewModel.favoriteStores.value.isNullOrEmpty()
            1 -> viewModel.favoriteProducts.value.isNullOrEmpty()
            else -> true
        }
        updateEmptyState(isEmpty, tabPosition)
    }

    private fun setupTabs() = with(binding.tabLayoutFavorites) {
        addTab(newTab().setText("Mağazalar"))
        addTab(newTab().setText("Ürünler"))

        addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let { switchTab(it.position) }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) = Unit
            override fun onTabReselected(tab: TabLayout.Tab?) = Unit
        })

        getTabAt(0)?.select()
    }

    private fun switchTab(tabPosition: Int) {
        when (tabPosition) {
            0 -> {
                binding.rvFavorites.layoutManager = LinearLayoutManager(requireContext())
                binding.rvFavorites.adapter = favoriteStoreAdapter
            }
            1 -> {
                binding.rvFavorites.layoutManager = GridLayoutManager(requireContext(), 2)
                binding.rvFavorites.adapter = favoriteProductAdapter
            }
        }
        checkEmptyStateForCurrentTab()
    }
    
    private fun observeViewModel() = with(viewModel) {
        favoriteStores.observe(viewLifecycleOwner) { stores ->
            favoriteStoreAdapter.submitList(stores)
            if (binding.tabLayoutFavorites.selectedTabPosition == 0) {
                updateEmptyState(stores.isEmpty(), 0)
            }
        }

        favoriteProducts.observe(viewLifecycleOwner) { products ->
            favoriteProductAdapter.submitList(products)
            if (binding.tabLayoutFavorites.selectedTabPosition == 1) {
                updateEmptyState(products.isEmpty(), 1)
            }
        }
        
        toastMessage.observe(viewLifecycleOwner) { message ->
            message?.let {
                requireContext().showToast(it)
                clearToastMessage()
            }
        }
    }

    private fun updateEmptyState(isEmpty: Boolean, tabPosition: Int) {
        binding.tvEmptyState.visibility = if (isEmpty) View.VISIBLE else View.GONE
        binding.rvFavorites.visibility = if (isEmpty) View.GONE else View.VISIBLE
        
        // Update empty state text based on tab
        val emptyText = when (tabPosition) {
            0 -> "Favori mağazanız bulunmamaktadır"
            1 -> "Favori ürününüz bulunmamaktadır"
            else -> "Favori bulunmamaktadır"
        }
        binding.tvEmptyState.text = emptyText
    }

    private fun onStoreClick(storeId: Int) {
        // TODO: Navigate to store detail
    }

    private fun onStoreFavoriteClick(storeId: Int) {
        viewModel.removeFavoriteStore(storeId)
    }

    private fun onProductClick(productId: Int) {
        // TODO: Navigate to product detail
    }

    private fun onProductFavoriteClick(productId: Int) {
        viewModel.removeFavoriteProduct(productId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

