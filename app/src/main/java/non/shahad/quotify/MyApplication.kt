package non.shahad.quotify

import android.app.Application
import non.shahad.quotify.di.networkModule
import non.shahad.quotify.di.persistenceModule
import non.shahad.quotify.di.repositoryModule
import non.shahad.quotify.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class MyApplication : Application(){

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            modules(networkModule)
            modules(persistenceModule)
            modules(viewModelModule)
            modules(repositoryModule)
        }

    }


}