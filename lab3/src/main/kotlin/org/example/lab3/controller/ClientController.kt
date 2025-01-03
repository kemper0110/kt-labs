package org.example.lab3.controller

import org.example.lab3.model.Client
import org.example.lab3.model.ClientInput
import org.example.lab3.model.Order
import org.example.lab3.repository.ClientRepository
import org.example.lab3.repository.OrderRepository
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.graphql.data.method.annotation.SchemaMapping
import org.springframework.stereotype.Controller

@Controller
class ClientController(
    private val clientRepository: ClientRepository,
    private val orderRepository: OrderRepository
) {
    @QueryMapping
    fun clients(): List<Client> {
        return clientRepository.findAll()
    }
    @SchemaMapping
    fun orders(client: Client): List<Order> {
        return orderRepository.findByClientId(client.id)
    }
    @MutationMapping
    fun createClient(@Argument input: ClientInput): Client {
        return clientRepository.create(input)
    }
}