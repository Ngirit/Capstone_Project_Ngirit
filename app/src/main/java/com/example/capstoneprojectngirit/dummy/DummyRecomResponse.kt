package com.example.capstoneprojectngirit.dummy

import android.graphics.drawable.Drawable
import android.graphics.drawable.PaintDrawable
import com.example.capstoneprojectngirit.R

data class DummyRecomResponse(
    val listRecom: ArrayList<DummyListRecom>
)

data class DummyListRecom(
    val photoUrl:Drawable= PaintDrawable(R.drawable.logo_ngirit),
    val name:String="name",
    val location:String="lokasi",
    val price:String="",
    val rating:String=""

)