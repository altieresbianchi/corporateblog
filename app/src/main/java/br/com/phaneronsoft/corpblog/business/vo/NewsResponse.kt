package br.com.phaneronsoft.corpblog.business.vo

import java.io.Serializable

data class NewsResponse(
    val news: List<NewsVO>
) : Serializable