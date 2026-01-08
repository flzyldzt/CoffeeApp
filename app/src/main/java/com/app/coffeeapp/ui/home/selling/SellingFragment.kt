package com.app.coffeeapp.ui.home.selling

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.app.coffeeapp.databinding.FragmentSellingBinding
import com.app.coffeeapp.domain.selling.SellingUiModel
import com.app.coffeeapp.domain.selling.SellingCategory
import com.app.coffeeapp.ui.home.selling.adapter.SellingAdapter
import com.app.coffeeapp.util.showToast
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SellingFragment : Fragment() {

    private var _binding: FragmentSellingBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SellingViewModel by viewModels()
    private val categories = SellingCategory.entries

    private val sellingAdapter by lazy {
        SellingAdapter(::onAddToCartClick, ::onFavoriteClick)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSellingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupTabs()
        setupSearch()
        observeViewModel()
    }

    private fun setupRecyclerView() = with(binding.rvProducts) {
        layoutManager = GridLayoutManager(requireContext(), 2)
        adapter = sellingAdapter
    }

    private fun setupTabs() = with(binding.tabLayoutCategories) {
        removeAllTabs()

        categories.forEach {
            addTab(newTab().setText(it.displayName))
        }

        addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewModel.setSelectedCategory(categories[tab.position])
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) = Unit
            override fun onTabReselected(tab: TabLayout.Tab?) = Unit
        })

        getTabAt(0)?.select()
    }

    private fun setupSearch() = with(binding.etSearch){
       doAfterTextChanged {
            viewModel.setSearchQuery(it?.toString().orEmpty())
        }
    }

    private fun observeViewModel() = with(viewModel) {
        filteredProducts.observe(viewLifecycleOwner) { products ->
            sellingAdapter.submitList(products)
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun onAddToCartClick(product: SellingUiModel) {
        requireContext().showToast("${product.name} sepete eklendi")
        // TODO: Sepete ekleme işlemi burada yapılacak
    }
    
    private fun onFavoriteClick(product: SellingUiModel, isFavorite: Boolean) {
        viewModel.toggleFavorite(product, isFavorite)
        val message = if (isFavorite) {
            "${product.name} favorilere eklendi"
        } else {
            "${product.name} favorilerden çıkarıldı"
        }
        requireContext().showToast(message)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}