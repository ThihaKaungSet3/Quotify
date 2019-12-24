package non.shahad.quotify.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import non.shahad.quotify.data.remote.EndPoint
import non.shahad.quotify.data.remote.QuotesAPI
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single <Retrofit> { Retrofit.Builder()
        .baseUrl(EndPoint.URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
        .build() }

    single <QuotesAPI>{ get<Retrofit>().create(QuotesAPI::class.java)}
}

