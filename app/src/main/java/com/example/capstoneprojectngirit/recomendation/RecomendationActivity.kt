package com.example.capstoneprojectngirit.recomendation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.capstoneprojectngirit.R
import com.example.capstoneprojectngirit.databinding.ActivityRecomendationBinding
import com.example.capstoneprojectngirit.dummy.DummyListRecom
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
        val newData = listOf(
            DummyListRecom(
                name = "Sirloin Steak Beef Pops",
                location = "Abe Steak, Cibubur",
                price = "Rp 25000.0",
                rating = "4.1"

            ),
            DummyListRecom(
                name ="Tenderloin Steak Beef Pop",
                location = "Abe Steak, Cibubur",
                price = "Rp 66000.0",
                rating = "4.1"

            ),
            DummyListRecom(
                name ="Chicken Drumstick",
                location = "Abe Steak, Cibubur",
                price = "Rp 25000.0",
                rating = "4.1"

            ),
            DummyListRecom(
                name ="Beef Steak",
                location = "Abe Steak, Cibubur",
                price = "Rp 42000.0",
                rating = "4.1"

            ),
            DummyListRecom(
                name ="Chicken Steak Geprek",
                location = "Abe Steak, Cibubur",
                price = "Rp 25000.0",
                rating = "4.1"

            ),
            DummyListRecom(
                name ="Tenderloin Crispy",
                location = "Abe Steak, Cibubur",
                price = "Rp 28000.0",
                rating = "4.1"

            ),
            DummyListRecom(
                name ="Chicken Steak Pops",
                location = "Abe Steak, Cibubur",
                price = "Rp 20000.0",
                rating = "4.1"

            ),
        )
        recomAdapter.updateData(newData)
    }
}