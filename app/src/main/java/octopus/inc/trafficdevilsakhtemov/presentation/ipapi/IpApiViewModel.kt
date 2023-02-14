package octopus.inc.trafficdevilsakhtemov.presentation.ipapi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import octopus.inc.domain.common.base.ApiResult
import octopus.inc.domain.model.DnsResponse
import octopus.inc.domain.model.JsonByQueryResponse
import octopus.inc.domain.usecases.GetDnsResponseUseCase
import octopus.inc.domain.usecases.GetJsonByQueryResponseUseCase
import javax.inject.Inject

@HiltViewModel
class IpApiViewModel @Inject constructor(
    private val getDnsResponseUseCase: GetDnsResponseUseCase,
    private val getJsonByQueryResponseUseCase: GetJsonByQueryResponseUseCase
) : ViewModel() {

    private val _dnsResponse = MutableLiveData<ApiResult<DnsResponse>>()
    private val _jsonResponse = MutableLiveData<ApiResult<JsonByQueryResponse>>()
    val dnsResponse: LiveData<ApiResult<DnsResponse>> = _dnsResponse
    val jsonResponse: LiveData<ApiResult<JsonByQueryResponse>> = _jsonResponse

    fun send(event: IpApiEvent) {
        when (event) {
            is SetDnsResponseEvent -> setDnsResponse()
            is SetJsonByQueryResponseEvent -> setJsonByQueryResponse(event.query)
        }
    }

    private fun setDnsResponse() = viewModelScope.launch {
        _dnsResponse.value = getDnsResponseUseCase.execute()
    }

    private fun setJsonByQueryResponse(query: String) = viewModelScope.launch {
        _jsonResponse.value = getJsonByQueryResponseUseCase.execute(query)
    }
}