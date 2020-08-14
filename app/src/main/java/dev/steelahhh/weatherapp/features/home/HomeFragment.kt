package dev.steelahhh.weatherapp.features.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import dev.steelahhh.weatherapp.R
import dev.steelahhh.weatherapp.features.addresspicker.AddressPickerFragment.Companion.ADDRESS_REQUEST_ID

/*
 * Author: steelahhh
 * 14/8/20
 */

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    private val vm: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setFragmentResultListener(ADDRESS_REQUEST_ID) { key, bundle ->
            if (key == ADDRESS_REQUEST_ID) {
                // TODO: handle the picked address
            }
        }
    }
}
