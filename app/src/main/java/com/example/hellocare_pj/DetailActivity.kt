package com.example.hellocare_pj

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hellocare_pj.databinding.ActivityDetailBinding
import com.example.hellocare_pj.model.HealthData

class DetailActivity: AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var db:HealthData

    override fun onCreate(savedInstanceState : Bundle?){
        binding = ActivityDetailBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

}