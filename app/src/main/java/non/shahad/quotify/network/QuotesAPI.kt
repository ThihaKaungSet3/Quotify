package non.shahad.quotify.network

import non.shahad.quotify.datamodels.models
import retrofit2.Call
import retrofit2.http.GET

interface QuotesAPI {
    @GET("qod")
    fun QOD() : Call<models.QODResponse>

}