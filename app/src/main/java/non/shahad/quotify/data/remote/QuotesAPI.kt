package non.shahad.quotify.data.remote

import non.shahad.quotify.datamodels.models
import retrofit2.http.GET

interface QuotesAPI {
    @GET("qod")
    suspend fun QOD() : models.QODResponse
}