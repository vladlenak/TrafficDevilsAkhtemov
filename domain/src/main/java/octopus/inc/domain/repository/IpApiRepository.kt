package octopus.inc.domain.repository

import octopus.inc.domain.common.base.ApiResult
import octopus.inc.domain.model.DnsResponse
import octopus.inc.domain.model.JsonByQueryResponse

interface IpApiRepository {
    suspend fun getDnsResponse(): ApiResult<DnsResponse>
    suspend fun getJsonByQueryResponse(query: String) : ApiResult<JsonByQueryResponse>
}