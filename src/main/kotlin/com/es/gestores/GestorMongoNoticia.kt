package org.example.com.es.gestores

import com.mongodb.client.MongoCollection
import com.mongodb.client.model.Filters
import com.mongodb.client.model.Sorts
import org.example.com.es.connection.MongoConection
import org.example.com.es.model.Noticia

class GestorMongoNoticia() {
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

    fun getNoticiaUser(username: String): List<Noticia>? {
        val filtro = Filters.eq("autor.nick", username)
        try {
            collection.find(filtro).forEach { println(it) }
            return collection.find(filtro).toList()
        } catch (e: Exception) {
            println(e.message)
        }
        return null
    }

    fun getNoticiaByTag(tag: String): List<Noticia>? {
        val filtro = Filters.eq("tag", tag)
        try {
            collection.find(filtro).forEach { println(it) }
        } catch (e: Exception) {
            println(e.message)
        }
        return null
    }

    fun lastNoticias(): List<Noticia>? {
        try {
            val noticias = collection.find()
                .sort(Sorts.descending("fecha_publicacion"))
                .limit(10)
                .toList()
            noticias.forEach { println(it) }
            return noticias
        } catch (e: Exception) {
            println(e.message)
        }
        return null
    }


}