package org.example.com.es.service

import org.example.com.es.gestores.RepositoryUsuario
import org.example.com.es.model.Usuario

class ServiceUsuario {
    private val repositoryUsuario = RepositoryUsuario()

    fun usernameExist(username: String): Boolean {
        return repositoryUsuario.usernameExist(username) != null
    }

    fun emailExist(email: String): Boolean {
        return repositoryUsuario.emailExist(email) != null
    }

    fun insertUser(user: Usuario) {
        repositoryUsuario.insertUser(user)
    }

    fun getStateUser(user: String): Boolean {
        return repositoryUsuario.stateUser(user)
    }
}