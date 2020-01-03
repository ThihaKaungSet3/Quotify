package non.shahad.quotify.dagger.components

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import non.shahad.quotify.dagger.modules.ActivityBuilder
import non.shahad.quotify.dagger.modules.AppModule
import non.shahad.quotify.dagger.modules.ViewModelModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    AppModule::class,
    ActivityBuilder::class,
    ViewModelModule::class
])
interface ApplicationComponent : AndroidInjector<DaggerApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application) : Builder

        fun build() : ApplicationComponent

    }


    override fun inject(application: DaggerApplication)
}