package com.app.coffeeapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.app.coffeeapp.R
import com.app.coffeeapp.databinding.ActivityMainBinding
import com.app.coffeeapp.util.toolbar.ToolbarProperties
import com.app.coffeeapp.util.toolbar.ToolbarType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initNavigation()
        destinationChangedListener()
    }


    private fun initNavigation() {
        navController = supportFragmentManager.findFragmentById(
            R.id.nav_host_fragment
        )?.findNavController()

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            val currentDestination = navController?.currentDestination?.id
            
            when (item.itemId) {
                R.id.menuDashboard -> {
                    if (currentDestination != R.id.dashboardFragment) {
                        navController?.navigate(R.id.dashboardFragment)
                    }
                    true
                }
                R.id.menuFavorites -> {
                    if (currentDestination != R.id.favoritesFragment) {
                        navController?.navigate(R.id.favoritesFragment)
                    }
                    true
                }
                R.id.menuSelling -> {
                    if (currentDestination != R.id.sellingFragment) {
                        navController?.navigate(R.id.sellingFragment)
                    }
                    true
                }
                R.id.menuStore -> {
                    if (currentDestination != R.id.storesFragment) {
                        navController?.navigate(R.id.storesFragment)
                    }
                    true
                }
                R.id.menuMore -> {
                    // TODO: Diğer sayfası eklenecek
                    true
                }
                else -> false
            }
        }
    }

    private fun destinationChangedListener() = with(binding) {
        navController?.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {

                R.id.splashFragment,
                R.id.authenticationMainFragment,
                R.id.loginFragment,
                R.id.passwordFragment -> {
                    toolbar.setProperties(
                        ToolbarProperties(
                            type = ToolbarType.GONE
                        )
                    )
                    bottomVisibility(false)
                }

                R.id.signupFragment -> {
                    toolbar.setProperties(
                        ToolbarProperties(
                            type = ToolbarType.BACK_WITH_TITLE,
                            title = getString(R.string.tv_signup),
                        )
                    )
                    bottomVisibility(false)
                }

                R.id.dashboardFragment -> {
                    toolbar.setProperties(
                        ToolbarProperties(
                            type = ToolbarType.GONE,
                        )
                    )
                    bottomVisibility(true)
                    updateBottomNavigationSelection(destination.id)
                }

                R.id.favoritesFragment -> {
                    toolbar.setProperties(
                        ToolbarProperties(
                            type = ToolbarType.GONE,
                        )
                    )
                    bottomVisibility(true)
                    updateBottomNavigationSelection(destination.id)
                }

                R.id.sellingFragment -> {
                    toolbar.setProperties(
                        ToolbarProperties(
                            type = ToolbarType.GONE,
                        )
                    )
                    bottomVisibility(true)
                    updateBottomNavigationSelection(destination.id)
                }

                R.id.storesFragment -> {
                    toolbar.setProperties(
                        ToolbarProperties(
                            type = ToolbarType.GONE,
                        )
                    )
                    bottomVisibility(true)
                    updateBottomNavigationSelection(destination.id)
                }

                R.id.campaignsListFragment -> {
                    toolbar.setProperties(
                        ToolbarProperties(
                            type = ToolbarType.BACK_WITH_TITLE,
                            title = getString(R.string.tv_campaigns),
                        )
                    )
                    bottomVisibility(false)
                }

                R.id.announcementsListFragment -> {
                    toolbar.setProperties(
                        ToolbarProperties(
                            type = ToolbarType.BACK_WITH_TITLE,
                            title = getString(R.string.tv_announcements),
                        )
                    )
                    bottomVisibility(false)
                }

                else -> {
                    toolbar.setProperties(
                        ToolbarProperties(
                            type = ToolbarType.GONE
                        )
                    )
                    bottomVisibility(isVisible = true)
                    updateBottomNavigationSelection(destination.id)
                }
            }
        }
    }

    private fun updateBottomNavigationSelection(destinationId: Int) {
        val menuItemId = when (destinationId) {
            R.id.dashboardFragment -> R.id.menuDashboard
            R.id.favoritesFragment -> R.id.menuFavorites
            R.id.sellingFragment -> R.id.menuSelling
            R.id.storesFragment -> R.id.menuStore
            R.id.menuMore -> R.id.menuMore
            else -> null
        }
        menuItemId?.let {
            binding.bottomNavigationView.selectedItemId = it
        }
    }

    private fun bottomVisibility(isVisible: Boolean) {
        binding.bottomNavigationView.isVisible = isVisible
    }
}