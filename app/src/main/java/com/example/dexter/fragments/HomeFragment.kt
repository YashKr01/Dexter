package com.example.dexter.fragments

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.dexter.R
import com.example.dexter.databinding.FragmentHomeBinding
import com.example.dexter.utils.NetworkUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        NetworkUtils.observeConnectivity(requireContext())
            .observe(viewLifecycleOwner) { isConnected ->
                if (isConnected) onConnectionAvailable()
                else onConnectionUnavailable()
            }

    }

    private fun onConnectionAvailable() {

        binding.textNetworkStatus.apply {
            text = "Connected"
            setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_connected, 0, 0, 0)
        }

        binding.containerNetworkStatus.apply {
            setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.colorGreenDark))
            animate()
                .alpha(1f)
                .setStartDelay(1000L)
                .setDuration(1000L)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?) {
                        visibility = View.GONE
                    }
                }).start()
        }

    }

    private fun onConnectionUnavailable() {

        binding.textNetworkStatus.apply {
            text = "No Connection"
            setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_not_connected, 0, 0, 0)
        }

        binding.containerNetworkStatus.apply {
            visibility = View.VISIBLE
            setBackgroundColor(context.getColor(R.color.colorRed))
        }

    }

}