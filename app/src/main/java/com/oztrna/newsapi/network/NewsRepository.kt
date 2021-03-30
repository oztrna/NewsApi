package com.oztrna.newsapi.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.oztrna.newsapi.database.AppDatabase
import com.oztrna.newsapi.model.Source
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsRepository(
    private val api: MyApi,
    private val db: AppDatabase
): SafeApiRequest() {

    /*
    private val quotes = MutableLiveData<List<Source>>()

    init {
        quotes.observeForever {
            saveNews(it)
        }
    }

    suspend fun getNews(): LiveData<List<Source>> {
        return withContext(Dispatchers.IO) {
            fetchQuotes()
            db.getSourceDao().getSource()
        }
    }

    private suspend fun fetchQuotes() {
        val response = apiRequest { api.getNews("besiktas", "1", "d54b713cc2fd47f4ba077861cf517268") }
        quotes.postValue(response.articles)
    }

    private fun saveNews(quotes: List<Source>) {
        Coroutines.io {
            db.getSourceDao().saveAllSource(quotes)
        }
    }

    fun news() = db.getSourceDao().getSource()
*/
}