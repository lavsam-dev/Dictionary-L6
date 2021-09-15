package geekbrains.ru.core.viewmodel

interface Interactor<T> {

    suspend fun getData(word: String, fromRemoteSource: Boolean): T
}
