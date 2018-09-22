package co.ruizhang.ruisbento

import android.app.Application
import co.ruizhang.ruisbento.presentation.presentationModule
import co.ruizhang.ruisbento.data.api.github.dataModule
import org.koin.android.ext.android.startKoin

class BentoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin(this, modules = listOf(dataModule, presentationModule))
    }
}