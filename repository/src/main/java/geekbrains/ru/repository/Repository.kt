package geekbrains.ru.repository

interface Repository<T> {

    suspend fun getData(word: String): T
}
