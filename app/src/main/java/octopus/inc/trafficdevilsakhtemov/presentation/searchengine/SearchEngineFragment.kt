package octopus.inc.trafficdevilsakhtemov.presentation.searchengine

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import octopus.inc.trafficdevilsakhtemov.databinding.FragmentSearchEngineBinding

class SearchEngineFragment : Fragment() {

    private lateinit var binding: FragmentSearchEngineBinding
    private val viewModel: SearchEngineViewModel by viewModels()

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchEngineBinding.inflate(inflater, container, false)

        viewModel.searchEngine.observe(viewLifecycleOwner) {
            binding.searchEngineWebView.apply {
                settings.javaScriptEnabled = true
                loadUrl(it)
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // TODO передать сюда аргумент country
        val args = arguments
        val country = args?.getString("country")
        viewModel.send(SetSearchEngineEvent(country ?: ""))
    }
}