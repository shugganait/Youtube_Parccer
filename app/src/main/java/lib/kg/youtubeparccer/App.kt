package lib.kg.youtubeparccer

import android.app.Application
import lib.kg.youtubeparccer.di.koinModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@App)
            modules(koinModules)
        }
    }
}