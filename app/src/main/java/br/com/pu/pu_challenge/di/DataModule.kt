package br.com.pu.pu_challenge.di

import android.content.Context
import androidx.room.Room
import br.com.pu.pu_challenge.data.database.AppDatabase
import br.com.pu.pu_challenge.data.DealRepositoryImpl
import br.com.pu.pu_challenge.domain.repository.DealRepository
import br.com.pu.pu_challenge.domain.usecase.GetDealsUseCase
import org.koin.dsl.module

val dataModule = module {
    single<DealRepository> { DealRepositoryImpl(get(), get()) }
    single { provideAppDatabase(get())}
    single { GetDealsUseCase(get()) }
}

private fun provideAppDatabase(context: Context): AppDatabase =
    Room.databaseBuilder(context, AppDatabase::class.java, "pu-db").build()
