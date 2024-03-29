package com.example.hellocare_pj

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class CommunityFragment : Fragment(), OnMapReadyCallback {

    var googleMap: GoogleMap?=null

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView: View = inflater.inflate(R.layout.fragment_community, container, false)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment


        mapFragment.getMapAsync(this)

        return rootView
    }



    // 지도 객체를 이용할 수 있는 상황이 될 때
    override fun onMapReady(p0: GoogleMap?) {
        googleMap = p0

        val marker = LatLng(37.5405, 126.9619)
        googleMap?.addMarker(MarkerOptions().position(marker).title("연세세림내과의원").snippet("02-706-1199"))
        val marker2 = LatLng(37.5399, 126.9611)
        googleMap?.addMarker(MarkerOptions().position(marker2).title("명내과의원").snippet("02-714-1675"))

        val builder = CameraPosition.Builder()
        builder.target(marker)
        builder.zoom(16F)
        val position = builder.build()
        googleMap?.moveCamera(CameraUpdateFactory.newCameraPosition(position))
        //googleMap?.moveCamera(CameraUpdateFactory.newLatLng(marker))
    }





}
