package br.com.pu.pu_challenge.di

import br.com.pu.pu_challenge.data.DealRepositoryImpl
import org.koin.dsl.module

val dataModule = module {
    single { DealRepositoryImpl(get()) }
}