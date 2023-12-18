package com.example.hellocare_pj

import android.content.Intent
import android.location.Location
import android.content.pm.PackageManager
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.FrameLayout
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.hellocare_pj.databinding.ActivityMainBinding
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApi
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity(), GoogleApiClient.ConnectionCallbacks,
    GoogleApiClient.OnConnectionFailedListener, OnMapReadyCallback{

    lateinit var binding: ActivityMainBinding

    lateinit var providerClient: FusedLocationProviderClient
    lateinit var apiClient: GoogleApiClient
    var googleMap: GoogleMap?=null

    private lateinit var tabLayout: TabLayout
    private lateinit var frameLayout: FrameLayout



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        frameLayout = findViewById(R.id.mypagetabcontent)
        tabLayout = findViewById(R.id.mypagetabs)

        tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            //val transaction = supportFragmentManager.beginTransaction()
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab!!.position) {
                    0 -> supportFragmentManager.beginTransaction().replace(R.id.mypagetabcontent, findViewById(R.id.myinfofrag)).commit()
                    1 -> supportFragmentManager.beginTransaction().replace(R.id.mypagetabcontent, findViewById(R.id.myinfofrag)).commit()
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }
        })

        //지도 api
        val requestPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            if (it.all { permission -> permission.value == true }){
                apiClient.connect()
            }else {
                Toast.makeText(this, "권한 거부..", Toast.LENGTH_SHORT).show()
            }
        }
        (supportFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment)!!.getMapAsync(this)

        providerClient = LocationServices.getFusedLocationProviderClient(this)
        apiClient = GoogleApiClient.Builder(this)
            .addApi(LocationServices.API)
            .addConnectionCallbacks(this)
            .addOnConnectionFailedListener(this)
            .build()

        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) !== PackageManager.PERMISSION_GRANTED) {
            requestPermissionLauncher.launch(
                arrayOf(
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                )
            )
        }else {
            // 위치 제공자 준비하기
            apiClient.connect()
        }


    }


    // 위치 제공자를 사용할 수 있는 상황일 때
    override fun onConnected(p0: Bundle?) {
        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) === PackageManager.PERMISSION_GRANTED){
            providerClient.lastLocation.addOnSuccessListener(
                this@MainActivity,
                object: OnSuccessListener<Location> {
                    override fun onSuccess(p0: Location?) {
                        p0?.let {
                            val latitude = p0.latitude
                            val longitude = p0.longitude
                            Log.d("kkang", "$latitude, $longitude")
                            // 지도 중심 이동하기
                            moveMap(latitude, longitude)
                        }
                    }
                }
            )
            apiClient.disconnect()
        }


    }

    override fun onConnectionSuspended(p0: Int) {
        // 위치 제공자를 사용할 수 없을 때
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        // 사용할 수 있는 위치 제공자가 없을 때
    }
    // 지도 객체를 이용할 수 있는 상황이 될 때
    override fun onMapReady(p0: GoogleMap?) {
        googleMap = p0

        val marker = LatLng(35.241615, 128.695587)
        googleMap?.addMarker(MarkerOptions().position(marker).title("마커 제목"))
        googleMap?.moveCamera(CameraUpdateFactory.newLatLng(marker))
    }

    private fun moveMap(latitude: Double, longitude: Double){
        val latLng = LatLng(latitude, longitude)
        val position: CameraPosition = CameraPosition.builder().target(latLng).zoom(16f).build()
        //지도 중심 이동하기
        googleMap!!.moveCamera(CameraUpdateFactory.newCameraPosition(position))
        //마커 옵션
        val markerOptions = MarkerOptions()
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
        markerOptions.position(latLng)
        markerOptions.title("MyLocation")
        //마커 표시하기
        googleMap?.addMarker(markerOptions)
    }

    val currentUser = Firebase.auth.currentUser

}