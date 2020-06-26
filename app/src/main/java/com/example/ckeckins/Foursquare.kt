package com.example.ckeckins

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.foursquare.android.nativeoauth.FoursquareOAuth
import com.google.gson.Gson
import org.json.HTTP

class Foursquare(var activity: AppCompatActivity, var activityDestino: AppCompatActivity) {

    private val CODIGO_CONEXION = 200
    private val CODIGO_INTERCAMBIO_TOKEN = 201

    private val CLIENT_ID = ""
    private val CLIENT_SECRET = ""

    private  val SETTINGS = "settings"
    private  val ACCESS_TOKEN = "accessToken"

    private val URL_BASE = "https://api.foursquare.com/v2/"
    private val VERSION = "v=20200120"
    init {

    }

    fun iniciarSeccion() {
        val intent = FoursquareOAuth.getConnectIntent(activity.applicationContext, CLIENT_ID)

        if(FoursquareOAuth.isPlayStoreIntent(intent)){
            Mensaje.mensajeError(activity.applicationContext, Errores.NO_HAY_APP_FSQR)
            activity.startActivity(intent)
        } else {
            activity.startActivityForResult(intent, CODIGO_CONEXION)
        }
    }
        public  fun validarActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
         when(requestCode) {
             CODIGO_CONEXION->{ conexionCompleta( resultCode, data)}
             CODIGO_INTERCAMBIO_TOKEN->{intercambioTokenCompleta(resultCode, data)}
             }
         }
        private fun conexionCompleta(resultCode: Int, data: Intent?){
        val codigoRespuesta = FoursquareOAuth.getAuthCodeFromResult(resultCode, data)
        val exception = codigoRespuesta.exception

        if(exception == null) {
            val codigo = codigoRespuesta.code
            realizarIntercambioToken(codigo)
        } else {
            Mensaje.mensajeError(activity.applicationContext, Errores.NO_HAY_APP_FSQR)
        }
    }

   private fun realizarIntercambioToken(codigo: String){
        val intent = FoursquareOAuth.getTokenExchangeIntent(activity.applicationContext, CLIENT_ID, CLIENT_SECRET, codigo)
       activity.startActivityForResult(intent, CODIGO_INTERCAMBIO_TOKEN)

    }
    private fun intercambioTokenCompleta(resultCode: Int, data: Intent?) {
        val respuestaToken = FoursquareOAuth.getTokenFromResult(resultCode, data)
        val exception = respuestaToken.exception

        if(exception == null) {
            val accesToken = respuestaToken.accessToken
            if(!guardarToken(accesToken)){
                Mensaje.mensajeError(activity.applicationContext, Errores.ERROR_GUARDAR_TOKEN)
            } else {
                navegarSiguienteActividad()
            }
        } else {
            Mensaje.mensajeError(activity.applicationContext, Errores.ERROR_INTERCAMBIO_TOKEN)
        }
    }

     fun hayToken():Boolean {

        if(obtenerToken() == "") {
            return false
        } else {
            return true
        }
    }

    fun obtenerToken(): String? {
        val settings = activity.getSharedPreferences(SETTINGS, 0)
        val token = settings.getString(ACCESS_TOKEN, "")
        return token
    }

    private  fun guardarToken(token: String): Boolean {
        if(token.isEmpty()) {
            return false
        }
        val settings = activity.getSharedPreferences(SETTINGS, 0)
        val editor  = settings.edit()

        editor.putString(ACCESS_TOKEN, token)
        editor.apply()
        return true
    }

    public fun navegarSiguienteActividad() {
        activity.startActivity(Intent(this.activity,activityDestino:: class.java))
        activity.finish()
    }

    fun obtenerVenues(lat:String, lon:String, obtenerVenuesInterface: ObtenerVenuesInterface) {
         val network = Network(activity)
         val seccion = "venues/"
         val metodo  = "search/"
         val ll = "ll=" + lat + "," + lon
         val token = "oauth_token=" + obtenerToken()
        // url creada para obtener los datos http
         val url = URL_BASE + seccion + metodo + "?" + ll + "&" + token + " &" + VERSION

        network.httpRequest(activity.applicationContext, url,object: HttpResponse{
            override fun httpResponseSuccess(response: String) {
                var gson = Gson()
                var objetoRespuesta = gson.fromJson(response, FoursquareApi::class.java)
                var meta = objetoRespuesta.meta
                var venues = objetoRespuesta.response?.venues!!

                if(meta?.code == 200) {
                    // mandar un mensaje de que se completó el query correctamente
                    obtenerVenuesInterface.venuesGenerados(venues)
                } else {
                    if(meta?.code == 400) {
                        // problema de coordenadas
                        Mensaje.mensajeError(activity.applicationContext, meta?.erroresDetail)
                    } else {
                        // mostrar un mensaje genérico

                        Mensaje.mensajeError(activity.applicationContext, Errores.ERROR_QUERY)
                    }
                }
            }
        })
    }
}