package br.com.pu.pu_challenge

import android.app.Application
import br.com.pu.pu_challenge.di.appModule
import br.com.pu.pu_challenge.di.dataModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PUApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val moduleList = listOf(appModule, dataModule)
        startKoin {
            androidContext(this@PUApplication)
            modules(moduleList)
        }
    }
}