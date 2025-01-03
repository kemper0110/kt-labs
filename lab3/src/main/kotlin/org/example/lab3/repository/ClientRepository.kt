package org.example.lab3.repository

import org.example.lab3.model.Client
import org.example.lab3.model.ClientInput
import org.example.lab3.utils.idSequence
import org.springframework.stereotype.Repository

@Repository
class ClientRepository(private val persistence: MemoryPersistence) {
    val idGenerator = idSequence("client").iterator()
    fun findAll(): List<Client> {
        return persistence.clients
    }
    fun create(clientInput: ClientInput): Client {
        val client = Client(idGenerator.next(), clientInput.name, clientInput.email, mutableListOf())
        persistence.clients.add(client)
        return client
    }
    fun findById(id: String): Client {
        return persistence.clients.first { it.id == id }
    }
}