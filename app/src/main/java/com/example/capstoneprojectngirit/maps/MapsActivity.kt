package com.example.capstoneprojectngirit.maps

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.example.capstoneprojectngirit.R
import com.example.capstoneprojectngirit.databinding.ActivityMapsBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private lateinit var fusedLocation: FusedLocationProviderClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title=getString(R.string.maps)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        fusedLocation= LocationServices.getFusedLocationProviderClient(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        getLastLocation()
        // Add a marker in Sydney and move the camera
       val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }
    private fun checkingPermission(permission:String):Boolean{
        return ContextCompat.checkSelfPermission(this,permission)== PackageManager
            .PERMISSION_GRANTED
    }

    private val requestPermission=registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()){
            permission ->
        when {
            permission[android.Manifest.permission.ACCESS_FINE_LOCATION] ?: false -> {
                //fine location access granted
                // getLastLocation()
            }
            permission[android.Manifest.permission.ACCESS_COARSE_LOCATION] ?: false -> {
                //only coarse location granted
                getLastLocation()
            }
            else -> {}
        }
    }
    private fun getLastLocation(){
        if (checkingPermission(android.Manifest.permission.ACCESS_FINE_LOCATION)&&
            checkingPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION))
        {
            //showMarkerLocation()
        }else{
            requestPermission.launch(arrayOf(
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ))
        }
    }
}