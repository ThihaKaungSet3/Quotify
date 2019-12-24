package non.shahad.quotify.di

import non.shahad.quotify.data.local.database.AppDatabase
import non.shahad.quotify.data.remote.QuotesAPI
import non.shahad.quotify.repositories.ColorRepository
import non.shahad.quotify.repositories.QuotesRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory { ColorRepository(get<AppDatabase>()) }
    factory { QuotesRepository(get<QuotesAPI>()) }

}