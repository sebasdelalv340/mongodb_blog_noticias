package org.example.com.es.model

import java.time.Instant
import java.util.*

data class Comentario(
    val username: String,
    val noticia: Noticia?,
    val texto: String,
    val fecha_pub: Date? = Date.from(Instant.now())
)
