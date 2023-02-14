package octopus.inc.trafficdevilsakhtemov.presentation.ipapi

interface IpApiEvent

class SetDnsResponseEvent() : IpApiEvent
class SetJsonByQueryResponseEvent(val query: String) : IpApiEvent