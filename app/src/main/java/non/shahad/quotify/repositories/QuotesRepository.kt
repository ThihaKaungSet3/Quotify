package non.shahad.quotify.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import non.shahad.quotify.data.remote.QuotesAPI
import non.shahad.quotify.datamodels.models
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuotesRepository
    constructor(val quotesAPI : QuotesAPI) {
    val quotesOD  = MutableLiveData<models.QODResponse>()

    suspend fun quotesOfTheDay() = quotesAPI.QOD()

//    fun qOD() {
//
//
//        quotesAPI.QOD().enqueue(object : Callback<models.QODResponse>{
//            override fun onResponse(
//                call: Call<models.QODResponse>,
//                response: Response<models.QODResponse>
//            ) {
//                Log.d("Wellplayed"," response: ${response.body()}")
//            }
//
//            override fun onFailure(call: Call<models.QODResponse>, t: Throwable) {
//                Log.d("Wellplayed","error: ${t.message}")
//            }
//        })
//
//
//    }
}