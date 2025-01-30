package org.example.com.es.service

import org.example.com.es.gestores.RepositoryUsuario
import org.example.com.es.model.Usuario
import org.example.com.es.usecase.validarUsernameEmail

class ServiceUsuario {
    val repositoryUsuario = RepositoryUsuario()

    private fun existUser(user: Usuario): Boolean {
        val username = repositoryUsuario.usernameExist(user.username)
        val email = repositoryUsuario.emailExist(user._id)

        return !validarUsernameEmail(username, email)
    }

    fun insertUser(user: Usuario) {
        if (existUser(user)) {
            repositoryUsuario.insertUser(user)
            println("Usuario registrado")
        } else {
            println("Ya existe un usuario con el mismo nick")
        }
    }

    fun getStateUser(user: Usuario): Boolean {
        return repositoryUsuario.stateUser(user.username)
    }
}