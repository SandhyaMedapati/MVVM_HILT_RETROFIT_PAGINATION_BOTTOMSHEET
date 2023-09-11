package com.sandhya.projects.characters.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sandhya.projects.databinding.FragmentCharactersBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CharactersFragment : Fragment() {

    private val viewModel: CharactersViewModel by viewModels()

    private lateinit var binding: FragmentCharactersBinding

    @Inject
    lateinit var adapter: CharactersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharactersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.charactersRecyclerView.adapter = adapter
        binding.charactersRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.charactersList.observe(viewLifecycleOwner) {
            adapter.setCharacters(it)
        }

        viewModel.fetchAllCharacters()

        adapter.onItemClick = { charactersData ->
            val bottomSheetFragment = CharactersBottomSheetFragment()
            val bundle = Bundle()
            bundle.putParcelable("charactersData", charactersData)
            bottomSheetFragment.arguments = bundle
            bottomSheetFragment.show(childFragmentManager, bottomSheetFragment.tag)
        }
    }
}