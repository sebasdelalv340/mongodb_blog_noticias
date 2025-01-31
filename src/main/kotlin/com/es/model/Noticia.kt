package org.example.com.es.model

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.codecs.pojo.annotations.BsonProperty
import org.bson.types.ObjectId
import java.time.Instant
import java.util.*

data class Noticia(
    @BsonId
    val _id: ObjectId?,
    val titulo: String,
    val cuerpo: String,
    val fecha_publicacion: Date? = Date.from(Instant.now()),
    val autor: String,
    @BsonProperty("tag")
    val tag: List<String>,
) {
}