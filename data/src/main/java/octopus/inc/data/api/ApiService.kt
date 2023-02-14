package octopus.inc.data.api

import octopus.inc.data.utils.EndPoints
import octopus.inc.domain.model.DnsResponse
import octopus.inc.domain.model.JsonByQueryResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET(EndPoints.JSON_URL)
    suspend fun getDnsResponse(): Response<DnsResponse>

    @GET("http://ip-api.com/json/{query}")
    suspend fun getJsonResponseById(
        @Path("query") query: String
    ): Response<JsonByQueryResponse>
}