package com.sandhya.projects.staff.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sandhya.projects.BottomSheetFragment
import com.sandhya.projects.databinding.FragmentStaffBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class StaffFragment: Fragment() {

    private val viewModel: StaffViewModel by viewModels()

    private lateinit var binding: FragmentStaffBinding
    @Inject
    lateinit var adapter: StaffAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStaffBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.staffList.observe(viewLifecycleOwner) {
            adapter.setStaff(it)
        }

        viewModel.fetchAllStaff()

        adapter.onItemClick = { staffData ->
            val bottomSheetFragment = BottomSheetFragment()
            val bundle = Bundle()
            bundle.putParcelable("staffData", staffData)
            bottomSheetFragment.arguments = bundle
            bottomSheetFragment.show(childFragmentManager, bottomSheetFragment.tag)
        }
    }
}
