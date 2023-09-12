package com.sandhya.projects.characters.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sandhya.projects.databinding.FragmentCharactersBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CharactersFragment : Fragment() {

    private val viewModel: CharactersViewModel by viewModels()

    private lateinit var binding: FragmentCharactersBinding

    @Inject
    lateinit var adapter: CharactersAdapter

    private var isLoading = false
    private var isLastPage = false

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
            isLastPage = it.size % viewModel.pageSize != 0
            updateCharactersProgressBarVisibility()
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            this.isLoading = isLoading
            updateCharactersProgressBarVisibility()
        }

        viewModel.fetchInitialCharacters()
        setupCharactersRecyclerViewScrollListener()

        adapter.onItemClick = { charactersData ->
            val bottomSheetFragment = CharactersBottomSheetFragment()
            val bundle = Bundle()
            bundle.putParcelable("charactersData", charactersData)
            bottomSheetFragment.arguments = bundle
            bottomSheetFragment.show(childFragmentManager, bottomSheetFragment.tag)
        }
    }

    private fun updateCharactersProgressBarVisibility() {
        val progressBarVisibility = if (isLoading && !isLastPage) View.VISIBLE else View.GONE
        binding.charactersProgressBar.visibility = progressBarVisibility
    }

    private fun setupCharactersRecyclerViewScrollListener() {
        binding.charactersRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if (!viewModel.isLoading.value!! && visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0) {
                    viewModel.fetchRemainingCharacters()
                }
            }
        })
    }
}