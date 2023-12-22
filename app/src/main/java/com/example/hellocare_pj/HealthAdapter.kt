package com.example.hellocare_pj

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.SearchView.OnQueryTextListener
import androidx.recyclerview.widget.RecyclerView
import com.example.hellocare_pj.databinding.ActivityHealthboardBinding
import com.example.hellocare_pj.databinding.HealthitemRecyclerviewBinding
import com.example.hellocare_pj.model.HealthBoardData
import com.example.hellocare_pj.model.HealthData

//항목 뷰를 가지는 역할
class MyViewHolder(val binding: HealthitemRecyclerviewBinding)
    :RecyclerView.ViewHolder(binding.root)

//항목 구성자 어댑터
class HealthAdapter ()
    : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    //리사이클러뷰에서 사용할 데이터 미리 정의
    var datalist = mutableListOf<HealthBoardData>()
    val datas = mutableListOf<String>("원인 모를 발목 통증? '부주상골 증후군' 통증, 수술, 치료법",
        "요즘 빈대 물리면 증상이 어떻게 되나요?")

    //항목 개수를 판단하기 위해 자동 호출
    override fun getItemCount(): Int = datas.size

    //항목 뷰를 가지는 뷰 홀더를 준비하기 위해 자동 호출
    private val imageResources = arrayOf(
        R.drawable.healthinfo_1,
        R.drawable.healthinfo_2,
        )
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        MyViewHolder(
            HealthitemRecyclerviewBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    //각 항목을 구성하기 위해 호출
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //뷰에 데이터 출력
        val binding = (holder as MyViewHolder).binding
        val imageResource = imageResources[position]
        binding.itemImg.setImageResource(imageResource)
        binding.itemTxt.text= datas[position]
    }

}

