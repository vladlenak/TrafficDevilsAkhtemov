package octopus.inc.domain.usecases

import octopus.inc.domain.common.base.ApiResult
import octopus.inc.domain.model.JsonByQueryResponse
import octopus.inc.domain.repository.IpApiRepository

class GetJsonByQueryResponseUseCase(private val ipApiRepository: IpApiRepository) {
    suspend fun execute(query: String): ApiResult<JsonByQueryResponse> {
        return ipApiRepository.getJsonByQueryResponse(query)
    }
}