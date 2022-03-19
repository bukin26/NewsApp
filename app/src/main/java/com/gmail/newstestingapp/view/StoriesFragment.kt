package com.gmail.newstestingapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.gmail.newstestingapp.R
import com.gmail.newstestingapp.databinding.FragmentStoriesBinding
import com.gmail.newstestingapp.viewmodel.StoriesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
@ExperimentalCoroutinesApi
class StoriesFragment : Fragment(R.layout.fragment_stories) {
    private lateinit var binding: FragmentStoriesBinding
    private val viewModel: StoriesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentStoriesBinding.inflate(inflater, container, false)

        setUpNews()

        return binding.root
    }

    private fun setUpNews() {
        val adapter = NewsAdapter()
        binding.storiesRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.storiesRecyclerView.adapter = adapter
        (binding.storiesRecyclerView.itemAnimator as? DefaultItemAnimator)?.supportsChangeAnimations =
            false

        observeUsers(adapter)
    }

    private fun observeUsers(adapter: NewsAdapter) {
        lifecycleScope.launch {
            viewModel.newsFlow.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }
}