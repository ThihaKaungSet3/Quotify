package non.shahad.quotify.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import non.shahad.quotify.data.daos.BackgroundColorDao
import non.shahad.quotify.data.entities.ColorEntity
import non.shahad.quotify.executors.AppExecutor

@Database(entities = [(ColorEntity::class)],version = 2)
abstract class AppDatabase : RoomDatabase(){


    companion object{
        private val executor : AppExecutor by lazy { AppExecutor() }
        @Volatile
        private var INSTANCE : AppDatabase? = null

        fun getInstance(context : Context) : AppDatabase{
            return INSTANCE ?: synchronized(this){
                INSTANCE ?: createDatabase(context).also { INSTANCE = it }
            }
        }

        private fun createDatabase(context: Context) : AppDatabase =
            Room.databaseBuilder(context.applicationContext,AppDatabase::class.java,"quotify")
                .fallbackToDestructiveMigration()
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        executor.diskIO().execute{
                            for (color in ColorEntity.populatedColors()){
                                getInstance(context).colorDao().insert(color)
                            }
                        }
                    }
                }).build()
        }


    abstract fun colorDao() : BackgroundColorDao

}