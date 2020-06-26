package com.example.ckeckins

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.style.ClickableSpan
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.ckeckins.RecyclerViewPrincipal.AdaptadorCustom
import com.example.ckeckins.RecyclerViewPrincipal.ClickListener
import com.example.ckeckins.RecyclerViewPrincipal.LongClickListener
import com.google.android.gms.location.LocationResult

class PantallaPrincipal : AppCompatActivity() {
    var ubicacion:Ubicacion? = null
    var foursquare: Foursquare? = null
    var lista: RecyclerView? = null
    var adaptador: AdaptadorCustom? = null
    var layoutManager: RecyclerView.LayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_principal)

        foursquare = Foursquare(this, this)

        lista = findViewById(R.id.rvLugares)
        lista?.setHasFixedSize(true)

        layoutManager = LinearLayoutManager( this)
        lista?.layoutManager = layoutManager

        if(foursquare?.hayToken()!!){
            ubicacion = Ubicacion(this, object: UbicacionListener{
                override fun ubicacionResponse(locationResult: LocationResult) {
                    val lat = locationResult.lastLocation.latitude.toString()
                    val lon = locationResult.lastLocation.longitude.toString()
                    foursquare?.obtenerVenues(lat, lon, object: ObtenerVenuesInterface{
                        override fun venuesGenerados(venues: ArrayList<Venue>) {
                            implementacionRcylerView(venues)
                            for (venue in venues) {

                            }
                        }
                    })
                }
            })

        }

    }

    private   fun implementacionRcylerView(lugares: ArrayList<Venue>) {
        adaptador = AdaptadorCustom( lugares, object: ClickListener {
            override fun onClick(vista: View, index: Int) {
                Toast.makeText(applicationContext, lugares.get(index).name, Toast.LENGTH_SHORT)
                    .show()
            }
        }, object: LongClickListener {
            override fun longClick(vista: View, index: Int) {
                TODO("Not yet implemented")
            }


        })
        //lista?.adapter = adaptador
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        ubicacion?.onRequestPermissionsResult(requestCode,permissions, grantResults)
    }

    override fun onStart() {
        super.onStart()
        ubicacion?.inicializarUbicación()
    }

    override fun onPause() {
        super.onPause()
        ubicacion?.detenerActualizacionUbicacion()
    }
}