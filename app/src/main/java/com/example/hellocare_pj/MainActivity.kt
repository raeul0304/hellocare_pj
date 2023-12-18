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
import androidx.fragment.app.FragmentManager

private const val TAG_HOME = "home_fragment"
private const val TAG_COMMUNITY = "community_fragment"
private const val TAG_RESERVATION = "reservation_fragment"
private const val TAG_MY_PAGE = "mypage_fragment"

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setFragment(TAG_HOME, HomeFragment())

        binding.navigationView.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.homeFragment -> setFragment(TAG_HOME, HomeFragment())
                R.id.communityFragment-> setFragment(TAG_COMMUNITY, CommunityFragment())
                R.id.reservationFragment -> setFragment(TAG_RESERVATION, ReservationFragment())
                R.id.myPageFragment -> setFragment(TAG_MY_PAGE, MypageFragment())
            }
            true
        }

    }

    private fun setFragment(tag: String, fragment: Fragment) {
        val manager: FragmentManager = supportFragmentManager
        val fragTransaction = manager.beginTransaction()

        if (manager.findFragmentByTag(tag) == null){
            fragTransaction.add(R.id.mainFrameLayout, fragment, tag)
        }


        val home = manager.findFragmentByTag(TAG_HOME)
        val community = manager.findFragmentByTag(TAG_COMMUNITY)
        val reservation = manager.findFragmentByTag(TAG_RESERVATION)
        val mypage = manager.findFragmentByTag(TAG_MY_PAGE)



        if (home != null){
            fragTransaction.hide(home)
        }

        if (community != null){
            fragTransaction.hide(community)
        }

        if (reservation != null) {
            fragTransaction.hide(reservation)
        }

        if (mypage != null) {
            fragTransaction.hide(mypage)
        }


        if (tag == TAG_HOME) {
            if (home != null) {
                fragTransaction.show(home)
            }
        }
        else if (tag == TAG_COMMUNITY) {
            if (community!=null){
                fragTransaction.show(community)
            }
        }
        else if (tag == TAG_RESERVATION) {
            if (reservation != null) {
                fragTransaction.show(reservation)
            }
        }
        else if (tag == TAG_MY_PAGE){
            if (mypage != null){
                fragTransaction.show(mypage)
            }
        }

        fragTransaction.commitAllowingStateLoss()
    }


    val currentUser = Firebase.auth.currentUser

}