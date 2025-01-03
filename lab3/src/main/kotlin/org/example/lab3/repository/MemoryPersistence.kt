package org.example.lab3.repository

import org.example.lab3.model.*
import org.springframework.stereotype.Component

@Component
class MemoryPersistence {
    val clients = mutableListOf(
        Client("default-client-1", "John", "john@example.com"),
        Client("default-client-2", "Jane", "jane@example.com"),
        Client("default-client-3", "Bob", "bob@example.com")
    )
    val products = mutableListOf(
        Product("default-product-1", "Компьютер", "Оборудование", 1000, 10),
        Product("default-product-2", "Мышь", "Оборудование", 500, 5),
        Product("default-product-3", "Клавиатура", "Оборудование", 700, 1),
        Product("default-product-4", "Монитор", "Оборудование", 600, 2),
        Product("default-product-5", "Карта", "Оборудование", 800, 1),
    )
    val carts = mutableListOf(
        CartItem("default-cart-item-1", "default-client-1", "default-product-1", 5),
        CartItem("default-cart-item-2", "default-client-1", "default-product-2", 1),
        CartItem("default-cart-item-3", "default-client-1", "default-product-3", 1),
        CartItem("default-cart-item-4", "default-client-1", "default-product-4", 1),
        CartItem("default-cart-item-5", "default-client-1", "default-product-5", 1),
    )
    val orderItems = mutableListOf(
        OrderItem("default-order-item-1", "default-order-1","default-product-1", 1),
        OrderItem("default-order-item-1", "default-order-1","default-product-1", 1),
        OrderItem("default-order-item-1", "default-order-1","default-product-1", 1),
        OrderItem("default-order-item-1", "default-order-1","default-product-1", 1),
        OrderItem("default-order-item-1", "default-order-1","default-product-1", 1),
    )
    val orders = mutableListOf(
        Order("default-order-1", "default-client-1", OrderStatus.NEW),
    )
}