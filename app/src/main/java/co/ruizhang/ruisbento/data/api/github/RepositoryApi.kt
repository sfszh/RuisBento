package co.ruizhang.ruisbento.data.api.github

import co.ruizhang.ruisbento.data.api.github.RepositoryApi.Companion.BASE_URL
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import kotlinx.coroutines.experimental.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RepositoryApi {

    @GET("/orgs/octokit/repos")
    fun loadRepo(): Deferred<List<Repository>>

    companion object {
        const val BASE_URL = "https://api.github.com/"
    }
}


//interface SuspendingApiClient {
//
//    suspend fun login(auth: Authorization) : GithubUser
//    suspend fun getRepositories(reposUrl: String, auth: Authorization) : List<GithubRepository>
//    suspend fun searchRepositories(searchQuery: String) : List<GithubRepository>
//
//}







