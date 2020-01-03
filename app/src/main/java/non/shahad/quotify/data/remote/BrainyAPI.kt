package non.shahad.quotify.data.remote

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface BrainyAPI {
    @GET("topics/{name}")
    suspend fun findByTopics(@Path("name") name : String) : ResponseBody

    @GET("topics")
    suspend fun findTopics() : ResponseBody
}