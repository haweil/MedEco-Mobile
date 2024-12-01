package com.mhss.app.shifak.app

import android.app.Application
import com.mhss.app.shifak.data.di.DataModule
import com.mhss.app.shifak.domain.di.DomainModule
import com.mhss.app.shifak.presentation.di.PresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module

class ShifakApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ShifakApplication)
            androidLogger()
            modules(
                DataModule().module,
                PresentationModule().module,
                DomainModule().module
            )
        }
    }
}