package org.example.com.es.usecase

import org.example.com.es.service.ServiceUsuario

fun validarUsername(username: String): Boolean {
    val serviceUsuario = ServiceUsuario()
    return if (serviceUsuario.usernameExist(username)) {
        println("Ya existe un usuario con ese username.")
        true
    } else {
        false
    }
}