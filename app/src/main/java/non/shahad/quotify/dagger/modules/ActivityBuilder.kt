package non.shahad.quotify.dagger.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import non.shahad.quotify.ui.MainActivity

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [MainFragmentBindingModule::class])
    abstract fun bindMainActivity() : MainActivity


}