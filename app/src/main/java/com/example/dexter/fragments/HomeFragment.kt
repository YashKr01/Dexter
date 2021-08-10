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

    private var visible = false

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

        visible = true

        binding.textNetworkStatus.apply {
            text = context.getString(R.string.connected)
            setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_connected, 0, 0, 0)
        }

        binding.containerNetworkStatus.apply {
            setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.colorGreenDark))
            animate()
                .alpha(1f)
                .setStartDelay(1500L)
                .setDuration(1500L)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?) {
                        if(visible) visibility = View.GONE
                    }
                }).start()
        }

    }

    private fun onConnectionUnavailable() {

        visible = false

        binding.textNetworkStatus.apply {
            text = context.getString(R.string.no_connection)
            setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_not_connected, 0, 0, 0)
        }

        binding.containerNetworkStatus.apply {
            visibility = View.VISIBLE
            setBackgroundColor(context.getColor(R.color.colorRed))
        }

    }

}