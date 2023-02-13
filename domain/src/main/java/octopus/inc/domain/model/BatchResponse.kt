package octopus.inc.domain.model

data class BatchResponse(
    val country: String,
    val countryCode: String,
    val city: String,
    val query: String,
    val status: String? = null,
    val region: String? = null,
    val regionName: String? = null,
    val zip: String? = null,
    val lat: Double? = null,
    val lon: Double? = null,
    val timezone: String? = null,
    val isp: String? = null,
    val org: String? = null,
    val welcomeAs: String? = null
)
