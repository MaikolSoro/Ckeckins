package com.example.ckeckins

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class login : AppCompatActivity() {

    var foursquare: Foursquare? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val blogin = findViewById<Button>(R.id.login)
        foursquare = Foursquare(this, PantallaPrincipal())

        if(foursquare?.hayToken()!!) {
            foursquare?.navegarSiguienteActividad()
        }
        blogin.setOnClickListener{
            foursquare?.iniciarSeccion()

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        foursquare?.validarActivityResult(requestCode, resultCode, data)

    }
}