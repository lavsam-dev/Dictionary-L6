package geekbrains.ru.repository

import geekbrains.ru.model.data.AppState
import geekbrains.ru.model.data.DataModel
import geekbrains.ru.model.room.HistoryDao

class RoomDataBaseImplementation(private val historyDao: HistoryDao) :
    DataSourceLocal<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        if (word.isNullOrEmpty()) {
            return mapHistoryEntityToSearchResult(historyDao.all())
        } else {
            return mapHistoryEntityToSearchResult(historyDao.getDataByWord(word))
        }
    }

    override suspend fun saveToDB(appState: AppState) {
        convertDataModelSuccessToEntity(appState)?.let {
            historyDao.insert(it)
        }
    }
}
