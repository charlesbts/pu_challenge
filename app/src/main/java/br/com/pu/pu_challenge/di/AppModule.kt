package br.com.pu.pu_challenge.di

import br.com.pu.pu_challenge.data.network.PUApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

const val API_URL = "https://raw.githubusercontent.com/PeixeUrbano/desafio-android/master/api/"

val appModule = module {
    single { provideRetrofitApi() }
}

private fun provideRetrofitApi() : PUApi {
    val retrofit = Retrofit.Builder()
        .baseUrl(API_URL)
        .addConverterFactory(MoshiConverterFactory.create(createMoshi()))
        .build()

    return retrofit.create(PUApi::class.java)
}

private fun createMoshi() = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
