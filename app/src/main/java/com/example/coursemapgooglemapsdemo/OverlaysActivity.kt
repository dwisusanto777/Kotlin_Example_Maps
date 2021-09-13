package com.example.coursemapgooglemapsdemo

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.lifecycle.lifecycleScope
import com.example.coursemapgooglemapsdemo.databinding.ActivityCircleBinding

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.example.coursemapgooglemapsdemo.databinding.ActivityOverlaysBinding
import com.example.coursemapgooglemapsdemo.misc.CameraAndViewPort
import com.example.coursemapgooglemapsdemo.misc.Overlays
import com.example.coursemapgooglemapsdemo.misc.Shape
import com.example.coursemapgooglemapsdemo.misc.TypeAndStyle
import com.google.android.gms.maps.model.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class OverlaysActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap
    private lateinit var binding: ActivityOverlaysBinding
    private val typeAndStyle by lazy { TypeAndStyle() }
    private val cameraAndViewPort by lazy { CameraAndViewPort() }
    private val shape by lazy { Shape() }
    private val overlays by lazy { Overlays() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOverlaysBinding.inflate(layoutInflater)
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

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
//        map.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))


        // modifikasi agar bisa zoom in dan out
        map.uiSettings.apply {
            isZoomControlsEnabled = true
            isZoomGesturesEnabled = false
            isScrollGesturesEnabled = false
        }

        val graoundOverlays = overlays.addGroundOverlaysWithTag(map)
        lifecycleScope.launch {
            delay(4000)
//            graoundOverlays.remove()
//            graoundOverlays.transparency = 0.5f
        }
    }

    private fun fromVectorToBitmap(id:Int, color:Int): BitmapDescriptor {
        val vectorDrawable: Drawable? = ResourcesCompat.getDrawable(resources, id, null)
        if(vectorDrawable==null){
            Log.d("MapsActivity", "Resources Not Found")
            return BitmapDescriptorFactory.defaultMarker()
        }else{
            val bitmap = Bitmap.createBitmap(
                vectorDrawable.intrinsicWidth,
                vectorDrawable.intrinsicHeight,
                Bitmap.Config.ARGB_8888
            )
            val canvas = Canvas(bitmap)
            vectorDrawable.setBounds(0,0,canvas.width,canvas.height)
            DrawableCompat.setTint(vectorDrawable, color)
            vectorDrawable.draw(canvas)
            return BitmapDescriptorFactory.fromBitmap(bitmap)
        }
    }

}