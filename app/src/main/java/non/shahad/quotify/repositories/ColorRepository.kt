package non.shahad.quotify.repositories

import androidx.lifecycle.LiveData
import non.shahad.quotify.data.local.database.AppDatabase
import non.shahad.quotify.data.local.entities.ColorEntity
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository to fetch stored Color value from local db
 */
@Singleton
class ColorRepository @Inject
constructor(val database: AppDatabase) {

    fun getAllBackgroundColor() : LiveData<List<ColorEntity>>{
        return database.colorDao().getAllBackgroundColors()
    }

    fun findColorById(id : Long) : LiveData<ColorEntity>{
        return database.colorDao().findColorById(id)
    }
}