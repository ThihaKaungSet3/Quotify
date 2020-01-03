package non.shahad.quotify.dagger.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import non.shahad.quotify.dagger.ViewModelKey
import non.shahad.quotify.dagger.ViewmodelFactory
import non.shahad.quotify.ui.bottomsheets.choosecolor.ChooseColorViewModel
import non.shahad.quotify.ui.bottomsheets.choosefont.ChooseFontBottomFragment
import non.shahad.quotify.ui.explore.ExploreViewModel
import non.shahad.quotify.ui.home.HomeViewModel

@Module
internal abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(value = HomeViewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: HomeViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(value = ExploreViewModel::class)
    abstract fun bindExploreViewModel(exploreViewModel : ExploreViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(value = ChooseColorViewModel::class)
    abstract fun bindChooseColorViewModel(chooseColorViewModel: ChooseColorViewModel) : ViewModel


    @Binds
    abstract fun bindViewModelFactory(factory : ViewmodelFactory) : ViewModelProvider.Factory
}