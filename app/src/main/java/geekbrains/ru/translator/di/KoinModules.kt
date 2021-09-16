package geekbrains.ru.translator.di

import androidx.room.Room
//import geekbrains.ru.history.view.history.HistoryInteractor
//import geekbrains.ru.history.view.history.HistoryViewModel
import geekbrains.ru.model.data.DataModel
import geekbrains.ru.model.room.HistoryDataBase
import geekbrains.ru.repository.*
import geekbrains.ru.translator.view.main.MainInteractor
import geekbrains.ru.translator.view.main.MainViewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

fun injectDependencies() = loadModules

private val loadModules by lazy {
    loadKoinModules(listOf(application, mainScreen))
}

val application = module {
    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDBL").build() }
    single { get<HistoryDataBase>().historyDao() }
    single<Repository<List<DataModel>>> { RepositoryImplementation(RetrofitImplementation()) }
    single<RepositoryLocal<List<DataModel>>> {
        RepositoryImplementationLocal(RoomDataBaseImplementation(get()))
    }
}

val mainScreen = module {
    factory { MainViewModel(get()) }
    factory { MainInteractor(get(), get()) }
}

