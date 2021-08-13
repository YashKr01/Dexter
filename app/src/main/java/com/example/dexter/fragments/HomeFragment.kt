package com.example.dexter.fragments

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dexter.R
import com.example.dexter.adapters.JobAdapter
import com.example.dexter.databinding.FragmentHomeBinding
import com.example.dexter.listeners.ItemClickListener
import com.example.dexter.model.JobEntity
import com.example.dexter.utils.NetworkUtils
import com.example.dexter.viewmodels.JobViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), ItemClickListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<JobViewModel>()

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

        // Observing Connectivity
        NetworkUtils.observeConnectivity(requireContext())
            .observe(viewLifecycleOwner) { isConnected ->
                if (isConnected) onConnectionAvailable()
                else onConnectionUnavailable()
            }

        val jobAdapter = JobAdapter(requireContext(), ArrayList(), this)
        binding.jobRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = jobAdapter
        }

        viewModel.getJobList()
        viewModel.observeList().observe(viewLifecycleOwner) { jobAdapter.submitList(it) }

        binding.floatingActionButton.setOnClickListener { findNavController().navigate(R.id.action_homeFragment_to_savedFragment) }

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
                .setStartDelay(3000L)
                .setDuration(3000L)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?) {
                        if (visible) visibility = View.GONE
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

    override fun onItemClicked(jobEntity: JobEntity) {
    }

    override fun onCheckboxClicked(jobEntity: JobEntity) {
    }

    override fun onDelete(jobEntity: JobEntity) {
    }

}