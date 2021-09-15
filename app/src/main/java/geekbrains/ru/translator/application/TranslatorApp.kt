package geekbrains.ru.translator.application

import android.app.Application
import geekbrains.ru.translator.di.application
import geekbrains.ru.translator.di.historyScreen
import geekbrains.ru.translator.di.mainScreen
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DictionaryL6 : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(listOf(application, mainScreen, historyScreen))
        }
    }
}
