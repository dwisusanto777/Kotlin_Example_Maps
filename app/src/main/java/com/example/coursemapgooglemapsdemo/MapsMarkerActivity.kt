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
import com.example.coursemapgooglemapsdemo.databinding.ActivityMapsMarkerBinding
import com.example.coursemapgooglemapsdemo.misc.CameraAndViewPort
import com.example.coursemapgooglemapsdemo.misc.CustomInfoAdapter
import com.example.coursemapgooglemapsdemo.misc.TypeAndStyle
import com.google.android.gms.maps.model.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MapsMarkerActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnMarkerDragListener {

    private lateinit var map: GoogleMap
    private lateinit var binding: ActivityMapsMarkerBinding
    private val typeAndStyle by lazy { TypeAndStyle() }
    private val cameraAndViewPort by lazy { CameraAndViewPort() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsMarkerBinding.inflate(layoutInflater)
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
//        val sydney = LatLng(-34.0, 151.0)
//        map.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        val aulaGrandCherry = LatLng(-6.3808608913118645, 106.88091887301215)
        val jiaecBcb = LatLng(-6.377304831688866, 106.84712749985857)
        val khutab = LatLng(-6.37719813220062, 106.84652663537027)
        val dutaFutsal = LatLng(-6.374543175689895, 106.84689141560838)
//        val dutaFutsalMarker = map.addMarker(MarkerOptions().position(dutaFutsal).title("Marker in duta futsa"))
//        val dutaFutsalMarker = map.addMarker(MarkerOptions().position(dutaFutsal).title("Marker in duta futsal").draggable(true))

        // marker dengan icon atau warna marker
//        val dutaFutsalMarker =
//            map.addMarker(MarkerOptions()
//                .position(dutaFutsal)
//                .title("Marker in duta futsal")
//                .icon(fromVectorToBitmap(R.drawable.ic_launcher_background, Color.parseColor("#123142"))))

        val dutaFutsalMarker =
            map.addMarker(MarkerOptions()
                .position(dutaFutsal)
                .title("Marker in duta futsal")
                .snippet("some random text"))
//        val khutabMarker =
//            map.addMarker(MarkerOptions()
//                .position(khutab)
//                .title("Marker in Khutab")
//                .zIndex(1f))


        map.moveCamera(CameraUpdateFactory.newCameraPosition(cameraAndViewPort.aulaGrandCherry))

        // modifikasi agar bisa zoom in dan out
        map.uiSettings.apply {
            isZoomControlsEnabled = true
            isZoomGesturesEnabled = false
            isScrollGesturesEnabled = false
        }

        //Menampilkan custom view
        map.setInfoWindowAdapter(CustomInfoAdapter(this));

        //menambahkan style pada maps
        typeAndStyle.setMapStyle(map, applicationContext)
        // menambahkan data object ke MARKER
        map.setOnMapClickListener { this }
        map.setOnMarkerDragListener(this)

        // pergantian lokasi dengan menunnggu waktu delay 4 detik dan menggunakan bounce
        lifecycleScope.launch {
            delay(4000L)
            dutaFutsalMarker.remove()

        }
    }

    private fun fromVectorToBitmap(id:Int, color:Int):BitmapDescriptor{
        val vectorDrawable:Drawable? = ResourcesCompat.getDrawable(resources, id, null)
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

    override fun onMarkerClick(marker: Marker): Boolean {
//        if(marker!=null) {
//            Log.d("Marker", marker.tag as String)
//        }else{
//            Log.d("Marker", "Marker empty")
//        }
        map.animateCamera(CameraUpdateFactory.zoomTo(17f), 2000, null)
        marker?.showInfoWindow()
        // jika return true tidak muncul pop up marker di maps
        return true
    }

    override fun onMarkerDragStart(p0: Marker) {
        Log.d("Drag", "Start")
    }

    override fun onMarkerDrag(p0: Marker) {
        Log.d("Drag", "Drag")
    }

    override fun onMarkerDragEnd(p0: Marker) {
        Log.d("Drag", "Stop")
    }
}