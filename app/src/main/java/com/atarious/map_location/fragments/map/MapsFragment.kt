package com.atarious.map_location.fragments.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.atarious.map_location.R
import com.atarious.map_location.databinding.FragmentMapsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsFragment : Fragment() {
    private var _binding: FragmentMapsBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<MapsFragmentArgs>()
    private val callback = OnMapReadyCallback { googleMap ->

        val lat = args.details.lat
        val lng = args.details.lng
        val location = LatLng(lat.toDouble(),lng.toDouble() )
        googleMap.addMarker(MarkerOptions().position(location).title("${args.details.city},${args.details.country}"))?.showInfoWindow()
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 17f))
        googleMap.animateCamera(CameraUpdateFactory.zoomIn())
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(10f), 5000, null)
        googleMap.uiSettings.isCompassEnabled = true
        googleMap.uiSettings.isZoomControlsEnabled =true

        when {
            binding.maptype1.isChecked -> {
                googleMap.mapType = GoogleMap.MAP_TYPE_NORMAL
            }
            binding.maptype2.isChecked -> {
                googleMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
            }
            binding.maptype3.isChecked -> {
                googleMap.mapType = GoogleMap.MAP_TYPE_HYBRID
            }
            binding.maptype4.isChecked -> {
                googleMap.mapType = GoogleMap.MAP_TYPE_TERRAIN
            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        activity?.findViewById<TextView>(R.id.custom_title)?.text = "City View"
        _binding = FragmentMapsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.back.setOnClickListener {
            findNavController().navigate(R.id.action_MapFragment_to_ListFragment)
        }
        binding.radio.setOnCheckedChangeListener { _, _ ->
            val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
            mapFragment?.getMapAsync(callback)
        }
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)

    }
}