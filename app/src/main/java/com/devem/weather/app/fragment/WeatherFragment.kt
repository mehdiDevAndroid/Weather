package com.devem.weather.app.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.devem.weather.app.adapter.WeatherAdapter
import com.devem.weather.app.view.WeatherView
import com.devem.weather.app.viewModel.WeatherViewModel
import com.devem.weather.databinding.FragmentWeatherBinding
import com.devem.weather.domain.retrofit.State
import com.devem.weather.utils.Extensions.logError
import com.devem.weather.utils.Extensions.logInfo
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class WeatherFragment : Fragment() {

    @Inject
    lateinit var weatherAdapter: WeatherAdapter

    private val viewModel: WeatherViewModel by viewModels()
    private lateinit var _binding: FragmentWeatherBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            initLiveData()
            initRecycleView()
            initRefreshLayout()

            viewModel.getWeather()
        }
    }

    private fun initLiveData() {
        viewModel.weatherLD.observe(viewLifecycleOwner) { handleWeather(it) }
    }

    private fun initRecycleView() {
        _binding.recyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 1)
            adapter = weatherAdapter
        }
    }

    private fun initRefreshLayout() {
        _binding.swipeRefreshLayout.setOnRefreshListener {
            weatherAdapter.apply {
                data.clear()
                notifyDataSetChanged()
            }
            viewModel.getWeather()
        }
    }


    private fun handleWeather(state: State<ArrayList<WeatherView.Data.Timelines?>>) {
        when (state) {
            is State.Success -> {
                handleSuccess(state.data)
            }

            is State.Error -> {
                _binding.swipeRefreshLayout.isRefreshing = false
                logError("Weather DATA: ${state.message}")
                Toast.makeText(requireContext(),state.message,Toast.LENGTH_LONG).show()
            }

            else -> {
            }
        }

    }

    private fun handleSuccess(response: ArrayList<WeatherView.Data.Timelines?>?) {
        _binding.swipeRefreshLayout.isRefreshing = false
        weatherAdapter.data.clear()
        logInfo("Weather DATA: $response")
        response?.getOrNull(0)?.intervals?.let { intervals ->
            weatherAdapter.apply {
                data.addAll(intervals)
                notifyDataSetChanged()
            }
        }
    }

}