package non.shahad.quotify.ui.home

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import non.shahad.quotify.data.local.entities.ColorEntity
import non.shahad.quotify.datamodels.models
import non.shahad.quotify.repositories.ColorRepository
import non.shahad.quotify.repositories.QuotesRepository
import org.jsoup.Jsoup
import javax.inject.Inject


class HomeViewModel
    @Inject constructor(val colorRepo : ColorRepository,val quotesRepository: QuotesRepository) : ViewModel(){


    fun findColorById(id : Long) : LiveData<ColorEntity>{
        return colorRepo.findColorById(id)
    }


//    val topics = liveData(Dispatchers.IO) {
//        val topicsList : MutableList<String> = ArrayList()
//        val response = quotesRepository.topics()
//        val document = Jsoup.parse(response.string())
//        val elements = document.getElementsByClass("topicContentName")
//
//        for (x in 1 until elements.size){
//            topicsList.add(elements[x].text())
//        }
//
//        emit(topicsList)
//    }

    fun quotesByCategory(category: String) : LiveData<MutableList<models.Quotes>> =
        liveData(Dispatchers.IO) {

            val quotesList: MutableList<models.Quotes> = ArrayList()
            val quotesResponse = quotesRepository.quotesByCategory(category)
            val document = Jsoup.parse(quotesResponse.string())
            val quotesElements = document.getElementsByClass("clearfix")
            val tagElements = document.getElementsByClass("kw-box")

            for (x in 0 until quotesElements.size){
                val element = quotesElements.get(index = x)
                val tags = tagElements[x].select("a")
                val tagList : MutableList<String> = ArrayList()

                for (tag in tags){
                    tagList.add(tag.text())
                }

                quotesList.add(models.Quotes(element.child(0).text(),
                    element.child(1).text(),
                    tagList))

            }

            emit(quotesList)

        }
}