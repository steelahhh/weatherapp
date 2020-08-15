package dev.steelahhh.weatherapp.features.addresspicker

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint
import dev.steelahhh.weatherapp.R
import dev.steelahhh.weatherapp.features.addresspicker.AddressPickerViewModel.State
import kotlinx.android.synthetic.main.fragment_address_picker.*
import kotlinx.coroutines.flow.collect

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
        setupListeners()
    }

    override fun onDestroyView() {
        map = null
        super.onDestroyView()
    }

    override fun onMapReady(map: GoogleMap) {
        this.map = map
        map.clear()

        // TODO change icon to ic_current to differentiate the icons
        val current = vm.getCurrentLocation()
        map.addMarker(MarkerOptions().position(current))

        // set margin to make Google visible
        val margin = requireContext().resources.getDimensionPixelSize(R.dimen.margin_16)
        map.setPadding(margin, 0, 0, pickAddressButton.measuredHeight)

        // move camera to current selected address
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(current, DEFAULT_ZOOM))

        // listen to camera changes, and store new location in memory
        map.setOnCameraMoveListener {
            val centerLatLng = map.cameraPosition.target
            vm.onCameraMoved(centerLatLng.latitude, centerLatLng.longitude)
        }
    }

    private fun setupListeners() {
        lifecycleScope.launchWhenResumed {
            vm.state.collect {
                val isAddressChanged = it is State.PickedNewAddress
                // show the center pin only when address is changed
                centerPinIv.isVisible = isAddressChanged

                // change the title depending on the current state
                pickAddressButton.text = requireContext().getString(
                    when {
                        isAddressChanged -> R.string.title_pick_address_button
                        else -> R.string.title_go_back
                    }
                )
            }
        }

        pickAddressButton.setOnClickListener {
            vm.onAddressPicked()

            val currentState = vm.state.value
            if (currentState is State.PickedNewAddress) {
                setFragmentResult(
                    requestKey = ADDRESS_REQUEST_ID,
                    result = bundleOf()
                )
            }

            findNavController().navigateUp()
        }
    }

    companion object {
        const val ADDRESS_REQUEST_ID = "request:pick_address"

        const val DEFAULT_ZOOM = 16f
    }
}
