package org.example.lab3.controller

import org.example.lab3.model.CategorySellsAnalytics
import org.example.lab3.repository.OrderRepository
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class SellsController(private val orderRepository: OrderRepository) {
    @QueryMapping
    fun sellsAnalytics(): List<CategorySellsAnalytics> {
        return orderRepository.sellsAnalytics()
    }
}