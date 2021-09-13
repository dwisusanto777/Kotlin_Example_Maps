package com.example.coursemapgooglemapsdemo.misc

import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds

class CameraAndViewPort {

    val aulaGrandCherry:CameraPosition = CameraPosition.Builder()
        .target(LatLng(-6.3808608913118645, 106.88091887301215))
        .zoom(17f)
        .bearing(0f)
        .tilt(45f)
        .build()

    val univGunadarma:CameraPosition = CameraPosition.Builder()
        .target(LatLng(-6.368998644071463, 106.83684922571267))
        .zoom(17f)
        .bearing(0f)
        .tilt(45f)
        .build()

    val univGunadarmaBounds = LatLngBounds(
        LatLng(-6.368998644071463, 106.83684922571267),//lokasi UI
        LatLng(-6.377304831688866, 106.84712749985857) // Lokasi JIAEC
    )

}