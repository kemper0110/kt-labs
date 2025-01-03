package org.example.lab3.controller

import org.example.lab3.model.*
import org.example.lab3.repository.CartRepository
import org.example.lab3.repository.OrderRepository
import org.example.lab3.repository.ProductRepository
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.graphql.data.method.annotation.SchemaMapping
import org.springframework.stereotype.Controller

@Controller
class CartController(
    private val cartRepository: CartRepository,
    private val productRepository: ProductRepository,
    private val orderRepository: OrderRepository,
) {
    @QueryMapping
    fun cart(@Argument clientId: String): List<CartItem> {
        return cartRepository.findByClientId(clientId)
    }

    @SchemaMapping
    fun product(cartItem: CartItem): Product {
        return productRepository.findById(cartItem.productId)
    }

    @QueryMapping
    fun cartPrice(@Argument clientId: String): CartPrice {
        val cart = cartRepository.findByClientId(clientId)
        val cartPriceValue = cart.map { it.quantity * productRepository.findById(it.productId).price }
            .fold(0) { acc, i -> acc + i }

        val orders = orderRepository.findByClientId(clientId)

        val discounts = mutableListOf<Discount>()
        // единственная скидка, и та захардкожена
        if (orders.isEmpty())
            discounts.add(Discount("Скидка за первый заказ", 50))

        // скидки применяются мультипликативно!
        // для суммы 1000 будут применены скидки 50% и 20% так:
        // 1000 * 0.5 = 500
        // 500 * 0.2 = 100
        val finalPrice = discounts.map { it.value }.fold(cartPriceValue) { acc, value ->
            acc * value / 100
        }

        return CartPrice(finalPrice, discounts)
    }

    @SchemaMapping
    fun discounts(cartPrice: CartPrice): List<Discount> {
        return cartPrice.discounts
    }

    @MutationMapping
    fun createCartItem(@Argument input: CreateCartItemInput): CartItem {
        return cartRepository.createCartItem(input)
    }

    @MutationMapping
    fun deleteCartItem(@Argument cartItemId: String): Boolean {
        return cartRepository.deleteCartItem(cartItemId)
    }
}