package com.example.capstoneprojectngirit.recomendation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.capstoneprojectngirit.databinding.ItemRecomendationBinding
import com.example.capstoneprojectngirit.dummy.DummyListRecom

class RecomendationAdapter : RecyclerView.Adapter<RecomendationAdapter.RecomViewHolder>() {
    private val recom = ArrayList<DummyListRecom>()
        class RecomViewHolder(private val binding:ItemRecomendationBinding):RecyclerView.ViewHolder(binding.root){
            fun bind(recom: DummyListRecom){
                binding.apply {
                    //Glide.with(itemView.context).load(recom.photoUrl)
                    tvFoodName.text=recom.name
                    tvDesc.text = recom.location
                    tvPrice.text = recom.price
                    tvRate.text = recom.rating
                }
            }
     }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecomViewHolder {
        val view = ItemRecomendationBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return RecomViewHolder(view)
    }

    override fun getItemCount(): Int = recom.size



    override fun onBindViewHolder(holder: RecomViewHolder, position: Int) {
        holder.bind(recom[position])
    }

    fun updateData(data: List<DummyListRecom>) {
        recom.clear()
        recom.addAll(data)
        notifyDataSetChanged()
    }
}