package com.app.coffeeapp.ui.home.products

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.app.coffeeapp.databinding.FragmentProductsBinding
import com.app.coffeeapp.domain.products.ProductUiModel
import com.app.coffeeapp.ui.home.products.adapter.ProductAdapter
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductsFragment : Fragment() {

    private var _binding: FragmentProductsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ProductsViewModel by viewModels()

    private val productAdapter by lazy {
        ProductAdapter { product ->
            onAddToCartClick(product)
        }
    }

    private val categories = listOf(
        "Sıcak İçecekler",
        "Soğuk İçecekler",
        "Kahve Çeşitleri",
        "Tatlılar",
        "Diyetlik Yemekler"
    )

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

    private fun setupRecyclerView() {
        binding.rvProducts.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = productAdapter
        }
    }

    private fun setupTabs() {
        // TabLayout'u temizle ve yeni tab'ları ekle
        binding.tabLayoutCategories.removeAllTabs()
        categories.forEach { category ->
            binding.tabLayoutCategories.addTab(
                binding.tabLayoutCategories.newTab().setText(category)
            )
        }

        binding.tabLayoutCategories.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {
                    val category = categories[it.position]
                    viewModel.setSelectedCategory(category)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        // İlk tab'ı seçili yap
        binding.tabLayoutCategories.getTabAt(0)?.select()
    }

    private fun setupSearch() {
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                viewModel.setSearchQuery(s?.toString() ?: "")
            }
        })
    }

    private fun setupClickListeners() {
        binding.icBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun observeViewModel() {
        viewModel.filteredProducts.observe(viewLifecycleOwner) { products ->
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