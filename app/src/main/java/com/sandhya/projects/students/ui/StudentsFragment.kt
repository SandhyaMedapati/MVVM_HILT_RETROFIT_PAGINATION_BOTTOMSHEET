package com.sandhya.projects.students.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sandhya.projects.databinding.FragmentStudentsBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class StudentsFragment : Fragment() {

    private val viewModel: StudentsViewModel by viewModels()

    private lateinit var binding: FragmentStudentsBinding

    @Inject
    lateinit var adapter: StudentsAdapter

    private var isLoading = false
    private var isLastPage = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentStudentsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.studentsRecyclerView.adapter = adapter
        binding.studentsRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.studentsList.observe(viewLifecycleOwner) {
            adapter.setStudents(it)
            isLastPage = it.size % viewModel.pageSize != 0
            updateStudentsProgressBarVisibility()
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            this.isLoading = isLoading
            updateStudentsProgressBarVisibility()
        }

        viewModel.fetchInitialStudents()
        setupStudentsRecyclerViewScrollListener()

        adapter.onItemClick = { studentData ->
            val bottomSheetFragment = StudentsBottomSheetFragment()
            val bundle = Bundle()
            bundle.putParcelable("studentsData", studentData)
            bottomSheetFragment.arguments = bundle
            bottomSheetFragment.show(childFragmentManager, bottomSheetFragment.tag)
        }
    }

    private fun updateStudentsProgressBarVisibility() {
        val progressBarVisibility = if (isLoading && !isLastPage) View.VISIBLE else View.GONE
        binding.studentsProgressBar.visibility = progressBarVisibility
    }

    private fun setupStudentsRecyclerViewScrollListener() {
        binding.studentsRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if (!viewModel.isLoading.value!! && visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0) {
                    viewModel.fetchRemainingStudents()
                }
            }
        })
    }
}