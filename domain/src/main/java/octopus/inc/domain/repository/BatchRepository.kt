package octopus.inc.domain.repository

import octopus.inc.domain.model.BatchResponse
import octopus.inc.domain.common.base.ApiResult

interface BatchRepository {
    suspend fun getBatchResponse(): ApiResult<BatchResponse>
}