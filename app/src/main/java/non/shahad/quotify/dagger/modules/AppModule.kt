package non.shahad.quotify.dagger.modules

import android.app.Application
import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import non.shahad.quotify.data.local.daos.BackgroundColorDao
import non.shahad.quotify.data.local.database.AppDatabase
import non.shahad.quotify.data.local.entities.ColorEntity
import non.shahad.quotify.data.remote.BrainyAPI
import non.shahad.quotify.data.remote.EndPoint
import non.shahad.quotify.executors.AppExecutor
import non.shahad.quotify.utils.SharedPreferencesHelper
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule{

    @Singleton
    @Provides
    fun provideAppExecutor() : AppExecutor = AppExecutor()

    @Singleton
    @Provides
    @Named("brainy")
    fun provideRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(EndPoint.BrainyURL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
            .build()
    }

    @Singleton
    @Provides
    fun provideBrainyAPI(@Named("brainy")retrofit: Retrofit) : BrainyAPI = retrofit.create(BrainyAPI::class.java)

    @Singleton
    @Provides
    fun provideSharedPrefHelper(application: Application) : SharedPreferencesHelper = SharedPreferencesHelper(application)


    @Singleton
    @Provides
    fun provideRoomDatabase(application: Application) : AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java,"quotify")
            .fallbackToDestructiveMigration()
            .build()
    }


    @Singleton
    @Provides
    fun providecolorDao(appDatabase: AppDatabase) : BackgroundColorDao {
        return appDatabase.colorDao()
    }

//    Room.databaseBuilder(androidApplication(),AppDatabase::class.java,"quotify")
//    .fallbackToDestructiveMigration()
//    .addCallback(object  : RoomDatabase.Callback() {
//        override fun onCreate(db: SupportSQLiteDatabase) {
//            val executor = AppExecutor()
////                 executor.diskIO().execute(Runnable {
////                     val colorDao = get<AppDatabase>().colorDao()
////
////                     ColorEntity.populatedColors().forEach{ colorEntity ->
////                         Log.d("AutumnSong","Starting")
////                         colorDao.insert(colorEntity)
////                         Log.d("AutumnSong","Done")
////                     }
////                 })
//            CoroutineScope(Dispatchers.IO).launch {
//                val colorDao = get<AppDatabase>().colorDao()
//
//                ColorEntity.populatedColors().forEach{ colorEntity ->
//                    Log.d("AutumnSong","Starting")
//                    colorDao.insert(colorEntity)
//                    Log.d("AutumnSong","Done")
//                }
//            }
//
//        }
//    }).build()

}