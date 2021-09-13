package com.example.coursemapgooglemapsdemo.misc

import com.example.coursemapgooglemapsdemo.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.*

class Overlays {
    private val aulaGrandCherry = LatLng(-6.3808608913118645, 106.88091887301215)
    private val jiaecBcb = LatLng(-6.377304831688866, 106.84712749985857)
    private val khutab = LatLng(-6.37719813220062, 106.84652663537027)
    val dutaFutsal = LatLng(-6.374543175689895, 106.84689141560838)
    private val aulaGrandCherryBounds = LatLngBounds(
        LatLng(-6.377304831688866, 106.84712749985857),
        LatLng(-6.377304831688866, 106.84712749985857)
    )

    fun addGroundOverlays(googleMap: GoogleMap): GroundOverlay{
        val groundOverlay = googleMap.addGroundOverlay(
            GroundOverlayOptions().apply {
                position(aulaGrandCherry, 10000f, 10000f)
                image(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher_background))
            }
        )
        return groundOverlay
    }

    fun addGroundOverlaysWithTag(googleMap: GoogleMap): GroundOverlay{
        val groundOverlay = googleMap.addGroundOverlay(
            GroundOverlayOptions().apply {
                position(aulaGrandCherry, 10000f, 10000f)
                image(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher_background))
            }
        )
        groundOverlay.tag = "My Yag"
        return groundOverlay
    }

}