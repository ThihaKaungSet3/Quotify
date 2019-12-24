package non.shahad.quotify.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import non.shahad.quotify.data.local.daos.BackgroundColorDao
import non.shahad.quotify.data.local.entities.ColorEntity

@Database(entities = [(ColorEntity::class)],version = 2,exportSchema = false)
abstract class AppDatabase : RoomDatabase(){

    abstract fun colorDao() : BackgroundColorDao

}