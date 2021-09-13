package com.example.coursemapgooglemapsdemo

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.lifecycle.lifecycleScope

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.example.coursemapgooglemapsdemo.databinding.ActivityPolylinesBinding
import com.example.coursemapgooglemapsdemo.misc.CameraAndViewPort
import com.example.coursemapgooglemapsdemo.misc.CustomInfoAdapter
import com.example.coursemapgooglemapsdemo.misc.TypeAndStyle
import com.google.android.gms.maps.model.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PolylinesActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnPolylineClickListener {

    private lateinit var map: GoogleMap

    private val aulaGrandCherry = LatLng(-6.3808608913118645, 106.88091887301215)
    private val jiaecBcb = LatLng(-6.377304831688866, 106.84712749985857)
    private val khutab = LatLng(-6.37719813220062, 106.84652663537027)
    private val dutaFutsal = LatLng(-6.374543175689895, 106.84689141560838)
    private lateinit var binding: ActivityPolylinesBinding
    private val typeAndStyle by lazy { TypeAndStyle() }
    private val cameraAndViewPort by lazy { CameraAndViewPort() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPolylinesBinding.inflate(layoutInflater)
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
        typeAndStyle.setMapType(item, map)
        return true
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
//        map.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))

        val dutaFutsalMarker =
            map.addMarker(MarkerOptions()
                .position(dutaFutsal)
                .title("Marker in duta futsal")
                .snippet("some random text"))

        map.moveCamera(CameraUpdateFactory.newCameraPosition(cameraAndViewPort.aulaGrandCherry))

        // modifikasi agar bisa zoom in dan out
        map.uiSettings.apply {
            isZoomControlsEnabled = true
            isZoomGesturesEnabled = false
            isScrollGesturesEnabled = false
        }
        //menambahkan style pada maps
        typeAndStyle.setMapStyle(map, applicationContext)
        map.setOnPolylineClickListener(this)

        lifecycleScope.launch {
            addPolyLine()
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

    private suspend fun addPolyLine(){
        val polyLine = map.addPolyline(
            PolylineOptions().apply {
                add(aulaGrandCherry, jiaecBcb)
                width(5f)
                color(Color.BLUE)
                geodesic(true)
                clickable(true)
            }
        )
        delay(5000)
        val newList = listOf<LatLng>(
            aulaGrandCherry, khutab, jiaecBcb
        )
        polyLine.points = newList
    }

    override fun onPolylineClick(p0: Polyline) {
        Toast.makeText(this, "Clicked", Toast.LENGTH_LONG).show()
    }
}