package org.example.com.es.model

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.codecs.pojo.annotations.BsonProperty
import java.util.*

data class Noticia(
    @BsonId
    val _id: String?,
    val titulo: String,
    val cuerpo: String,
    val fecha_publicacion: Date?,
    val autor: Usuario,
    @BsonProperty("tag")
    val tag: List<String>,
) {
}