package non.shahad.quotify.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import non.shahad.quotify.datamodels.models
import non.shahad.quotify.network.Apiservice
import non.shahad.quotify.network.QuotesAPI

class QuotesRepository (application: Application){
    private val quotesAPI : QuotesAPI = Apiservice.create()
    private val quotesOD  = MutableLiveData<models.QODResponse>()

    fun QOD() : LiveData<models.QODResponse>{
        val call = quotesAPI.QOD()
        val result = call.execute().body()
        quotesOD.postValue(result)

        return quotesOD
    }
}