package geekbrains.ru.repository

interface DataSource<T> {

    suspend fun getData(word: String): T
}
