package dev.steelahhh.weatherapp.features.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import dev.steelahhh.weatherapp.R

/*
 * Author: steelahhh
 * 14/8/20
 */

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    private val vm: HomeViewModel by viewModels()
}
