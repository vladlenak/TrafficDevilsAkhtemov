package octopus.inc.data.api

import octopus.inc.data.utils.EndPoints
import octopus.inc.domain.model.DnsResponse
import retrofit2.Response
import retrofit2.http.GET

interface DnsApi {
    @GET(EndPoints.DNS_URL)
    suspend fun getDnsResponse() : Response<DnsResponse>
}