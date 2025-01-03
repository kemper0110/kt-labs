package org.example.lab3.repository

import org.example.lab3.model.Product
import org.example.lab3.model.ProductFilter
import org.example.lab3.model.ProductInput
import org.example.lab3.utils.idSequence
import org.springframework.stereotype.Repository

@Repository
class ProductRepository(private val persistence: MemoryPersistence) {
    private val idGenerator = idSequence("product").iterator()
    fun findAll(): List<Product> {
        return persistence.products
    }
    fun findAllFiltering(filter: ProductFilter): List<Product> {
        return persistence.products
            .filter {  filter.categories.isNullOrEmpty() || filter.categories.contains(it.category) }
            .filter { filter.minPrice == null || it.price >= filter.minPrice }
            .filter { filter.maxPrice == null || it.price <= filter.maxPrice }
    }
    fun createProduct(input: ProductInput): Product {
        val product = Product(idGenerator.next(), input.name, input.category, input.price, input.quantity)
        persistence.products.add(product)
        return product
    }
    fun updateProductQuantity(id: String, quantity: Int): Product {
        val productIndex = persistence.products.indexOfFirst { it.id == id }
        if (productIndex == -1) {
            throw IllegalArgumentException("Product with id $id not found")
        }
        persistence.products[productIndex] = persistence.products[productIndex]
            .copy(quantity = quantity)
        return persistence.products[productIndex]
    }
    fun findById(id: String): Product {
        return persistence.products.first { it.id == id }
    }
}