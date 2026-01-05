package com.app.coffeeapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.app.coffeeapp.R
import com.app.coffeeapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupNavigation()
        destinationChangedListener()
    }

    private fun setupNavigation() {
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
                R.id.menuProduct -> {
                    if (currentDestination != R.id.productsFragment) {
                        navController?.navigate(R.id.productsFragment)
                    }
                    true
                }
                R.id.menuSelling -> {
                    // TODO: Sipariş Ver sayfası eklenecek
                    true
                }
                R.id.menuStore -> {
                    // TODO: Mağazalar sayfası eklenecek
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
                R.id.splashFragment -> {
                    bottomVisibility(isVisible = false)
                }

                R.id.authenticationMainFragment, R.id.loginFragment, R.id.signupFragment, R.id.passwordFragment -> {
                    bottomVisibility(isVisible = false)
                }

                else -> {
                    bottomVisibility(isVisible = true)
                    updateBottomNavigationSelection(destination.id)
                }
            }
        }
    }

    private fun updateBottomNavigationSelection(destinationId: Int) {
        val menuItemId = when (destinationId) {
            R.id.dashboardFragment -> R.id.menuDashboard
            R.id.productsFragment -> R.id.menuProduct
            R.id.menuSelling -> R.id.menuSelling
            R.id.menuStore -> R.id.menuStore
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