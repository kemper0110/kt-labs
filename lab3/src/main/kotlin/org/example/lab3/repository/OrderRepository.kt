package org.example.lab3.repository

import org.example.lab3.model.*
import org.example.lab3.utils.idSequence
import org.springframework.stereotype.Repository

@Repository
class OrderRepository(private val persistence: MemoryPersistence) {
    private val orderIdGenerator = idSequence("order").iterator()
    private val orderItemIdGenerator = idSequence("order-item").iterator()

    fun findAll(): List<Order> {
        return persistence.orders
    }

    fun createOrder(clientId: String): Order {
        val cart = persistence.carts.filter { it.clientId == clientId }
        val products = cart.map { persistence.products.first { p -> p.id == it.productId } }

        // Проверить, что товара достаточно на складе
        products.forEachIndexed { idx, product ->
            if (product.quantity < cart[idx].quantity)
                throw RuntimeException("Недостаточно товара ${product.name}[${product.id}] - ${product.quantity} единицы.")
        }
        val orderId = orderIdGenerator.next()
        // Создаем OrderItem для каждого элемента корзины
        cart.forEach { cartItem ->
            persistence.orderItems.add(
                OrderItem(orderItemIdGenerator.next(), orderId, cartItem.productId, cartItem.quantity)
            )
        }
        val order = Order(orderId, clientId, OrderStatus.NEW)
        persistence.orders.add(order)
        return order
    }

    fun findByClientId(clientId: String): List<Order> {
        return persistence.orders.filter { it.clientId == clientId }
    }

    fun findItemsByOrderId(orderId: String): List<OrderItem> {
        return persistence.orderItems.filter { it.orderId == orderId }
    }

    fun sellsAnalytics(): List<CategorySellsAnalytics> {
        return persistence.orderItems
            .groupBy { item -> persistence.products.first { p -> p.id == item.productId }.category }
            .map { (category, orders) ->
                CategorySellsAnalytics(category, orders.size)
            }
    }
}