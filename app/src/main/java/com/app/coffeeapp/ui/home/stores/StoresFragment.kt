package com.app.coffeeapp.ui.home.stores

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.coffeeapp.databinding.FragmentStoresBinding
import com.app.coffeeapp.domain.stores.StoreUiModel
import com.app.coffeeapp.ui.home.stores.adapter.StoreAdapter
import com.app.coffeeapp.ui.home.stores.state.StoresUiState
import com.app.coffeeapp.util.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StoresFragment : Fragment() {

    private var _binding: FragmentStoresBinding? = null
    private val binding get() = _binding!!
    private val viewModel: StoresViewModel by viewModels()
    private var selectedStore: StoreUiModel? = null

    private val storeAdapter by lazy {
        StoreAdapter(::onDetailClick, ::onFavoriteClick)
    }

    private val locationPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->

            val granted =
                permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true ||
                        permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true

            if (granted) {
                selectedStore?.let {
                    navigateToStoreDetail(it)
                }
            } else {
                requireContext().showToast(
                    "Mağaza detayını görmek için konum izni gereklidir"
                )
            }
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
        observeViewModel()
    }

    private fun setupRecyclerView() = binding.rvStores.apply {
        layoutManager = LinearLayoutManager(requireContext())
        adapter = storeAdapter
    }

    private fun setupSearch() = binding.etSearch.doAfterTextChanged {
        viewModel.search(it.toString())
    }

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
        selectedStore = store

        if (hasLocationPermission()) {
            navigateToStoreDetail(store)
        } else {
            requestLocationPermission()
        }
    }


    private fun onFavoriteClick(store: StoreUiModel, isFavorite: Boolean) {
        viewModel.toggleFavorite(store, isFavorite)
        val message = if (isFavorite) {
            "${store.name} favorilere eklendi"
        } else {
            "${store.name} favorilerden çıkarıldı"
        }
        requireContext().showToast(message)
    }

    private fun hasLocationPermission(): Boolean {
        return requireContext().checkSelfPermission(
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == android.content.pm.PackageManager.PERMISSION_GRANTED ||
                requireContext().checkSelfPermission(
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == android.content.pm.PackageManager.PERMISSION_GRANTED
    }

    private fun requestLocationPermission() {
        locationPermissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }

    private fun navigateToStoreDetail(store: StoreUiModel) {
        requireContext().showToast("${store.name} detay sayfasına gidiliyor")

        // TODO: Navigation graph hazırsa burası:
        // findNavController().navigate(
        //     StoresFragmentDirections.actionStoresToStoreDetail(store.id)
        // )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}