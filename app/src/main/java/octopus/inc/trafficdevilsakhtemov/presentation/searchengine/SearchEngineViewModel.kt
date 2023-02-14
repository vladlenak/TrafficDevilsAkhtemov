package octopus.inc.trafficdevilsakhtemov.presentation.searchengine

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchEngineViewModel : ViewModel() {

    private val _searchEngine = MutableLiveData<String>()
    val searchEngine: LiveData<String> = _searchEngine

    fun send(event: SearchEngineEvent) {
        when(event) {
            is SetSearchEngineEvent -> setSearchEngineEvent(event.country)
        }
    }

    private fun setSearchEngineEvent(country: String) {
        _searchEngine.value = "https://www.google.com/search?q=$country"
    }

}