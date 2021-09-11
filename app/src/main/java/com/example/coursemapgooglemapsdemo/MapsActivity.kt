package com.example.coursemapgooglemapsdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.coursemapgooglemapsdemo.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.map_types_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.normal_map ->{
                map.mapType = GoogleMap.MAP_TYPE_NORMAL
            }
            R.id.hybrid_map ->{
                map.mapType = GoogleMap.MAP_TYPE_HYBRID
            }
            R.id.satellite_map ->{
                map.mapType = GoogleMap.MAP_TYPE_SATELLITE
            }
            R.id.terrain_map ->{
                map.mapType = GoogleMap.MAP_TYPE_TERRAIN
            }
            R.id.none_map ->{
                map.mapType = GoogleMap.MAP_TYPE_NONE
            }
        }
        return true
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        // Add a marker in Sydney and move the camera
//        val sydney = LatLng(-34.0, 151.0)
//        map.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        val aulaGrandCherry = LatLng(-6.3808608913118645, 106.88091887301215)
        map.addMarker(MarkerOptions().position(aulaGrandCherry).title("Lokasi aula grand cherry"))
//        map.moveCamera(CameraUpdateFactory.newLatLng(aulaGrandCherry))
        // ini untuk agar zoom default
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(aulaGrandCherry, 15f))

        // modifikasi agar bisa zoom in dan out
        map.uiSettings.apply {
            isZoomControlsEnabled = true
            isZoomGesturesEnabled = false
            isScrollGesturesEnabled = false
        }

        // set padding untuk mengukur ukuran map
        map.setPadding(0,0,300,0)
    }
}