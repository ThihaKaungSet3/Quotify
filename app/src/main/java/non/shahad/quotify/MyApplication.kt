package non.shahad.quotify


import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import non.shahad.quotify.dagger.components.DaggerApplicationComponent


class MyApplication : DaggerApplication(){

    private val component = DaggerApplicationComponent
                            .builder()
                            .application(this)
                            .build()

    override fun onCreate() {
        super.onCreate()

    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return component
    }


}