package geekbrains.ru.translator.view.main

import geekbrains.ru.core.viewmodel.Interactor
import geekbrains.ru.model.data.AppState
import geekbrains.ru.model.data.DataModel
import geekbrains.ru.repository.Repository
import geekbrains.ru.repository.RepositoryLocal

class MainInteractor(
    private val repositoryRemote: Repository<List<DataModel>>,
    private val repositoryLocal: RepositoryLocal<List<DataModel>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        val appState: AppState
        if (fromRemoteSource) {
            appState = AppState.Success(repositoryRemote.getData(word))
            repositoryLocal.saveToDB(appState)
        } else {
            appState = AppState.Success(repositoryLocal.getData(word))
        }
        return appState
    }
}
