package org.example.com.es.service

import org.example.com.es.gestores.RepositoryComentario
import org.example.com.es.model.Comentario

class ServiceComentario {
    private val repositoryComentario = RepositoryComentario()

    fun insertComentario(comentario: Comentario){
        repositoryComentario.insertComentario(comentario)
    }

    fun getComentarioByNoticia(noticia: String): List<Comentario>?{
        if (repositoryComentario.getComentarioByNoticia(noticia).isNullOrEmpty()) {
            println("No se encontraron comentarios")
            return null
        } else {
            return repositoryComentario.getComentarioByNoticia(noticia)
        }
    }
}