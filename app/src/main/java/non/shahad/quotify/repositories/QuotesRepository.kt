package non.shahad.quotify.repositories

import non.shahad.quotify.data.remote.BrainyAPI
import javax.inject.Inject

class QuotesRepository @Inject constructor(private val brainyAPI: BrainyAPI) {

    suspend fun topics() = brainyAPI.findTopics()

    suspend fun quotesByCategory(category : String) = brainyAPI.findByTopics(category)

}