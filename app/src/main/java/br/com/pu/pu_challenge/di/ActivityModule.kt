package br.com.pu.pu_challenge.di

import br.com.pu.pu_challenge.presentation.viewmodel.DealsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { DealsViewModel(get()) }
}
