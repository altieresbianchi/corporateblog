package br.com.phaneronsoft.corpblog

import android.app.Application
import br.com.phaneronsoft.corpblog.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        this.initializeKoin();
    }

    private fun initializeKoin() {
        startKoin {
            androidContext(this@MainApplication)

            // Use AndroidLogger as Koin Logger - default Level.INFO
            androidLogger(Level.ERROR)

            modules(
                listOf(
                    coroutineModule,
                    LocalDataStorageModule,
                    userRepositoryModule,
                    postRepositoryModule,
                    newsRepositoryModule,
                    splashViewModelModule,
                    loginViewModelModule,
                    signUpViewModelModule,
                    mainViewModelModule,
                    postViewModelModule,
                    newsViewModelModule,
                )
            )
        }
    }
}