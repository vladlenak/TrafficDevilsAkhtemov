package octopus.inc.trafficdevilsakhtemov.presentation.ipapi

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import octopus.inc.domain.common.base.ApiError
import octopus.inc.domain.common.base.ApiException
import octopus.inc.domain.common.base.ApiSuccess
import octopus.inc.trafficdevilsakhtemov.databinding.FragmentIpApiBinding

@AndroidEntryPoint
class IpApiFragment: Fragment() {

    companion object {
        private const val TAG = "IpApiFragment"
    }

    private lateinit var binding: FragmentIpApiBinding
    private val viewModel: IpApiViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIpApiBinding.inflate(inflater, container, false)

        viewModel.dnsResponse.observe(viewLifecycleOwner) {
            when(it) {
                is ApiSuccess -> {
                    val dnsResponse = it.data
                    val geo = dnsResponse.dns.geo
                    val ip = dnsResponse.dns.ip
                    val result = "Geo: $geo\n\nIp: $ip"
                    binding.batchResponseTv.text = result

                    Log.d(TAG, "IP is: $ip")
                    viewModel.send(SetJsonByQueryResponseEvent(ip))
                }
                is ApiError -> {
                    binding.batchResponseTv.text = "Error code: ${it.code}. Error message: ${it.message}"
                    Log.d(TAG, "Error code: ${it.code}. Error message: ${it.message}")
                }
                is ApiException -> {
                    binding.batchResponseTv.text = "Exception: ${it.e}"
                    Log.d(TAG, "Exception: ${it.e}")
                }
            }
            binding.batchResponseTv.text
        }

        viewModel.jsonResponse.observe(viewLifecycleOwner) {
            when(it) {
                is ApiSuccess -> {
                    Log.d(TAG, "Json as: ${it.data.`as`}")
                    Log.d(TAG, "Json city: ${it.data.city}")
                    Log.d(TAG, "Json country: ${it.data.country}")
                    Log.d(TAG, "Json countryCode: ${it.data.countryCode}")
                    Log.d(TAG, "Json isp: ${it.data.isp}")
                    Log.d(TAG, "Json lat: ${it.data.lat}")
                    Log.d(TAG, "Json lon: ${it.data.lon}")
                    Log.d(TAG, "Json org: ${it.data.org}")
                    Log.d(TAG, "Json query: ${it.data.query}")
                    Log.d(TAG, "Json region: ${it.data.region}")
                    Log.d(TAG, "Json regionName: ${it.data.regionName}")
                    Log.d(TAG, "Json status: ${it.data.status}")
                    Log.d(TAG, "Json timezone: ${it.data.timezone}")
                    Log.d(TAG, "Json zip: ${it.data.zip}")
                    Log.d(TAG, "")

                    if (it.data.countryCode == "PL") {
                        val action = IpApiFragmentDirections.actionIpApiFragmentToSearchEngineFragment(it.data.country)
                        findNavController().navigate(action)
                    }
                }
                is ApiError -> {
                    Log.d(TAG, "Api Error Code: ${it.code}")
                    Log.d(TAG, "Api Error Message: ${it.message}")
                }
                is ApiException -> {
                    Log.d(TAG, "Api Exception: ${it.e}")
                }
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.send(SetDnsResponseEvent())
    }
}