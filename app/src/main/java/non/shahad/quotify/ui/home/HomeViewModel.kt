package non.shahad.quotify.ui.home


import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import non.shahad.quotify.data.local.entities.ColorEntity
import non.shahad.quotify.datamodels.models
import non.shahad.quotify.repositories.ColorRepository
import non.shahad.quotify.repositories.QuotesRepository


class HomeViewModel
    constructor(val colorRepo : ColorRepository,val quotesRepository: QuotesRepository) : ViewModel(){
    val data : MutableLiveData<models.QODResponse> = MutableLiveData()



    fun findColorById(id : Long) : LiveData<ColorEntity>{
        return colorRepo.findColorById(id)
    }

//    fun qOD(){
//        quotesRepository.qOD()
//    }

    val result = liveData(Dispatchers.IO) {
       val response = quotesRepository.quotesOfTheDay()
        Log.d("Hola","$response")
        emit(response)
    }



}