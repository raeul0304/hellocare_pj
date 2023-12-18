package com.example.hellocare_pj

import android.os.Bundle
import android.os.PersistableBundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.hellocare_pj.databinding.ActivitySigninBinding

class SigninActivity : AppCompatActivity(){

    lateinit var binding: ActivitySigninBinding
    var nameFlag = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySigninBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val count = 0
        //모두 입력시 회원가입 가능
        val signname = binding.signnameEditline.text.toString()
        val signbirth = binding.signbirthEditline.text.toString()

        val email = binding.idEditline.text.toString()
        val password = binding.pwEditline.text.toString()



        binding.signNextbtn.setOnClickListener{
            //이메일, 비밀번호 회원가입
            MyApplication.auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    binding.idEditline.text.clear()
                    binding.pwEditline.text.clear()
                    if(task.isSuccessful){
                        MyApplication.auth.currentUser?.sendEmailVerification()
                            ?.addOnCompleteListener { sendTask ->
                                if(sendTask.isSuccessful){
                                    Toast.makeText(baseContext, "회원가입 되었습니다. 전송된 메일을 확인해주세요!", Toast.LENGTH_SHORT).show()
                                    //changeVisibility("logout") - 리사이클러뷰, 어댑터 사용해야
                                }else{
                                    Toast.makeText(baseContext, "메일 발송 실패", Toast.LENGTH_SHORT).show()
                                    //changeVisibility("logout")
                                }
                            }
                    }else{
                        Toast.makeText(baseContext, "회원가입 실패", Toast.LENGTH_SHORT).show()
                        //changeVisibility("logout")
                    }
                }
        }


    }

    //특수문자 존재 여부를 확인하는 메소드
    private fun hasSpecialCharacter(string: String):Boolean{
        for(i in string.indices){
            if(!Character.isLetterOrDigit(string[i])){
                return true
            }
        }
        return false
    }
    //영문자 존재 여부를 확인하는 메소드
    private fun hasAlphabet(string: String):Boolean{
        for(i in string.indices){
            if(Character.isAlphabetic(string[i].code)){
                return true
            }
        }
        return false
    }
    //특수 문자 없이 empty(2글자 이상)인지 아닌지 확인 메소드
    private fun nullCheck(name: String):Boolean{
        if((!hasSpecialCharacter(name)) and (name.length>=2)){
            return true
        }
        return false
    }

    //nameTextWatcher
    private val signnameListener = object : TextWatcher{
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            if (s!=null){
                when{
                    s.isEmpty()->{
                        binding.signnameEditline.error="이름을 입력해주세요"
                        nameFlag = false
                    }
                    !nullCheck(s.toString()) -> {
                        binding.signnameEditline.error="두 글자 이상 입력해주세요"
                        nameFlag = false
                    }
                    else -> {
                        binding.signnameEditline.error=null
                        nameFlag = true
                    }
                }
                flagCheck()
            }
        }
    }

    //flagCheck()
    fun flagCheck(){
        binding.signNextbtn.isEnabled = nameFlag
    }
}