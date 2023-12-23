package com.example.hellocare_pj

import android.graphics.Color
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hellocare_pj.databinding.ActivityHealthboardBinding
import com.example.hellocare_pj.model.HealthBoardData
import com.example.hellocare_pj.model.HealthData

private const val TAG = "HealthFrag"
class HealthActivity: AppCompatActivity() {
    private lateinit var binding: ActivityHealthboardBinding
    private lateinit var adapter: HealthAdapter


    val mDatas = mutableListOf<HealthBoardData>()
    val datas = mutableListOf<HealthData>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHealthboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initHealthRecyclerView()
    }
    fun initHealthRecyclerView(){
        val adapter: HealthAdapter = HealthAdapter() //어댑터 객체 생성
        adapter.datalist=mDatas // 데이터 넣기
        binding.healthRecyclerview.adapter=adapter //리사이클러뷰에 어댑터 연결
        binding.healthRecyclerview.layoutManager = LinearLayoutManager(this)
        binding.healthRecyclerview.addItemDecoration(MyDecoration(30,50))

   //     adapter!!.itemClick = object: HealthAdapter.ItemClick{
   //         override fun onClick(view: View, position: Int) {
   //             val clickedItem = datas[position]
   //             val intent=Intent(baseContext, DetailActivity::class.java)
   //             intent.putExtra("h1", "lkjh")
   //             startActivity(intent)
    //        }
   //     }
    }

}


//리사이클러뷰 꾸미기
class MyDecoration(private val horizontalSpacing : Int,
    private val verticalSpacing: Int): RecyclerView.ItemDecoration(){
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val index = parent.getChildAdapterPosition(view)
        outRect.right = horizontalSpacing
        outRect.left = horizontalSpacing
        if(parent.getChildAdapterPosition(view) == 0){
            outRect.top = verticalSpacing
        }
        outRect.bottom = verticalSpacing
        if(index %2 ==0){
            view.setBackgroundColor(Color.parseColor("#9FC6F9"))
        }else{
            view.setBackgroundColor(Color.parseColor("#EFEEEE"))
        }
        ViewCompat.setElevation(view, 20.0f)
    }
    }