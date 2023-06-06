package com.example.capstoneprojectngirit.dummy

import android.graphics.drawable.Drawable
import android.graphics.drawable.PaintDrawable
import androidx.annotation.DrawableRes
import com.example.capstoneprojectngirit.R

data class DummyRecomResponse(
    val listRecom: ArrayList<DummyListRecom>
)

data class DummyListRecom(
    val photoUrl:Drawable= PaintDrawable(R.drawable.logo_ngirit),
    val name:String="name",
    val description:String="lokasi",
    val lon:Double=-7.77,
    val lat:Double=112.112,
)