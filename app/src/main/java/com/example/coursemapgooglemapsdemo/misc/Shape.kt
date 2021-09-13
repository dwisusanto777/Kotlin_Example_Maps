package com.example.coursemapgooglemapsdemo.misc

import android.R
import android.graphics.Color
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.*
import kotlinx.coroutines.delay

class Shape {
    private val aulaGrandCherry = LatLng(-6.3808608913118645, 106.88091887301215)
    private val jiaecBcb = LatLng(-6.377304831688866, 106.84712749985857)
    private val khutab = LatLng(-6.37719813220062, 106.84652663537027)
    val dutaFutsal = LatLng(-6.374543175689895, 106.84689141560838)

    private val p0 = LatLng(-6.132553820299358, 106.69100057179182)
    private val p1 = LatLng(-6.138131293338081, 106.94735944874793)
    private val p2 = LatLng(-6.430307939017842, 106.96474921939483)
    private val p3 = LatLng(-6.401320688124353, 106.56758929623307)

    private val p00 = LatLng(-6.037030924238269, 106.5560816604117)
    private val p01 = LatLng(-6.060893476892686, 107.61443849581522)
    private val p02 = LatLng(-6.653340396481007, 107.37321396650488)
    private val p03 = LatLng(-6.659612607032548, 106.3388532884673)

    suspend fun addPolyLine(googleMap: GoogleMap){
        val pattern = listOf(Dot(), Gap(30f), Dash(50f))
        val polyLine = googleMap.addPolyline(
            PolylineOptions().apply {
                add(aulaGrandCherry, jiaecBcb)
                width(50f)
                color(Color.BLUE)
                geodesic(true)
                clickable(true)
                pattern(pattern)
                startCap(RoundCap())
                endCap(ButtCap())
            }
        )
        delay(5000)
        val newList = listOf<LatLng>(
            aulaGrandCherry, khutab, jiaecBcb
        )
        polyLine.points = newList
    }

    fun addPolygon(googleMap: GoogleMap){
        val polygon = googleMap.addPolygon(
            PolygonOptions().apply {
                add(p0,p1,p2,p3)
                fillColor(R.color.black)
                strokeColor(R.color.black)
                addHole(listOf(p00,p01,p02,p03))
            }
        )
    }

    fun addCircle(googleMap: GoogleMap){
        val polygon = googleMap.addCircle(
            CircleOptions().apply {
                center(aulaGrandCherry)
                radius(50000.0)
                fillColor(R.color.holo_purple)
                strokeColor(R.color.holo_purple)
            }
        )
    }
}