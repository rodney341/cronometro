package com.example.cronometro

interface RepositorioLugares {

    fun elemento(id: Int): GeoPunto  //Devuelve el elemento dado su id
    fun guardar(geopunto: GeoPunto)      //Añade el elemento indicado
    fun nuevo(): Int       //Añade un elemento en blanco y devuelve su id
    fun borrar(id: Int)    //Elimina el elemento con el id indicado
    fun tamaño(): Int     //Devuelve el número de elementos
    fun actualiza(id: Int, lugar: GeoPunto)  //Reemplaza un elemento

    fun añadeEjemplos() {
        guardar(GeoPunto(-0.166093, 38.995656))
        guardar(GeoPunto(-0.266093, 38.995656))
        guardar(GeoPunto(-0.366093, 38.995656))
        guardar(GeoPunto(-0.466093, 38.995656))
        guardar(GeoPunto(-0.566093, 38.995656))
        guardar(GeoPunto(-0.666093, 38.995656))
    }
}