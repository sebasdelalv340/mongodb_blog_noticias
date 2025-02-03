package org.example.com.es.gestores

import com.mongodb.client.MongoCollection
import com.mongodb.client.model.Filters
import com.mongodb.client.model.Sorts
import org.example.com.es.connection.MongoConection
import org.example.com.es.model.Noticia

class RepositoryNoticia() {
    private val collection: MongoCollection<Noticia> = MongoConection.getCollection(
        "adaprueba",
        "collNoticias",
        Noticia::class.java
    )

    fun insertNoticia(noticia: Noticia) {
        try {
            collection.insertOne(noticia)
            println("Noticia insertada")
        } catch (e: Exception) {
            println(e.message)
        }
    }

    fun getNoticiaByNick(username: String): List<Noticia>? {
        val filtro = Filters.eq("autor", username)
        try {
            return collection.find(filtro).toList()
        } catch (e: Exception) {
            println(e.message)
            return null
        }
    }

    fun getNoticiaByTag(tag: String): List<Noticia>? {
        val filtro = Filters.regex("tag", ".*$tag.*")
        try {
            return collection.find(filtro).toList()
        } catch (e: Exception) {
            println(e.message)
        }
        return null
    }

    fun lastNoticias(): List<Noticia>? {
        try {
            return collection.find()
                .sort(Sorts.descending("fecha_publicacion"))
                .limit(10)
                .toList()
        } catch (e: Exception) {
            println(e.message)
        }
        return null
    }

    fun getNoticiaByTitulo(title: String): Noticia? {
        val filtro = Filters.regex("titulo", ".*$title.*")
        try {
            return collection.find(filtro).first()
        } catch (e: Exception) {
            println(e.message)
        }
        return null
    }


}