package com.sandhya.projects.students.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sandhya.projects.databinding.FragmentStudentsBottomSheetBinding
import com.sandhya.projects.students.data.StudentsData

class StudentsBottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentStudentsBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStudentsBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val characterData: StudentsData? = arguments?.getParcelable("studentsData")

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
