package non.shahad.quotify.dagger.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import non.shahad.quotify.ui.bottomsheets.choosecolor.ChooseColorBottomFragment
import non.shahad.quotify.ui.explore.ExploreFragment
import non.shahad.quotify.ui.home.HomeFragment

@Module
abstract class MainFragmentBindingModule {

    @ContributesAndroidInjector
    abstract fun provideHomeFragment() : HomeFragment

    @ContributesAndroidInjector
    abstract fun provideExploreFragment() : ExploreFragment

    @ContributesAndroidInjector
    abstract fun provideChooseColorBottomFragment() : ChooseColorBottomFragment

}