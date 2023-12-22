package com.example.hellocare_pj

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.hellocare_pj.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.btnMap.setOnClickListener(View.OnClickListener {
            val intent =
                Intent(activity, MapActivity::class.java) //fragment라서 activity intent와는 다른 방식
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        })

        binding.btnMap2.setOnClickListener(View.OnClickListener {
            val intent =
                Intent(activity, MaptwoActivity::class.java) //fragment라서 activity intent와는 다른 방식
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        })

        return binding.root
    }

}