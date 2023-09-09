package com.sandhya.projects

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sandhya.projects.databinding.FragmentBottomSheetBinding
import com.sandhya.projects.staff.data.StaffData

class BottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val characterData: StaffData? = arguments?.getParcelable("staffData")

        // Check if characterData is not null and display character information
        characterData?.let {
            binding.valueName.text = it.name
            binding.valueSpecies.text = it.species
            //binding.tvDateOfBirth.text = it.dateOfBirth
        }
    }
}
