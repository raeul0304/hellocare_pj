package com.example.hellocare_pj

import androidx.multidex.MultiDexApplication
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

//앱 전역에서 파이어베이스 인증 객체를 이용하고자 Application을 상속받은 MyApplication 클래스를 작성
//매니페스트에 등록해야 앱 전역에서 이용 가능 -> androidmanifest.xml
class MyApplication: MultiDexApplication() {
    companion object{
        lateinit var auth : FirebaseAuth
        var email:String? = null
        //파이어베이스 인증 객체인 FirebaseAuth와 인증된 사용자의 이메일 정보, 인증 상태를 파악하는 checkAuth()
        fun checkAuth():Boolean {
            val currentUser = auth.currentUser
            return currentUser?.let{
                email = currentUser.email
                if(currentUser.isEmailVerified){
                    true
                }else{
                    false
                }
            } ?: let {
                false
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        auth = Firebase.auth
    }
}