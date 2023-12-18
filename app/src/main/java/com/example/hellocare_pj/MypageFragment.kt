package com.example.hellocare_pj

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.hellocare_pj.databinding.FragmentMypageBinding
import com.example.hellocare_pj.util.MyinfoAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MypageFragment : Fragment() {
    private lateinit var viewPager2 : ViewPager2
    private lateinit var tabLayout : TabLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view:View = inflater.inflate(R.layout.fragment_mypage, container, false)
        viewPager2 = view.findViewById(R.id.viewpager2)
        tabLayout = view.findViewById(R.id.tabLayout)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val pagerAdapter = MyinfoAdapter(requireActivity())
        // 2개의 Fragment Add
        pagerAdapter.addFragment(MyinfoFragment())
        pagerAdapter.addFragment(MystarFragment())

        // Adapter
        viewPager2.adapter = pagerAdapter
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int){
                super.onPageSelected(position)
                Log.e("ViewPagerFragment", "Page ${position+1}")
            }
        })

        TabLayoutMediator(tabLayout, viewPager2) {tab, position ->
            when (position){
                0 -> {
                    tab.text = "내 정보 수정"
                }
                1 -> {
                    tab.text = "즐겨찾기"
                }
            }
        }.attach()
    }


}