package com.app.coffeeapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.app.coffeeapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        destinationChangedListener()
    }

    private fun destinationChangedListener() = with(binding) {
        val navController = supportFragmentManager.findFragmentById(
            R.id.nav_host_fragment
        )?.findNavController()

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
                }
            }
        }
    }

    private fun bottomVisibility(isVisible: Boolean) {
        binding.bottomNavigationView.isVisible = isVisible
    }
}