package com.devem.weather.app.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.devem.weather.app.view.WeatherView
import com.devem.weather.app.viewModel.WeatherViewModel
import com.devem.weather.databinding.FragmentWeatherBinding
import com.devem.weather.domain.retrofit.State
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WeatherFragment : Fragment() {

    private lateinit var _binding: FragmentWeatherBinding
    private val viewModel: WeatherViewModel by viewModels()


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

            viewModel.getWeather()
        }
    }

    private fun initLiveData() {
        viewModel.weatherLD.observe(viewLifecycleOwner) { handleWeather(it) }
    }

    private fun initRecycleView() {
        _binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 1)
    }

    private fun handleWeather(state: State<ArrayList<WeatherView.Data.Timelines?>>) {
        when (state) {
            is State.Success -> {

            }

            is State.Error -> {

            }

            else -> {}
        }
    }
}