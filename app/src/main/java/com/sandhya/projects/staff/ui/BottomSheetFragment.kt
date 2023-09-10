package com.sandhya.projects.staff.ui

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

        characterData?.let {
            binding.valueName.text = it.name
            binding.valueGender.text = it.gender
            binding.valueSpecies.text = it.species
            binding.valueHouse.text = it.house
            binding.valueWizard.text = it.wizard.toString()
            binding.valueEyeColor.text = it.eyeColour
            binding.valueHairColor.text = it.hairColour
        }
    }
}
