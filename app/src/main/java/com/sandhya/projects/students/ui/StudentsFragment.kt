package com.sandhya.projects.students.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sandhya.projects.databinding.FragmentStudentsBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class StudentsFragment: Fragment() {

    private val viewModel: StudentsViewModel by viewModels()

    private lateinit var binding: FragmentStudentsBinding
    @Inject
    lateinit var adapter: StudentsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
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
        }

        viewModel.fetchAllStaff()
    }

}