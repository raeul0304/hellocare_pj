package com.example.hellocare_pj

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class TypeoneFragment : Fragment(), OnMapReadyCallback {
    var googleMap: GoogleMap?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView: View = inflater.inflate(R.layout.fragment_typeone, container, false)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map_fragment2) as SupportMapFragment
        mapFragment.getMapAsync(this)
        return rootView
    }

    override fun onMapReady(p0: GoogleMap?) {
        googleMap = p0

        val marker = LatLng(37.5427, 126.9519)
        googleMap?.addMarker(MarkerOptions().position(marker).title("공덕연세이비인후과의원").snippet("02-2197-1707"))
        val marker2 = LatLng(37.545343, 126.949150)
        googleMap?.addMarker(MarkerOptions().position(marker2).title("연세소리이비인후과").snippet("02-701-1199"))
        val marker3 = LatLng(37.5429, 126.9525)
        googleMap?.addMarker(MarkerOptions().position(marker3).title("조앤필이비인후과의원").snippet("02-6401-1111"))


        val builder = CameraPosition.Builder()
        builder.target(marker)
        builder.zoom(15F)
        val position = builder.build()
        googleMap?.moveCamera(CameraUpdateFactory.newCameraPosition(position))
        //googleMap?.moveCamera(CameraUpdateFactory.newLatLng(marker))
    }
}