package com.example.ckeckins

import android.content.Context
import android.widget.Toast

class Mensaje {

    companion object{
        fun mensajeError() {

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
            }
            Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show()
        }
    }
}