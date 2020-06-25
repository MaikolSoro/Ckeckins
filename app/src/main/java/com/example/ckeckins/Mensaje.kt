package com.example.ckeckins

import android.content.Context
import android.widget.Toast

class Mensaje {

    companion object{

        fun mensaje(context: Context, mensaje: Mensajes) {
            var str = ""
            when(mensaje) {
               Mensajes.RATIONALE->{
                   str = "Requiero permisos para obtener ubicacion"
               }
            }
        }
        fun mensajeError(context: Context, error: Errores){
            var mensaje = ""
            when (error){
                Errores.NO_HAY_RED->{
                    mensaje = "No hay una conexión disponible"
                }
                Errores.HTTP_ERROR->{
                    mensaje = "HUBO UN PROBLEMA EN LA SOLICITUD. INTENTA MÁS TARDE"
                }
                Errores.NO_HAY_APP_FSQR->{
                    mensaje = "No tienes instalada la app de Foursquare"
                }
                Errores.ERROR_CONEXION_FSQR->{
                    mensaje = "No se pudo completar la conexion a Foursquare"
                }
                Errores.ERROR_INTERCAMBIO_TOKEN->{
                    mensaje = "No se pudo completar el intercambio de token en Foursquare"
                }
                Errores.ERROR_GUARDAR_TOKEN->{
                    mensaje = "No se pudo guardar el token"
                }
            }
            Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show()
        }
    }
}