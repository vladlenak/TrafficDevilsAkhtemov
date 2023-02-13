package octopus.inc.domain.usecases

import octopus.inc.domain.common.base.ApiResult
import octopus.inc.domain.model.BatchResponse
import octopus.inc.domain.repository.BatchRepository

class GetBatchResponseUseCase(private val batchRepository: BatchRepository) {
    suspend fun execute(): ApiResult<BatchResponse> {
        return batchRepository.getBatchResponse()
    }
}