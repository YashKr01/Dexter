package com.example.dexter.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dexter.adapters.SavedJobAdapter
import com.example.dexter.databinding.FragmentSavedBinding
import com.example.dexter.listeners.SavedJobItemClickListener
import com.example.dexter.model.JobEntity
import com.example.dexter.viewmodels.SavedJobViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SavedFragment : Fragment(), SavedJobItemClickListener {

    private var _binding: FragmentSavedBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<SavedJobViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSavedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val savedJobsAdapter = SavedJobAdapter(requireContext(), ArrayList(), this)

        binding.savedJobsRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = savedJobsAdapter
        }

        viewModel.getSavedJobs()
            .observe(viewLifecycleOwner) { list -> savedJobsAdapter.submitList(list) }

    }

    override fun onDeleteJob(jobEntity: JobEntity) {
        viewModel.deleteJob(jobEntity)
    }

    override fun onItemClickListener(jobEntity: JobEntity) {
    }


}