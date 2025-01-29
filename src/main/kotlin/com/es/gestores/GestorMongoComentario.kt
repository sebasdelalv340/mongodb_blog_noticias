package org.example.com.es.gestores

import com.mongodb.client.MongoCollection
import com.mongodb.client.model.Filters
import org.example.com.es.connection.MongoConection
import org.example.com.es.model.Comentario

class GestorMongoComentario {
    private val collection: MongoCollection<Comentario> = MongoConection.getCollection(
        "adaprueba",
        "collComentarios",
        Comentario::class.java
    )

    fun getComentByNoticia(noticia: String): List<Comentario>? {
        val filtro = Filters.eq("noticia.titulo", noticia)
        try {
            val comentarios = collection.find(filtro)
            comentarios.forEach { println(it) }
            return comentarios.toList()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

}