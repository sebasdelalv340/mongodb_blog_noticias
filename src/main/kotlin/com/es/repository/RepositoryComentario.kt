package org.example.com.es.gestores

import com.mongodb.client.MongoCollection
import com.mongodb.client.model.Filters
import org.example.com.es.connection.MongoConection
import org.example.com.es.model.Comentario

class RepositoryComentario {
    private val collection: MongoCollection<Comentario> = MongoConection.getCollection(
        "adaprueba",
        "collComentarios",
        Comentario::class.java
    )

    fun insertComentario(comentario: Comentario) {
        try {
            collection.insertOne(comentario)
            println("Comentario registrado")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getComentarioByNoticia(noticia: String): List<Comentario>? {
        val filtro = Filters.regex("noticia.titulo", ".*$noticia.*")
        try {
            return collection.find(filtro).toList()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

}