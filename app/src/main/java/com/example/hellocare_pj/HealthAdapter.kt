package com.example.hellocare_pj

import android.view.LayoutInflater
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hellocare_pj.databinding.HealthitemRecyclerviewBinding
import com.example.hellocare_pj.model.HealthBoardData

//항목 뷰를 가지는 역할
class MyViewHolder(val binding: HealthitemRecyclerviewBinding) :RecyclerView.ViewHolder(binding.root)

//항목 구성자 어댑터
class HealthAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var onClickListener: OnClickListener?=null
    //리사이클러뷰에서 사용할 데이터 미리 정의
    var datalist = mutableListOf<HealthBoardData>()
    val datas = mutableListOf<String>("원인 모를 발목 통증? '부주상골 증후군' 통증, 수술, 치료법",
        "요즘 빈대 물리면 증상이 어떻게 되나요?",
        "모공각화증, 각질이 쌓이는 원인을 없애야..",
        "혼자만의 세계에 갇힌 아이, ‘자폐증’ 증상과 치료법",
        "생리 중 성관계하면 임신 안된다? NO",
        "긁히면 피부 부풀어 오르는 ‘피부 묘기증’,\n" + "냉찜질로 가라앉혀야",
        "‘눈꺼풀 여드름’ 안검염, 만성 안구 건조증으로?",
        "뒤로 젖힐 때 허리 아프다면? ‘척추분리증’ 의심해야",
        "흔한 신생아 황달, 부모가 알아야 할 것은?")

    //항목 개수를 판단하기 위해 자동 호출
    override fun getItemCount(): Int = datas.size

    //항목 뷰를 가지는 뷰 홀더를 준비하기 위해 자동 호출
    private val imageResources = arrayOf(
        R.drawable.healthinfo_1,
        R.drawable.healthinfo_2,
        R.drawable.healthinfo_3,
        R.drawable.healthinfo_4,
        R.drawable.healthinfo_5,
        R.drawable.healthinfo_6,
        R.drawable.healthinfo_7,
        R.drawable.healthinfo_8
        )



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        MyViewHolder(
            HealthitemRecyclerviewBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )


    //각 항목을 구성하기 위해 호출
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int){
        //뷰에 데이터 출력
       // val item = items[position]
        val binding = (holder as MyViewHolder).binding
        val imageResource = imageResources[position]
        binding.itemImg.setImageResource(imageResource)
        binding.itemTxt.text = datas[position]


        //holder.itemView.setOnClickListener{
          //  if(onClickListener != null){
            //    onClickListener!!.onClick(position,item)
            //}
       // }
    }

 //   fun setOnClickListener(onClickListener: OnClickListener){
 //       this.onClickListener=onClickListener
 //   }
 //   interface onClickListener {
 //       fun onClick(position: Int, model: HealthData)
 //   }
}


