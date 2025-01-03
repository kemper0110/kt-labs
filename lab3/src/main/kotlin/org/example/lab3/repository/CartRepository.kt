package org.example.lab3.repository

import org.example.lab3.model.CartItem
import org.example.lab3.model.CreateCartItemInput
import org.example.lab3.utils.idSequence
import org.springframework.stereotype.Repository

@Repository
class CartRepository(private val persistence: MemoryPersistence) {
    val idGenerator = idSequence("cart-item").iterator()
    fun findByClientId(clientId: String): List<CartItem> {
        return persistence.carts.filter { it.clientId == clientId }
    }
    fun createCartItem(input: CreateCartItemInput): CartItem {
        val cartItem = CartItem(idGenerator.next(), input.clientId, input.productId, input.quantity)
        persistence.carts.add(cartItem)
        return cartItem
    }
    fun deleteCartItem(cartItemId: String): Boolean {
        val index = persistence.carts.indexOfFirst { it.id == cartItemId }
        if (index == -1)
            return false
        persistence.carts.removeAt(index)
        return true
    }
}