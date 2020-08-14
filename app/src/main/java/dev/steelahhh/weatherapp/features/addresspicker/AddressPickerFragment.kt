package dev.steelahhh.weatherapp.features.addresspicker

import android.os.Bundle
import android.view.View
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // TODO add this to some button that closes the picker
        // setOnClickListener {
        //     setFragmentResult(ADDRESS_RESULT_KEY, bundleOf(ADDRESS_RESULT_KEY to Any()))
        //
        //     findNavController().navigateUp()
        // }
    }

    companion object {
        const val ADDRESS_REQUEST_ID = "request:pick_address"

        const val ADDRESS_RESULT_KEY = "key:address"
    }
}
