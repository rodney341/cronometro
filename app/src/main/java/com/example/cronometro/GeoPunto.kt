package com.example.cronometro



data class GeoPunto(var longitud: Double, var latitud: Double) {
    companion object {
        val SIN_POSICION = GeoPunto(0.0,0.0)
    }
}

