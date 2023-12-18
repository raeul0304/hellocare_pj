package com.example.hellocare_pj

import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.hellocare_pj.databinding.FragmentCommunityBinding
import com.example.hellocare_pj.databinding.FragmentMypageBinding
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.OnSuccessListener

class CommunityFragment : Fragment(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap


    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView: View = inflater.inflate(R.layout.fragment_community, container, false)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment

        mapFragment.getMapAsync(this)

        return rootView
    }



    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val marker = LatLng(35.241615, 128.695587)
        val marker2 = LatLng(37.545064, 126.965697)
        mMap.addMarker(MarkerOptions().position(marker).title("Marker LAB"))
        mMap.addMarker(MarkerOptions().position(marker2).title("Marker2"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(marker))
    }

}
