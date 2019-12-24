package non.shahad.quotify.ui.bottomsheets.choosecolor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import non.shahad.quotify.data.local.entities.ColorEntity
import non.shahad.quotify.repositories.ColorRepository

class ChooseColorViewModel
constructor( val colorRepository: ColorRepository
            ) : ViewModel(){

    private val colorList = MediatorLiveData<List<ColorEntity>>()

    init {
        getAllColor()
    }

    fun colorList() : LiveData<List<ColorEntity>>{
        return colorList
    }

    private fun getAllColor(){
        colorList.addSource(colorRepository.getAllBackgroundColor()){color ->
            colorList.postValue(color)
        }
    }



}