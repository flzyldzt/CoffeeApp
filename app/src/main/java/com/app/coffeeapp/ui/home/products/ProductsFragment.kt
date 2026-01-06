package com.app.coffeeapp.ui.home.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.app.coffeeapp.databinding.FragmentProductsBinding
import com.app.coffeeapp.domain.products.ProductUiModel
import com.app.coffeeapp.domain.products.ProductsCategory
import com.app.coffeeapp.ui.home.products.adapter.ProductAdapter
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductsFragment : Fragment() {

    private var _binding: FragmentProductsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ProductsViewModel by viewModels()
    private val categories = ProductsCategory.values().toList()

    private val productAdapter by lazy {
        ProductAdapter(::onAddToCartClick)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupTabs()
        setupSearch()
        setupClickListeners()
        observeViewModel()
    }

    private fun setupRecyclerView() = with(binding.rvProducts) {
        layoutManager = GridLayoutManager(requireContext(), 2)
        adapter = productAdapter
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

    private fun setupClickListeners() = with(binding.icBack) {
        setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun observeViewModel() = with(viewModel. filteredProducts) {
       observe(viewLifecycleOwner) { products ->
            productAdapter.submitList(products)
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun onAddToCartClick(product: ProductUiModel) {
        Toast.makeText(
            requireContext(),
            "${product.name} sepete eklendi",
            Toast.LENGTH_SHORT
        ).show()
        // TODO: Sepete ekleme işlemi burada yapılacak
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}