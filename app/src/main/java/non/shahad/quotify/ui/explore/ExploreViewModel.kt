package non.shahad.quotify.ui.explore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import non.shahad.quotify.datamodels.models
import non.shahad.quotify.repositories.QuotesRepository
import org.jsoup.Jsoup
import javax.inject.Inject

class ExploreViewModel @Inject
    constructor(quotesRepository: QuotesRepository) : ViewModel() {

    val topics = liveData(Dispatchers.IO) {
            val topicsList : MutableList<models.Topics> = ArrayList()
            val response = quotesRepository.topics()
            val document = Jsoup.parse(response.string())
            val elements = document.getElementsByClass("topicContentName")

            for (x in 0 until elements.size){
                topicsList.add(models.Topics(elements[x].text()))
            }

        emit(topicsList)
    }
}