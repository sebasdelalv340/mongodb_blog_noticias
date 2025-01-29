package org.example.com.es.gestores

import com.mongodb.client.MongoCollection
import com.mongodb.client.model.Filters
import org.example.com.es.connection.MongoConection
import org.example.com.es.model.Estado
import org.example.com.es.model.Usuario

class GestorMongoUsuario {
    private val collection: MongoCollection<Usuario> = MongoConection.getCollection(
        "adaprueba",
        "collUsuarios",
        Usuario::class.java
    )

    private fun userExists(username: String): Boolean {
        val filtro = Filters.eq("nick", username)
        try {
            return if(collection.find(filtro) != null) true else false
        } catch (e: Exception) {
            println(e.message)
        }
        return false
    }

    fun stateUser(username: String): Boolean {
        val filtro = Filters.eq("nick", username)
        val user = collection.find(filtro).first()
        try {
            if (user != null) {
                return user.estado == Estado.ACTIVO
            }
        } catch (e: Exception) {
            println(e.message)
        }
        return false
    }

    fun insertUser(user: Usuario) {
        try {
            if (!userExists(user.nick)) {
                collection.insertOne(user)
                println("Usuario insertado")
            } else {
                println("El usuario ya existe.")
            }
        } catch (e: Exception) {
            println(e.message)
        }

    }
}