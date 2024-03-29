package com.example.hellocare_pj

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.hellocare_pj.databinding.ActivityLoginBinding
import com.example.hellocare_pj.databinding.ActivitySigninBinding
import com.example.hellocare_pj.util.myCheckPermission

class LoginActivity: AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myCheckPermission(this)
        //로그인
        binding.loginbtn.setOnClickListener {
            //로그인 through 이메일, 비밀번호
            val email = binding.idText.text.toString()
            val password = binding.pwText.text.toString()
            Log.d("hellocare", "email:$email, password:$password")
            MyApplication.auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    binding.idText.text.clear()
                    binding.pwText.text.clear()
                    if (task.isSuccessful) {
                        if (MyApplication.checkAuth()) {
                            MyApplication.email = email
                            //changevisibility("login") - recyclerview활용해야..
                            startActivity(Intent(this, MainActivity::class.java))
                        } else {
                            Toast.makeText(
                                baseContext,
                                "전송된 메일로 이메일 인증이 되지 않았습니다",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        Toast.makeText(baseContext, "로그인 실패", Toast.LENGTH_SHORT).show()
                    }
                }
        }

        //회원가입 버튼 클릭 시 화면 이동
        binding.joinBtn.setOnClickListener{
            startActivity((Intent(this, SigninActivity::class.java)))
        }

    }
}