package com.gmail.newstestingapp.model

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavouritesRepository @Inject constructor(
    private val newsService: NewsService
) {

    fun getPagedNews(): Flow<PagingData<NewsResponseItem>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = NETWORK_PAGE_SIZE),
            pagingSourceFactory = { NewsPagingSource(newsService) }
        ).flow
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 5
    }
}