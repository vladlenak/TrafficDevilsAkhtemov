package octopus.inc.domain.usecases

import octopus.inc.domain.common.base.ApiResult
import octopus.inc.domain.model.DnsResponse
import octopus.inc.domain.repository.IpApiRepository

class GetDnsResponseUseCase(private val batchRepository: IpApiRepository) {
    suspend fun execute(): ApiResult<DnsResponse> {
        return batchRepository.getDnsResponse()
    }
}