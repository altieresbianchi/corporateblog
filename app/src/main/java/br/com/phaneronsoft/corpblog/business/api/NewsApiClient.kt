package br.com.phaneronsoft.corpblog.business.api

import br.com.phaneronsoft.corpblog.business.vo.NewsResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface NewsApiClient {
    @GET("data.json")
    fun fetchNewsListAsync(
        @QueryMap params: Map<String, String>
    ): Deferred<Response<NewsResponse>>
}