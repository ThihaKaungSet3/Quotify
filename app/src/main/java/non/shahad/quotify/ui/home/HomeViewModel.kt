package non.shahad.quotify.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import non.shahad.quotify.data.entities.ColorEntity
import non.shahad.quotify.datamodels.models
import non.shahad.quotify.repositories.ColorRepository
import non.shahad.quotify.repositories.QuotesRepository


class HomeViewModel(application: Application) : AndroidViewModel(application){
    private val colorRepo : ColorRepository = ColorRepository(application)
//    private val quotesRepo = QuotesRepository(application)

    fun findColorById(id : Long) : LiveData<ColorEntity>{
        return colorRepo.findColorById(id)
    }

//    fun getQOD() : LiveData<models.QODResponse>{
//        return quotesRepo.QOD()
//    }
}