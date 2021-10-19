package com.ashok.weatherapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import com.ashok.weatherapp.R
import com.ashok.weatherapp.adapter.WeatherAdapter
import com.ashok.weatherapp.adapter.WeatherListener
import com.ashok.weatherapp.databinding.FragmentMainBinding
import com.ashok.weatherapp.viewmodel.WeatherViewModel

@AndroidEntryPoint
class MainFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentMainBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)

        val weatherViewModel: WeatherViewModel by viewModels()

        val weatherAdapter = WeatherAdapter(WeatherListener { item ->
            weatherViewModel.onWeatherItemClicked(item)
        }
        )
        binding.recyclerviewWeather.adapter = weatherAdapter
        binding.recyclerviewWeather.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        binding.viewmodel = weatherViewModel

        weatherViewModel.navigateToWeatherDetail.observe(viewLifecycleOwner, { item ->
            item?.let {
                this.findNavController().navigate(
                    MainFragmentDirections
                        .actionMainFragmentToDetailFragment(item)
                )
                weatherViewModel.onWeatherDetailNavigated()
            }
        })

        weatherViewModel.listOfItems.observe(viewLifecycleOwner, {
            weatherAdapter.differ.submitList(it)
            binding.groupLoading.visibility = View.GONE
        })

        weatherViewModel.darkMode.observe(viewLifecycleOwner, {
            setDefaultNightMode(if (it) MODE_NIGHT_YES else MODE_NIGHT_NO)
        })

        return binding.root
    }
}