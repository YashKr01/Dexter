package com.example.dexter.fragments

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.util.Log
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
import com.example.dexter.listeners.JobItemClickListener
import com.example.dexter.model.JobEntity
import com.example.dexter.utils.NetworkUtils
import com.example.dexter.viewmodels.JobViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), JobItemClickListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<JobViewModel>()

    private var visible = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("TAG", "onCreateView: ")
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

        viewModel.getJobList()

        // initialise adapter and recycler view
        val jobAdapter = JobAdapter(requireContext(), ArrayList(), this, ArrayList())

        viewModel.getSavedJobList().observe(viewLifecycleOwner) {
            jobAdapter.databaseList = it as ArrayList<JobEntity>
        }

        binding.jobRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = jobAdapter
        }

        viewModel.jobList.observe(viewLifecycleOwner) { jobAdapter.submitList(it) }

        binding.floatingActionButton
            .setOnClickListener { navigateToSavedJobsFragment() }

    }

    private fun navigateToSavedJobsFragment() = findNavController()
        .navigate(R.id.action_homeFragment_to_savedFragment)

    override fun onCheckboxCheckedListener(jobEntity: JobEntity) {
        viewModel.insertJob(jobEntity)
    }

    override fun onCheckboxUncheckedListener(jobEntity: JobEntity) {

    }

    override fun onJobItemClick(jobEntity: JobEntity) {

    }

    // if connection is available
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

    // if connection is not available
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}