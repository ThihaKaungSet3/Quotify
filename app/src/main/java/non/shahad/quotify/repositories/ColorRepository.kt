package non.shahad.quotify.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import non.shahad.quotify.data.database.AppDatabase
import non.shahad.quotify.data.entities.ColorEntity

/**
 * Repository to fetch stored Color value from local db
 */
class ColorRepository(application: Application) {
    private val database : AppDatabase = AppDatabase.getInstance(application)

    fun getAllBackgroundColor() : LiveData<List<ColorEntity>>{
        return database.colorDao().getAllBackgroundColors()
    }

    fun findColorById(id : Long) : LiveData<ColorEntity>{
        return database.colorDao().findColorById(id)
    }
}