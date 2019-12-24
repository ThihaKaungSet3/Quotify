package non.shahad.quotify.di

import non.shahad.quotify.repositories.ColorRepository
import non.shahad.quotify.repositories.QuotesRepository
import non.shahad.quotify.ui.bottomsheets.choosecolor.ChooseColorViewModel
import non.shahad.quotify.ui.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get<ColorRepository>(),get()) }
    viewModel { ChooseColorViewModel(get<ColorRepository>()) }
}