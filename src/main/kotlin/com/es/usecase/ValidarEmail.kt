package org.example.com.es.usecase

import org.example.com.es.service.ServiceUsuario

fun validarEmail(email: String): Boolean {
    val serviceUsuario = ServiceUsuario()
    return if (serviceUsuario.emailExist(email)) {
        println("Ya existe un usuario con ese email.")
        true
    } else {
        false
    }
}