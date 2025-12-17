package com.app.coffeeapp.ui.authentication.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.app.coffeeapp.R
import com.app.coffeeapp.databinding.FragmentSplashBinding
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!
    private val auth by lazy { FirebaseAuth.getInstance() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hideStatusBar()
        logoAnim()
        writeAnim()
        navigation()
    }

    private fun hideStatusBar() {
        requireActivity().window.apply {
            WindowCompat.setDecorFitsSystemWindows(this, false)
            WindowInsetsControllerCompat(this, decorView).let { controller ->
                controller.hide(WindowInsetsCompat.Type.statusBars() or WindowInsetsCompat.Type.navigationBars())
                controller.systemBarsBehavior =
                    WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        }
    }

    private fun logoAnim() = with(binding.imgLogo) {
        val logoAnim = AnimationUtils.loadAnimation(requireContext(), R.anim.logo_scale)
        startAnimation(logoAnim)
    }

    private fun writeAnim() = with(binding.txtAppName) {
        Handler(Looper.getMainLooper()).postDelayed({
            animate()
                .alpha(1f)
                .translationY(0f)
                .setDuration(800)
                .start()
        }, 600)

    }

    private fun navigation() {
        Handler(Looper.getMainLooper()).postDelayed({
            requireActivity().setTheme(R.style.Theme_CoffeeApp)

            val isLoggedIn = auth.currentUser != null //daha önce giriş yapılmış

            if (isLoggedIn) {
                findNavController().navigate(
                    R.id.action_splashFragment_to_home_nav_graph
                )
            } else {
                findNavController().navigate(
                    R.id.action_splashFragment_to_authenticationMainFragment
                )
            }
        }, 2000)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}