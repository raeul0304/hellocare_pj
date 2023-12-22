package com.example.hellocare_pj

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.hellocare_pj.databinding.ActivityHealthboardBinding
import com.example.hellocare_pj.model.HealthBoardData
import com.example.hellocare_pj.model.HealthData

class HealthActivity: AppCompatActivity(){
    private lateinit var binding: ActivityHealthboardBinding
    private lateinit var adapter: HealthAdapter

    val mDatas = mutableListOf<HealthBoardData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHealthboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initHealthRecyclerView()
    }
    fun initHealthRecyclerView(){
        val adapter = HealthAdapter() //어댑터 객체 생성
        adapter.datalist=mDatas // 데이터 넣기
        binding.healthRecyclerview.adapter=adapter //리사이클러뷰에 어댑터 연결
        binding.healthRecyclerview.layoutManager = LinearLayoutManager(this)
    }

    //리사이클러뷰 꾸미기


}