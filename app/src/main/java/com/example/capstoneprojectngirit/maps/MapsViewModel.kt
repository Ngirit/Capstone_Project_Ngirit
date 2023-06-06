package com.example.capstoneprojectngirit.maps

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsViewModel:ViewModel() {
    private val _markerOption=MutableLiveData<MarkerOptions>()
    val markerOption: LiveData<MarkerOptions> = _markerOption

    /*fun createMarker(lat:Double,lon:Double,title:String){
        val position=LatLng(lat,lon)
        val marker = MarkerOptions().position(position).title(title)
        _markerOption.value = marker

    }

     */
}