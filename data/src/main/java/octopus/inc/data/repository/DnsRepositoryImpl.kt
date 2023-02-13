package octopus.inc.data.repository

import octopus.inc.data.api.DnsApi
import octopus.inc.domain.common.base.ApiError
import octopus.inc.domain.common.base.ApiException
import octopus.inc.domain.common.base.ApiResult
import octopus.inc.domain.common.base.ApiSuccess
import octopus.inc.domain.model.DnsResponse
import octopus.inc.domain.repository.BatchRepository
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject

class DnsRepositoryImpl @Inject constructor(
    private val dnsApi: DnsApi,
): BatchRepository {

    override suspend fun getDnsResponse(): ApiResult<DnsResponse> {
        return handleApi { dnsApi.getDnsResponse() }
    }

    private suspend fun <T : Any> handleApi(
        execute: suspend () -> Response<T>
    ): ApiResult<T> {
        return try {
            val response = execute()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                ApiSuccess(body)
            } else {
                ApiError(code = response.code(), message = response.message())
            }
        } catch (e: HttpException) {
            ApiError(code = e.code(), message = e.message())
        } catch (e: Throwable) {
            ApiException(e)
        }
    }
}