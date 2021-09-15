package geekbrains.ru.repository

import geekbrains.ru.model.data.AppState
import geekbrains.ru.model.data.DataModel
import geekbrains.ru.model.room.HistoryEntity

fun mapHistoryEntityToSearchResult(list: List<HistoryEntity>): List<DataModel> {
    val searchResult = ArrayList<DataModel>()
    if (!list.isNullOrEmpty()) {
        for (entity in list) {
            searchResult.add(DataModel(entity.word, null))
        }
    }
    return searchResult
}

fun convertDataModelSuccessToEntity(appState: AppState): HistoryEntity? {
    return when (appState) {
        is AppState.Success -> {
            val searchResult = appState.data
            if (searchResult.isNullOrEmpty() || searchResult[0].text.isNullOrEmpty()) {
                null
            } else {
                HistoryEntity(searchResult[0].text!!, null)
            }
        }
        else -> null
    }
}

//
//fun convertMeaningsToString(meanings: List<Meanings>): String {
//    var meaningsSeparatedByComma = String()
//    for ((index, meaning) in meanings.withIndex()) {
//        meaningsSeparatedByComma += if (index + 1 != meanings.size) {
//            String.format("%s%s", meaning.translation?.translation, ", ")
//        } else {
//            meaning.translation?.translation
//        }
//    }
//    return meaningsSeparatedByComma
//}
