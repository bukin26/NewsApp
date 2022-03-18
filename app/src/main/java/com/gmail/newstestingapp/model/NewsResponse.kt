package com.gmail.newstestingapp.model

import com.google.gson.annotations.SerializedName

data class NewsResponse(

	@field:SerializedName("NewsResponse")
	val newsResponse: List<NewsResponseItem>
)

data class NewsResponseItem(

	@field:SerializedName("top")
	val top: String,

	@field:SerializedName("time")
	val time: String,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("type")
	val type: String,

	@field:SerializedName("click_url")
	val clickUrl: String,

	@field:SerializedName("img")
	val img: String?
)
