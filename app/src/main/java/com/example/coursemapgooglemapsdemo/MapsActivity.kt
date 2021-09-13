package com.example.coursemapgooglemapsdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.lifecycleScope

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.coursemapgooglemapsdemo.databinding.ActivityMapsBinding
import com.example.coursemapgooglemapsdemo.misc.CameraAndViewPort
import com.example.coursemapgooglemapsdemo.misc.TypeAndStyle
import com.google.android.gms.maps.model.MapStyleOptions
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Exception

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private val typeAndStyle by lazy {TypeAndStyle()}
    private val cameraAndViewPort by lazy {CameraAndViewPort()}

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
        val dutaFutsal = LatLng(-6.374543175689895, 106.84689141560838)
        val dutaFutsalMarker = map.addMarker(MarkerOptions().position(dutaFutsal).title("Marker in duta futsa"))
//        map.addMarker(MarkerOptions().position(aulaGrandCherry).title("Lokasi aula grand cherry"))
//        map.moveCamera(CameraUpdateFactory.newLatLng(aulaGrandCherry))
        // ini untuk agar zoom default
//        map.moveCamera(CameraUpdateFactory.newLatLngZoom(aulaGrandCherry, 15f))
        // menggunakan fitue seperti tampilan di camera atau terlihat 3D
        map.moveCamera(CameraUpdateFactory.newCameraPosition(cameraAndViewPort.aulaGrandCherry))

        // modifikasi agar bisa zoom in dan out
        map.uiSettings.apply {
            isZoomControlsEnabled = true
            isZoomGesturesEnabled = false
            isScrollGesturesEnabled = false
        }

        // set padding untuk mengukur ukuran map
//        map.setPadding(0,0,300,0)

        //menambahkan style pada maps
        typeAndStyle.setMapStyle(map, applicationContext)

//        map.setMinZoomPreference(15f)
//        map.setMaxZoomPreference(17f)
//        lifecycleScope.launch {
//            delay(2000L)
//            map.moveCamera(CameraUpdateFactory.zoomBy(3f))
//        }

        // pergantian lokasi dengan menunnggu waktu delay 4 detik dan menggunakan bounce
        lifecycleScope.launch {
            delay(4000L)
//            map.moveCamera(CameraUpdateFactory.newLatLngBounds(cameraAndViewPort.univGunadarmaBounds,0))
//            map.setLatLngBoundsForCameraTarget(cameraAndViewPort.univGunadarmaBounds)

            // mengunakan animasi ketika perpindahan lokasi dengan cara movement
//            map.animateCamera(CameraUpdateFactory.newLatLngBounds(cameraAndViewPort.univGunadarmaBounds,0), 2000, null)
            // animasi zoom in dengang mengunakan animasi
//            map.animateCamera(CameraUpdateFactory.zoomTo(15f), 2000, null)

//            map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraAndViewPort.univGunadarma), 2000, object : GoogleMap.CancelableCallback{
//                override fun onFinish() {
//                    Toast.makeText(applicationContext, "Finish", Toast.LENGTH_LONG).show()
//                }
//                override fun onCancel() {
//                    Toast.makeText(applicationContext, "Cancel", Toast.LENGTH_LONG).show()
//                }
//            })

            // menghapus lokasi marker
//            dutaFutsalMarker.remove()

        }

        // pengaktifan single click and long click
//        onMapClicked()
//        onMapLongClicked()
    }

    private fun onMapClicked(){
        map.setOnMapClickListener {
            Toast.makeText(applicationContext, "Single click", Toast.LENGTH_LONG).show()
        }
    }

    private fun onMapLongClicked(){
        map.setOnMapLongClickListener {
            Toast.makeText(applicationContext, "${it.longitude} - ${it.longitude}", Toast.LENGTH_LONG).show()
            map.addMarker(MarkerOptions().position(it).title("New marker position"))
        }
    }
}