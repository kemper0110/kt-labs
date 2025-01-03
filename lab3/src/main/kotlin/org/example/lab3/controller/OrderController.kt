package org.example.lab3.controller

import org.example.lab3.model.Client
import org.example.lab3.model.Order
import org.example.lab3.model.OrderItem
import org.example.lab3.model.Product
import org.example.lab3.repository.ClientRepository
import org.example.lab3.repository.OrderRepository
import org.example.lab3.repository.ProductRepository
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.graphql.data.method.annotation.SchemaMapping
import org.springframework.stereotype.Controller

@Controller
class OrderController(
    private val orderRepository: OrderRepository,
    private val productRepository: ProductRepository,
    private val clientRepository: ClientRepository,
) {
    @MutationMapping
    fun createOrder(@Argument clientId: String): Order {
        return orderRepository.createOrder(clientId)
    }
    @SchemaMapping
    fun products(order: Order): List<OrderItem> {
        return orderRepository.findItemsByOrderId(order.id)
    }
    @SchemaMapping(typeName = "OrderItem")
    fun product(orderItem: OrderItem): Product {
        return productRepository.findById(orderItem.productId)
    }

    @SchemaMapping
    fun client(order: Order): Client {
        return clientRepository.findById(order.clientId)
    }
    @QueryMapping
    fun orders(): List<Order> {
        return orderRepository.findAll()
    }
}