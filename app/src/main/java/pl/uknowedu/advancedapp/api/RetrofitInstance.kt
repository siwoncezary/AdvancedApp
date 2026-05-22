package pl.uknowedu.advancedapp.api

import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitInstance {
    companion object {
        val BASE_URL = "https://jsonplaceholder.typicode.com/"
        fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(
                    MoshiConverterFactory
                        .create(Moshi.Builder().build())
                )
                .build()
        }
    }
}