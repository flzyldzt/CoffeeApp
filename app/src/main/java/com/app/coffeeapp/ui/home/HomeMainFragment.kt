package com.app.coffeeapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.app.coffeeapp.R
import com.app.coffeeapp.databinding.FragmentHomeMainBinding
import com.app.coffeeapp.ui.home.dashboard.DashboardFragment
import com.app.coffeeapp.ui.home.favorites.FavoritesFragment
import com.app.coffeeapp.ui.home.selling.SellingFragment
import com.app.coffeeapp.ui.home.stores.StoresFragment
import com.app.coffeeapp.util.toolbar.ToolbarProperties
import com.app.coffeeapp.util.toolbar.ToolbarType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeMainFragment : Fragment() {

    private var _binding: FragmentHomeMainBinding? = null
    private val binding get() = _binding!!
    private var navController: NavController? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        initNavigation()
        initBottomView()
    }

    private fun initToolbar() {
        binding.toolbarHomeMain.isVisible = false
    }

    private fun initBottomView() = with(binding) {
        replaceFragment(DashboardFragment())
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menuDashboard -> {
                    replaceFragment(DashboardFragment())
                    toolbarHomeMain.setProperties(
                        ToolbarProperties(
                            type = ToolbarType.GONE,
                        )
                    )
                    bottomVisibility(true)
                }

                R.id.menuFavorites -> {
                    replaceFragment(FavoritesFragment())
                    toolbarHomeMain.setProperties(
                        ToolbarProperties(
                            type = ToolbarType.GONE,
                        )
                    )
                    bottomVisibility(true)
                }

                R.id.menuSelling -> {
                    replaceFragment(SellingFragment())
                    toolbarHomeMain.setProperties(
                        ToolbarProperties(
                            type = ToolbarType.GONE,
                        )
                    )
                    bottomVisibility(true)
                }

                R.id.menuStore -> {
                    replaceFragment(StoresFragment())
                    toolbarHomeMain.setProperties(
                        ToolbarProperties(
                            type = ToolbarType.GONE,
                        )
                    )
                    bottomVisibility(true)
                }

                R.id.menuMore -> Unit
                else -> Unit
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) = parentFragmentManager.beginTransaction()
        .replace(R.id.fragmentHomeContainer, fragment)
        .commit()

    private fun initNavigation() {
        navController = parentFragmentManager.findFragmentById(
            R.id.fragmentHomeContainer
        )?.findNavController()
        destinationChangedListener()
    }

    private fun destinationChangedListener() = with(binding) {
        navController?.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {

                R.id.splashFragment,
                R.id.authenticationMainFragment,
                R.id.loginFragment,
                R.id.passwordFragment -> {
                    toolbarHomeMain.setProperties(
                        ToolbarProperties(
                            type = ToolbarType.GONE
                        )
                    )
                    bottomVisibility(false)
                }

                R.id.signupFragment -> {
                    toolbarHomeMain.setProperties(
                        ToolbarProperties(
                            type = ToolbarType.BACK_WITH_TITLE,
                            title = getString(R.string.tv_signup),
                        )
                    )
                    bottomVisibility(false)
                }

                R.id.campaignsListFragment -> {
                    toolbarHomeMain.apply {
                        isVisible = true
                        setProperties(
                            ToolbarProperties(
                                type = ToolbarType.BACK_WITH_TITLE,
                                title = getString(R.string.tv_campaigns),
                            )
                        )
                    }
                    bottomVisibility(false)
                }

                R.id.announcementsListFragment -> {
                    toolbarHomeMain.apply {
                        isVisible = true
                        setProperties(
                            ToolbarProperties(
                                type = ToolbarType.BACK_WITH_TITLE,
                                title = getString(R.string.tv_announcements),
                            )
                        )
                    }
                    bottomVisibility(false)
                }

                else -> {
                    toolbarHomeMain.setProperties(
                        ToolbarProperties(
                            type = ToolbarType.GONE
                        )
                    )
                    bottomVisibility(isVisible = true)
                }
            }
        }
    }

    private fun bottomVisibility(isVisible: Boolean) {
        binding.bottomNavigationView.isVisible = isVisible
    }
}