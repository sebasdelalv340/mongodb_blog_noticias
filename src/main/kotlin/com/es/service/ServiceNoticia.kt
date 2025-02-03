package org.example.com.es.service

import org.example.com.es.gestores.RepositoryNoticia
import org.example.com.es.model.Noticia

class ServiceNoticia {
    private val repositoryNoticia = RepositoryNoticia()

    fun insertNoticia(noticia: Noticia) {
        repositoryNoticia.insertNoticia(noticia)
    }

    fun getNoticiaByUsername(username: String): List<Noticia>? {

        if (repositoryNoticia.getNoticiaByNick(username).isNullOrEmpty()) {
            println("No se han encontrado noticia con este nombre de usuario")
            return null
        } else {
            return repositoryNoticia.getNoticiaByNick(username)
        }
    }

    fun getNoticiaByTag(tag: String): List<Noticia>? {
        if (repositoryNoticia.getNoticiaByTag(tag).isNullOrEmpty()) {
            println("No se han encontrado noticia con este tag")
            return null
        } else {
            return repositoryNoticia.getNoticiaByTag(tag)
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

    fun getNoticiaByTitulo(titulo: String): Noticia? {
        if (repositoryNoticia.getNoticiaByTitulo(titulo) != null) {
            return repositoryNoticia.getNoticiaByTitulo(titulo)
        } else {
            println("No se han encontrado noticias")
            return null
        }
    }
}