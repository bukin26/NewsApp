package com.gmail.newstestingapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.gmail.newstestingapp.model.NewsResponseItem
import com.gmail.newstestingapp.model.StoriesRepository
import com.gmail.newstestingapp.model.VideoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
@ExperimentalCoroutinesApi
class VideoViewModel  @Inject constructor(
    private val videoRepository: VideoRepository
) : ViewModel() {

    val newsFlow: Flow<PagingData<NewsResponseItem>> =
        videoRepository.getPagedNews().map { pagingData ->
            pagingData.filter { newsResponseItem ->
                newsResponseItem.type == "video"
            }
        }.cachedIn(viewModelScope)
}