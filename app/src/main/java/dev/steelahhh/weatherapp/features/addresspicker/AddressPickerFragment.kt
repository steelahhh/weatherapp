package dev.steelahhh.weatherapp.features.addresspicker

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import dev.steelahhh.weatherapp.R

/*
 * Author: steelahhh
 * 14/8/20
 */

@AndroidEntryPoint
class AddressPickerFragment : Fragment(R.layout.fragment_address_picker) {
    private val vm: AddressPickerViewModel by viewModels()
}
