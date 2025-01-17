package com.example.ckeckins

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
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
                Mensaje.mensajeError(context, Errores.HTTP_ERROR)
            })
            queue.add(solicitud)
        } else {
           Mensaje.mensajeError(context, Errores.NO_HAY_RED)
        }
    }


}