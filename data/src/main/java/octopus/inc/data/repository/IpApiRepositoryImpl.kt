package octopus.inc.data.repository

import octopus.inc.data.api.ApiService
import octopus.inc.domain.common.base.ApiError
import octopus.inc.domain.common.base.ApiException
import octopus.inc.domain.common.base.ApiResult
import octopus.inc.domain.common.base.ApiSuccess
import octopus.inc.domain.model.DnsResponse
import octopus.inc.domain.model.JsonByQueryResponse
import octopus.inc.domain.repository.IpApiRepository
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject

class IpApiRepositoryImpl @Inject constructor(
    private val dnsApi: ApiService,
): IpApiRepository {

    override suspend fun getDnsResponse(): ApiResult<DnsResponse> {
        return handleApi { dnsApi.getDnsResponse() }
    }

    override suspend fun getJsonByQueryResponse(query: String): ApiResult<JsonByQueryResponse> {
        return handleApi { dnsApi.getJsonResponseById(query) }
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