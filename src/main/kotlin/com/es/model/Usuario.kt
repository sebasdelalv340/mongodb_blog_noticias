package org.example.com.es.model

import org.bson.codecs.pojo.annotations.BsonProperty

data class Usuario(
    val _id: String?, //EMAIL
    val nombre: String,
    val username: String,
    val estado: Estado? = Estado.ACTIVO,
    val direccion: Direccion?,
    @BsonProperty("telefonos")
    val telefonos: List<String>
)