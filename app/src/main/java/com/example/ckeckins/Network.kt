package com.example.ckeckins

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class Network (var activity: AppCompatActivity) {

    fun hayRed(): Boolean {
        val connectivityManager = activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected

    }
    fun httpRequest(context: Context, url:String, httpResponse: HttpResponse ) {
        if(hayRed()){

            val queue = Volley.newRequestQueue(context)
            val solicitud = StringRequest(Request.Method.GET,url , Response.Listener<String>{
                    response ->
                httpResponse.httpResponseSuccess(response)
            }, Response.ErrorListener {
                    error ->
                Log.d("HTTP_REQUEST", error.message.toString())
                Toast.makeText(context, "HUBO UN PROBLEMA EN LA SOLICITUD. INTENTA MÁS TARDE", Toast.LENGTH_LONG).show()
            })
        } else {
            Toast.makeText(context, "No se detecta ninguna conexión disponible", Toast.LENGTH_LONG).show()
        }
    }


}