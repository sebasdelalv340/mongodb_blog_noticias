package org.example.com.es.gestores

import com.mongodb.client.MongoCollection
import com.mongodb.client.model.Filters
import org.example.com.es.connection.MongoConection
import org.example.com.es.model.Estado
import org.example.com.es.model.Usuario

class RepositoryUsuario {
    private val collection: MongoCollection<Usuario> = MongoConection.getCollection(
        "adaprueba",
        "collUsuarios",
        Usuario::class.java
    )

    fun usernameExist(username: String): Usuario? {
        val filtro = Filters.eq("username", username)
        try {
            val user = collection.find(filtro)
            return user.first()
        } catch (e: Exception) {
            println(e.message)
            return null
        }
    }

    fun emailExist(email: String?): Usuario? {
        val filtro = Filters.eq("_id", email)
        try {
            return collection.find(filtro).first()
        } catch (e: Exception) {
            println(e.message)
            return null
        }
    }

    fun stateUser(username: String): Boolean {
        val filtro = Filters.eq("username", username)
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
            collection.insertOne(user)
            println("Usuario registrado")
        } catch (e: Exception) {
            println(e.message)
        }
    }
}