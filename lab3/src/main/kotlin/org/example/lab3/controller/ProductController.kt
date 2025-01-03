package org.example.lab3.controller

import org.example.lab3.model.Product
import org.example.lab3.model.ProductFilter
import org.example.lab3.model.ProductInput
import org.example.lab3.repository.ProductRepository
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class ProductController(private val productRepository: ProductRepository) {
    @QueryMapping
    fun products(@Argument filter: ProductFilter?): List<Product> {
        return if(filter == null)
            productRepository.findAll()
        else
            productRepository.findAllFiltering(filter)
    }

    @MutationMapping
    fun createProduct(@Argument input: ProductInput): Product {
        return productRepository.createProduct(input)
    }

    @MutationMapping
    fun updateProductQuantity(@Argument id: String, @Argument quantity: Int): Product {
        return productRepository.updateProductQuantity(id, quantity)
    }
}