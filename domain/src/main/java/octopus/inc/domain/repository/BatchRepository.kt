package octopus.inc.domain.repository

import octopus.inc.domain.common.base.ApiResult
import octopus.inc.domain.model.DnsResponse

interface BatchRepository {
    suspend fun getDnsResponse(): ApiResult<DnsResponse>
}