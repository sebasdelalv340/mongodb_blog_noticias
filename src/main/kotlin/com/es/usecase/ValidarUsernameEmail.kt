package org.example.com.es.usecase

import org.example.com.es.model.Usuario

fun validarUsernameEmail(username: Usuario?, email: Usuario?): Boolean{
    if (username != null && email != null) {
        return true
    } else if (username == null) {
        println("Ya exutes un usuario con este nombre de usuario")
        return false
    } else if (email == null) {
        println("Ya existe un usuario con este email")
        return false
    } else {
        return true
    }
}