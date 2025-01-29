package org.example.com.es.connection

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import io.github.cdimascio.dotenv.Dotenv
import io.github.cdimascio.dotenv.dotenv

object MongoConection {
    private val dotenv: Dotenv = dotenv()
    private val urlConnectionMongo = dotenv["URL_MONGODB"] ?: throw Exception()
    private val cluster: MongoClient = MongoClients.create(urlConnectionMongo)

    private fun getConectionDB(db: String): MongoDatabase {
        return cluster.getDatabase(db)
    }

    fun <T> getCollection(db: String, coll: String, entity: Class<T>): MongoCollection<T> {
        val bd = getConectionDB(db)
        return bd.getCollection(coll, entity)
    }
}