package com.example.capstoneprojectngirit.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import com.example.capstoneprojectngirit.R
import com.example.capstoneprojectngirit.databinding.ActivityMainBinding
import com.example.capstoneprojectngirit.databinding.ActivityMapsBinding
import com.example.capstoneprojectngirit.recomendation.RecomendationActivity
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody.Companion.toResponseBody

class MainActivity : AppCompatActivity() {
    private val binding by lazy(LazyThreadSafetyMode.PUBLICATION){
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        radioButtonAction()

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
}