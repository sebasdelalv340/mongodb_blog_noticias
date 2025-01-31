package org.example.com.es.menu

import org.example.com.es.model.Comentario
import org.example.com.es.model.Direccion
import org.example.com.es.model.Noticia
import org.example.com.es.model.Usuario
import org.example.com.es.service.ServiceComentario
import org.example.com.es.service.ServiceNoticia
import org.example.com.es.service.ServiceUsuario

class MainMenu {
    private val serviceUsuario = ServiceUsuario()
    private val serviceNoticia = ServiceNoticia()
    private val serviceComentario = ServiceComentario()

    fun mainMenu() {
        while (true) {
            println("\nMenú:")
            println("1. Registrar usuario.")
            println("2. Publicar noticia.")
            println("3. Publicar comentario.")
            println("4. Listar noticias publicadas por usuario.")
            println("5. Listar comentarios de una noticia.")
            println("6. Buscar noticias por etiqueta.")
            println("7. Listar las 10 últimas noticias.")
            println("8. Salir.")
            print("Elige una opción: ")
            val opcion = readln().toIntOrNull()
            if (opcion in 1..8) {
                if (opcion == 8) {
                    println("Saliendo del programa...")
                    Thread.sleep(1500)
                    break
                }
                menuOpciones(opcion!!)
            } else {
                println("Opción no válida. Inténtalo de nuevo.")
            }
        }
    }

    private fun menuOpciones(opcion: Int) {
        when(opcion) {
            1 -> {registrarUsuario()}
            2 -> {publicarNoticia()}
            3 -> {publicarComentario()}
            4 -> {getNoticiaByUsername()}
            5 -> {getComentariosByNoticia()}
            6 -> {getNoticiasByTag()}
            7 -> {getLastNoticias()}
        }
    }

    private fun pedirUsuario(): Usuario {
        println("Introduce el email:")
        val email = readln()
        println("Introduce el nombre completo:")
        val nombre = readln()
        println("Introduce el nombre de usuario:")
        val username = readln().lowercase()

        val direccion = pedirDireccion()
        val listaTelefono = pedirTelefono()

        val user = Usuario(email, nombre, username, null, direccion, listaTelefono)
        return user
    }

    private fun pedirDireccion(): Direccion {
        println("Introduce la dirección")
        println("Calle:")
        val calle = readln()
        println("Número:")
        val num = readln()
        println("Puerta:")
        val puerta = readln()
        println("Código postal:")
        val cp = readln()
        println("Ciudad:")
        val ciudad = readln()
        return Direccion(
            calle,
            num,
            puerta,
            cp,
            ciudad
        )
    }

    private fun pedirTelefono(): List<String> {
        var bucle = true
        val listaTlf = mutableListOf<String>()

        println("Introduce un teléfono")
        val tlf = readln()
        listaTlf.add(tlf)

        do {
            println("¿Quieres introducir otro teléfono? (S/N):")
            val opcion = readln().lowercase()
            if (opcion == "s") {
                println("Teléfono:")
                val tlf2 = readln()
                listaTlf.add(tlf2)
            } else {
                bucle = false
            }
        } while (bucle)
        return listaTlf
    }

    private fun registrarUsuario() {
        serviceUsuario.insertUser(pedirUsuario())
    }

    private fun publicarNoticia() {
        var repetirUsername = true
        var username = ""
        do {
            println("Introduce tu nombre de usuario:")
            username = readln().lowercase()
            if (comprobarUsuario(username)) {
                repetirUsername = false
            } else {
                println("No existe un usuario registrado con este nombre de usuario")
                if (probarUser()) {
                    repetirUsername = true
                } else {
                    repetirUsername = false
                }
            }
        } while (repetirUsername)
        println("Introduce el titulo:")
        val titulo = readln()
        println("Introduce el cuerpo:")
        val cuerpo = readln()
        println("Introduce una etiqueta:")
        val etiqueta = pedirTags()

        serviceNoticia.insertNoticia(
            Noticia(null, titulo, cuerpo, null, username, etiqueta)
        )
    }

    private fun comprobarUsuario(username: String): Boolean {
        return serviceUsuario.usernameExist(username)
    }

    private fun probarUser(): Boolean {
        println("1. Probar otro usuario")
        println("2. Registrarme")
        val opcion = readln().toInt()
        when (opcion) {
            1 -> return true
            2 -> {
                registrarUsuario()
                return false
            }
        }
        return false
    }

    private fun pedirTags(): List<String> {
        var bucle = true
        val listaTags = mutableListOf<String>()

        println("Introduce una etiqueta")
        val tag = readln()
        listaTags.add(tag)

        do {
            println("¿Quieres introducir otra etiqueta? (S/N):")
            val opcion = readln().lowercase()
            if (opcion == "s") {
                println("Etiqueta:")
                val tag2 = readln()
                listaTags.add(tag2)
            } else {
                bucle = false
            }
        } while (bucle)
        return listaTags
    }

    private fun publicarComentario() {
        var repetirUsername = true
        var username = ""
        do {
            println("Introduce tu nombre de usuario:")
            username = readln().lowercase()
            if (comprobarActividadUser(username)) {
                repetirUsername = false
            } else {
                println("Este usuario está inactivo, no puede publicar comentarios")
                if (probarUser()) {
                    repetirUsername = true
                } else {
                    repetirUsername = false
                }
            }
        } while (repetirUsername)
        val noticia = getNoticiaByTitulo()
        println("Introduce el comentario:")
        val texto = readln()

        serviceComentario.insertComentario(
            Comentario(username, noticia, texto, null)
        )
    }

    private fun comprobarActividadUser(username: String): Boolean {
        return serviceUsuario.getStateUser(username)
    }

    private fun getNoticiaByTitulo(): Noticia? {
        var correcto = true
        val noticia: Noticia?
        do {
            println("Introduce el titulo de la noticia:")
            val titulo = readln()
            noticia = serviceNoticia.getNoticiaByTitulo(titulo)
            println(noticia)
            println("¿ES la noticia que buscas? (S/N):")
            val opcion = readln()
            if (opcion == "s") {
                correcto = false
                return noticia
            } else {
                println("Buscamos de nuevo")
                return null
            }
        } while (correcto)
    }

    private fun getNoticiaByUsername(): List<Noticia>? {
        println("Introduce el nombre de usuario:")
        val username = readln().lowercase()
        val listaNoticias = serviceNoticia.getNoticiaByUsername(username)
        listaNoticias?.toList()?.forEach { println(it) }
        return listaNoticias
    }

    private fun getComentariosByNoticia(): List<Comentario>? {
        println("Introduce el titulo de la noticia:")
        val titulo = readln()
        val listaComentarios = serviceComentario.getComentarioByNoticia(titulo)
        listaComentarios?.toList()?.forEach { println(it) }
        return listaComentarios
    }

    private fun getNoticiasByTag(): List<Noticia>? {
        println("Introduce el tag:")
        val tag = readln()
        val listaNoticias = serviceNoticia.getNoticiaByTag(tag)
        listaNoticias?.toList()?.forEach { println(it) }
        return listaNoticias
    }

    private fun getLastNoticias(): List<Noticia>? {
        val noticias = serviceNoticia.lastNoticias()
        noticias?.toList()?.forEach { println(it) }
        return noticias
    }
}