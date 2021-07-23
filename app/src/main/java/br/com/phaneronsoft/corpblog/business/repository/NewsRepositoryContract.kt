package br.com.phaneronsoft.corpblog.business.repository

import br.com.phaneronsoft.corpblog.business.vo.NewsResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response

interface NewsRepositoryContract {
    fun fetchNewsListAsync(): Deferred<Response<NewsResponse>>
}