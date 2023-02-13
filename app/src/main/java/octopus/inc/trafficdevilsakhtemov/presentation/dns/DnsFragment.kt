package octopus.inc.trafficdevilsakhtemov.presentation.dns

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import octopus.inc.domain.common.base.ApiError
import octopus.inc.domain.common.base.ApiException
import octopus.inc.domain.common.base.ApiSuccess
import octopus.inc.trafficdevilsakhtemov.databinding.FragmentDnsBinding

@AndroidEntryPoint
class DnsFragment: Fragment() {

    companion object {
        private const val TAG = "DnsFragment"
    }

    private lateinit var binding: FragmentDnsBinding
    private val viewModel: DnsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDnsBinding.inflate(inflater, container, false)

        viewModel.dnsResponse.observe(viewLifecycleOwner) {
            when(it) {
                is ApiSuccess -> {
                    val dnsResponse = it.data
                    val geo = dnsResponse.dns.geo
                    val ip = dnsResponse.dns.ip
                    val result = "Geo: $geo\n\nIp: $ip"
                    binding.batchResponseTv.text = result
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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.send(SetDnsResponseEvent())
    }
}