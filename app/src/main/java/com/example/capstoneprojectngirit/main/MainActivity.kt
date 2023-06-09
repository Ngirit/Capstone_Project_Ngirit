package com.example.capstoneprojectngirit.main

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import android.widget.Toast
import androidx.core.app.ActivityOptionsCompat
import com.example.capstoneprojectngirit.R
import com.example.capstoneprojectngirit.databinding.ActivityMainBinding
import com.example.capstoneprojectngirit.databinding.ActivityMapsBinding
import com.example.capstoneprojectngirit.login.LoginActivity
import com.example.capstoneprojectngirit.recomendation.RecomendationActivity
import com.example.capstoneprojectngirit.user.UserPreference
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody.Companion.toResponseBody

class MainActivity : AppCompatActivity() {
    private val binding by lazy(LazyThreadSafetyMode.PUBLICATION){
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var userPreference: UserPreference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        userValidation()
        radioButtonAction()

    }

    private fun userValidation(){
        if (!userPreference.getUser().isLogin){
            val userLogin = userPreference.getUser().isLogin
            Log.d(TAG,userLogin.toString())
            val intent = Intent(this@MainActivity,LoginActivity::class.java)
            startActivity(intent,ActivityOptionsCompat.makeSceneTransitionAnimation(this@MainActivity as Activity).toBundle())
        }
    }

    private fun radioButtonAction(){
        binding.rgKategori.setOnCheckedChangeListener{group,checkedId->
            binding.btSearch.isEnabled=checkedId!=-1
        }
        binding.btSearch.setOnClickListener {
            val selectedId=binding.rgKategori.checkedRadioButtonId
            if (selectedId!=-1){
                val radioButton:RadioButton=findViewById(selectedId)
                val selectedText = radioButton.text.toString().toRequestBody("text/plain".toMediaType())
                Toast.makeText(this,"$selectedText",Toast.LENGTH_SHORT).show()
            }
        }

    }

    companion object{
        const val TAG = "extra_tag"
    }
}