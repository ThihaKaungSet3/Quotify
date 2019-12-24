package non.shahad.quotify.di

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import non.shahad.quotify.data.local.database.AppDatabase
import non.shahad.quotify.data.local.entities.ColorEntity
import non.shahad.quotify.executors.AppExecutor
import non.shahad.quotify.utils.SharedPreferencesHelper
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val persistenceModule = module {


    // Shared Preference
    single  { SharedPreferencesHelper(androidApplication()) }

    // App database
     single {
         Room.databaseBuilder(androidApplication(),AppDatabase::class.java,"quotify")
         .fallbackToDestructiveMigration()
         .addCallback(object  : RoomDatabase.Callback() {
             override fun onCreate(db: SupportSQLiteDatabase) {
                 runBlocking {
                     withContext(Dispatchers.IO){
                         for (color in ColorEntity.populatedColors()) {
                             get<AppDatabase>().colorDao().insert(color)
                         }
                     }
                 }

//                 get<AppExecutor>().diskIO().execute {
//
//                 }
             }
         }).build()
     }

    // Color Dao
    single { get<AppDatabase>().colorDao() }
}