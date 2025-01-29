package org.example

import org.example.com.es.gestores.GestorMongoComentario
import org.example.com.es.gestores.GestorMongoNoticia
import org.example.com.es.gestores.GestorMongoUsuario
import org.example.com.es.model.Direccion
import org.example.com.es.model.Estado
import org.example.com.es.model.Noticia
import org.example.com.es.model.Usuario
import java.time.Instant
import java.util.*

fun main() {
    val gestorMongoNoticia = GestorMongoNoticia()
    val gestorMongoUsuario = GestorMongoUsuario()
    val gestorMongoComentario = GestorMongoComentario()

    val direccion = Direccion("alamo", "24", "12", "29003","Mojacar")
    val usuario = Usuario("maria@gmail.com", "Maria", "mar14", Estado.ACTIVO, direccion, listOf("965123578", "687459258"))
    val noticia = Noticia(null,"El Alberti está que arde", "IES Rafael ALberti es el instituto más caliente de Cádiz", Date.from(
        Instant.now()), usuario, listOf("Actualidad")
    )
    val user2 = Usuario("antonio@gmail.com", "Antonio", "mar14", Estado.ACTIVO, direccion, listOf("958746532", "722589634"))

    //gestorMongoNoticia.insertNoticia(noticia)
    gestorMongoUsuario.insertUser(user2)

}