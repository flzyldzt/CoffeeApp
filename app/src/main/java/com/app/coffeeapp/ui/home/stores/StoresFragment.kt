package com.app.coffeeapp.ui.home.stores

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.coffeeapp.databinding.FragmentStoresBinding
import com.app.coffeeapp.domain.stores.StoreUiModel
import com.app.coffeeapp.ui.home.stores.adapter.StoreAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StoresFragment : Fragment() {

    private var _binding: FragmentStoresBinding? = null
    private val binding get() = _binding!!
    private val viewModel: StoresViewModel by viewModels()

//    private val storeAdapter by lazy {
//        StoreAdapter { store ->
//            onDetailClick(store)
//        }
//    }

    private val storeAdapter by lazy {
        StoreAdapter(::onDetailClick)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStoresBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupSearch()
        setupClickListeners()
        observeViewModel()
    }

    private fun setupRecyclerView() = binding.rvStores.apply {
        layoutManager = LinearLayoutManager(requireContext())
        adapter = storeAdapter
    }

//    private fun setupSearch() {
//        binding.etSearch.doAfterTextChanged { text ->
//            viewModel.setSearchQuery(text?.toString().orEmpty())
//        }
//    }

    private fun setupSearch() = binding.etSearch.doAfterTextChanged {
        viewModel.search(it.toString())
    }

    private fun setupClickListeners() = binding.icBack.setOnClickListener {
        findNavController().navigateUp()
    }

//    private fun observeViewModel() = with(viewModel.filteredStores){
//       observe(viewLifecycleOwner) { stores ->
//            storeAdapter.submitList(stores)
//            binding.progressBar.visibility = View.GONE
//        }
//    }

    private fun observeViewModel() = with(viewModel) {
        uiState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is StoresUiState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is StoresUiState.Success -> {
                    binding.progressBar.visibility = View.GONE
                    storeAdapter.submitList(state.stores)
                }

                is StoresUiState.Empty -> {
                    binding.progressBar.visibility = View.GONE
                    storeAdapter.submitList(emptyList())
                }

                is StoresUiState.Error -> {
                    binding.progressBar.visibility = View.GONE
                }
            }
        }
    }

    private fun onDetailClick(store: StoreUiModel) {
        Toast.makeText(
            requireContext(),
            "${store.name} detayları",
            Toast.LENGTH_SHORT
        ).show()
        // TODO: Store detail sayfasına yönlendirme yapılacak
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}