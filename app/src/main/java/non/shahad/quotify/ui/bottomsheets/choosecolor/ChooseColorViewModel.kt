package non.shahad.quotify.ui.bottomsheets.choosecolor

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import non.shahad.quotify.data.database.AppDatabase
import non.shahad.quotify.data.entities.ColorEntity
import non.shahad.quotify.repositories.ColorRepository

class ChooseColorViewModel (application: Application) : AndroidViewModel(application){

    private val colorList = MediatorLiveData<List<ColorEntity>>()
    private val colorRepo : ColorRepository = ColorRepository(application)

    init {
        getAllColor()
    }

    fun colorList() : LiveData<List<ColorEntity>>{
        return colorList
    }

    private fun getAllColor(){
        colorList.addSource(colorRepo.getAllBackgroundColor()){color ->
            colorList.postValue(color)
        }
    }



}