package octopus.inc.trafficdevilsakhtemov.presentation.dns

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import octopus.inc.domain.common.base.ApiResult
import octopus.inc.domain.model.DnsResponse
import octopus.inc.domain.usecases.GetDnsResponseUseCase
import javax.inject.Inject

@HiltViewModel
class DnsViewModel @Inject constructor(
    private val getDnsResponseUseCase: GetDnsResponseUseCase
) : ViewModel() {

    private val _dnsResponse = MutableLiveData<ApiResult<DnsResponse>>()
    val dnsResponse: LiveData<ApiResult<DnsResponse>> = _dnsResponse

    fun send(event: DnsEvent) {
        when(event) {
            is SetDnsResponseEvent -> setBatchResponse()
        }
    }

    private fun setBatchResponse() = viewModelScope.launch {
        _dnsResponse.value = getDnsResponseUseCase.execute()
    }
}