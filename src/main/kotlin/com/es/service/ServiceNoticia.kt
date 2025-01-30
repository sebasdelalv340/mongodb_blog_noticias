package org.example.com.es.service

import org.example.com.es.gestores.RepositoryNoticia
import org.example.com.es.model.Noticia

class ServiceNoticia {
    private val repositoryNoticia = RepositoryNoticia()

    fun insertNoticia(noticia: Noticia) {
        repositoryNoticia.insertNoticia(noticia)
    }

    fun getNoticiaByUsername(username: String): List<Noticia>? {
        if (repositoryNoticia.getNoticiaByNick(username) != null) {
            return repositoryNoticia.getNoticiaByNick(username)
        } else {
            println("No se han encontrado noticia con este nombre de usuario")
            return null
        }
    }

    fun getNoticiaByTag(tag: String): List<Noticia>? {
        if (repositoryNoticia.getNoticiaByTag(tag) != null) {
            return repositoryNoticia.getNoticiaByTag(tag)
        } else {
            println("No se han encontrado noticia con este tag")
            return null
        }
    }

    fun lastNoticias(): List<Noticia>? {
        if (repositoryNoticia.lastNoticias() != null) {
            return repositoryNoticia.lastNoticias()
        } else {
            println("No se han encontrado noticias")
            return null
        }
    }
}