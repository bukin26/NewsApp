package com.gmail.newstestingapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.gmail.newstestingapp.model.NewsResponseItem
import com.gmail.newstestingapp.model.StoriesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
@ExperimentalCoroutinesApi
class StoriesViewModel @Inject constructor(
    private val storiesRepository: StoriesRepository
) : ViewModel() {

    val newsFlow: Flow<PagingData<NewsResponseItem>> =
        storiesRepository.getPagedNews().map { pagingData ->
            pagingData.filter { newsResponseItem ->
                newsResponseItem.type == "strories"
            }
        }.cachedIn(viewModelScope)
}