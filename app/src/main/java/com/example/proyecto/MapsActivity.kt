package com.example.proyecto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.map_view)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val santaFeBogota = LatLng(4.595572, -74.065116)
        mMap.addMarker(MarkerOptions().position(santaFeBogota).title("Marcador en la Localidad de Santa Fe"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(santaFeBogota, 14f))

        searchNearbyParking(santaFeBogota)

        mMap.setInfoWindowAdapter(CustomInfoWindowAdapter(this))
    }

    private fun searchNearbyParking(location: LatLng) {
        val apiKey = "AIzaSyA0HZNYtvVnSH94M2RTNwqijM1OKxOKozE"
        val radius = 1000 // Radio de búsqueda en metros
        val type = "parking" // Tipo de lugar a buscar
        val url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=${location.latitude},${location.longitude}&radius=$radius&type=$type&key=$apiKey"

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            { response ->
                val results = response.getJSONArray("results")
                for (i in 0 until results.length()) {
                    val result = results.getJSONObject(i)
                    val geometry = result.getJSONObject("geometry")
                    val location = geometry.getJSONObject("location")
                    val lat = location.getDouble("lat")
                    val lng = location.getDouble("lng")
                    val name = result.getString("name")
                    val parkingLatLng = LatLng(lat, lng)

                    mMap.addMarker(MarkerOptions().position(parkingLatLng).title(name))
                }
            },
            { error ->
                Log.e("MapsActivity", "Error al buscar estacionamientos cercanos", error)
            })

        // Agrega la solicitud a la cola de solicitudes
        Volley.newRequestQueue(this).add(jsonObjectRequest)
    }
}
