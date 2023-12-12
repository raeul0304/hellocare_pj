package com.example.hellocare_pj

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

lateinit var auth : FirebaseAuth
class EmailPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = Firebase.auth
    }

    public override fun onStart() {
        super.onStart()
        //Check if user is signed in (non-null) and update UI account
        val currentUser = auth.currentUser
    }

    //회원가입하기
    private fun createAccount(email: String, password: String){
        //createUserWithEmailAndPassword() 함수로 파이어베이스에 이메일/비밀번호 등록
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){ task ->
                //회원가입 성공, 실패 판단
                if(task.isSuccessful){
                    //Sign in success, update UI with the signed-in users information
                    Log.d("hellocare", "createUserWithEmail:success")
                    //이메일/비번 등록 성공시 sendEmailVerification 함수로 인증 메일 전송
                    auth.currentUser?.sendEmailVerification()
                        ?.addOnCompleteListener { sendTask ->
                            if(sendTask.isSuccessful){
                                Log.d("hellocare", "sendverificationEmail:success")

                            }else{
                                Log.d("hellocare", "sendverificationEmail:failure")
                            }
                        }
                    //입력한 계정을 새로 등록
                    goToMainActivity(task.result?.user)
                }else{
                    Log.w("hellocare", "createUserWithEmail:failure", task.exception)
                    //입력한 계정 정보가 이미 Firebase DB에 있는 경우
                    signIn(email, password)
                }
        }
    }
    //회원가입 및 로그인 성공 시 메인 화면으로 이동하는 함수
    private fun goToMainActivity(user: FirebaseUser?){
        //Firebase에 등록된 계정일 경우에만 메인 화면으로 이동
        if(user != null){
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    //기존 사용자 로그인 - 로그인 함수
    private fun signIn(email:String, password: String){
        auth?.signInWithEmailAndPassword(email, password)
            ?.addOnCompleteListener { task ->
                if(task.isSuccessful){
                    //로그인에 성공한 경우 메인 화면으로 이동
                    goToMainActivity(task.result?.user)
                }else{
                    //로그인에 실패한 경우 Toast 메시지로 에러 띄워주기
                    Toast.makeText(this, task.exception?.message, Toast.LENGTH_SHORT)
                }
            }
    }
}
