package com.sandhya.projects.staff.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sandhya.projects.databinding.FragmentStaffBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class StaffFragment : Fragment() {

    private val viewModel: StaffViewModel by viewModels()

    private lateinit var binding: FragmentStaffBinding

    @Inject
    lateinit var adapter: StaffAdapter

    private var isLoading = false
    private var isLastPage = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentStaffBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.staffRecyclerView.adapter = adapter
        binding.staffRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.staffList.observe(viewLifecycleOwner) {
            adapter.setStaff(it)
            isLastPage = it.size % viewModel.pageSize != 0
            updateStaffProgressBarVisibility()
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            this.isLoading = isLoading
            updateStaffProgressBarVisibility()
        }

        viewModel.fetchInitialStaff()
        setupStaffRecyclerViewScrollListener()

        adapter.onItemClick = { staffData ->
            val bottomSheetFragment = BottomSheetFragment()
            val bundle = Bundle()
            bundle.putParcelable("staffData", staffData)
            bottomSheetFragment.arguments = bundle
            bottomSheetFragment.show(childFragmentManager, bottomSheetFragment.tag)
        }
    }

    private fun updateStaffProgressBarVisibility() {
        val progressBarVisibility = if (isLoading && !isLastPage) View.VISIBLE else View.GONE
        binding.staffProgressBar.visibility = progressBarVisibility
    }

    private fun setupStaffRecyclerViewScrollListener() {
        binding.staffRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if (!viewModel.isLoading.value!! && visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0) {
                    viewModel.fetchRemainingStaff()
                }
            }
        })
    }
}