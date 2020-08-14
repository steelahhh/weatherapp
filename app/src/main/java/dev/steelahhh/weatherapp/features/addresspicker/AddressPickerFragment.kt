package dev.steelahhh.weatherapp.features.addresspicker

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import dagger.hilt.android.AndroidEntryPoint
import dev.steelahhh.weatherapp.R

/*
 * Author: steelahhh
 * 14/8/20
 */

@AndroidEntryPoint
class AddressPickerFragment : Fragment(R.layout.fragment_address_picker), OnMapReadyCallback {
    private val vm: AddressPickerViewModel by viewModels()

    /**
     * Store map reference in a nullable variable, to avoid potential crashes
     */
    private var map: GoogleMap? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.supportMapFragment) as SupportMapFragment

        mapFragment.getMapAsync(this)
        // TODO add this to some button that closes the picker
        // setOnClickListener {
        //     setFragmentResult(ADDRESS_RESULT_KEY, bundleOf(ADDRESS_RESULT_KEY to Any()))
        //
        //     findNavController().navigateUp()
        // }
    }

    override fun onDestroyView() {
        map = null
        super.onDestroyView()
    }

    override fun onMapReady(map: GoogleMap) {
        this.map = map
        // map.setPadding(0, 0, 0, buttonHeight)
    }

    companion object {
        const val ADDRESS_REQUEST_ID = "request:pick_address"

        const val ADDRESS_RESULT_KEY = "key:address"
    }
}
