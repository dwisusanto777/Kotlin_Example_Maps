package com.example.coursemapgooglemapsdemo.misc

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.TextView
import com.example.coursemapgooglemapsdemo.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker

class CustomInfoAdapter(context: Context) : GoogleMap.InfoWindowAdapter {

    private val contentView = (context as Activity).layoutInflater.inflate(R.layout.custom_window_inf, null)
    override fun getInfoWindow(marker: Marker): View? {
        renderViews(marker, contentView)
        return contentView;
    }

    override fun getInfoContents(marker: Marker): View? {
        renderViews(marker, contentView)
        return contentView;
    }

    private fun renderViews(marker: Marker?, contentView:View){
        val title = marker?.title
        val description = marker?.snippet

        val titleTextView = contentView.findViewById<TextView>(R.id.textView)
        titleTextView.text = title

        val descTextView = contentView.findViewById<TextView>(R.id.textView2)
        descTextView.text = description
    }
}