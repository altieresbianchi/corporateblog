package br.com.phaneronsoft.corpblog.business.repository

import br.com.phaneronsoft.corpblog.business.api.NewsApiClient
import br.com.phaneronsoft.corpblog.business.vo.NewsResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response

class NewsApiRepository(
    private val newsApiClient: NewsApiClient
) : NewsRepositoryContract {

    override fun fetchNewsListAsync(): Deferred<Response<NewsResponse>> {
        try {
            val params = HashMap<String, String>()
            params["Content-Type"] = "application/json"

            return newsApiClient.fetchNewsListAsync(params)

        } catch (e: Exception) {
            throw e
        }
    }
}
