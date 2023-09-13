package com.example.cronometro



class MyDatabaseHelper : RepositorioLugares {

    val listaLugares= mutableListOf<GeoPunto>()

    override fun elemento(id: Int): GeoPunto {
        return listaLugares[id]
    }

    override fun guardar(lugar: GeoPunto) {
        listaLugares.add(lugar)
    }

    override fun nuevo(): Int {
        val lugar = GeoPunto(1.2312,3.32432)
        listaLugares.add(lugar)
        return listaLugares.size - 1
    }

    override fun borrar(id: Int) {
        listaLugares.removeAt(id)
    }

    override fun tamaño(): Int {
        return listaLugares.size
    }

    override fun actualiza(id: Int, lugar: GeoPunto) {
        listaLugares[id] = lugar
    }
}

