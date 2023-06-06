package com.example.capstoneprojectngirit.recomendation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.capstoneprojectngirit.R
import com.example.capstoneprojectngirit.databinding.ActivityRecomendationBinding
import com.example.capstoneprojectngirit.maps.MapsActivity

class RecomendationActivity : AppCompatActivity() {
    private val binding by lazy(LazyThreadSafetyMode.NONE){
      ActivityRecomendationBinding.inflate(layoutInflater)
    }
    private lateinit var recomAdapter:RecomendationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.title=getString(R.string.recomendation)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        recomAdapter = RecomendationAdapter()

        setupRecyclerView()
        buttonAction()
    }

    private fun setupRecyclerView(){
        binding.apply {
            rvRecom.layoutManager = LinearLayoutManager(this@RecomendationActivity)
            rvRecom.setHasFixedSize(true)
            rvRecom.adapter = recomAdapter
        }
    }

    private fun buttonAction(){
        binding.fbMap.setOnClickListener {
            val intent=Intent(this@RecomendationActivity,MapsActivity::class.java)
            startActivity(intent)
        }
    }
}