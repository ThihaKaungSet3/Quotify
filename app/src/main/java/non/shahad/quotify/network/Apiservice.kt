package non.shahad.quotify.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Apiservice {

        private const val BASEURL = "https://a8b4465b-8df2-48f1-9ea5-c34dd7111300.mock.pstmn.io/"

        fun create() : QuotesAPI = Retrofit.Builder()
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .baseUrl(BASEURL)
                                    .build()
                                    .create(QuotesAPI::class.java)


}