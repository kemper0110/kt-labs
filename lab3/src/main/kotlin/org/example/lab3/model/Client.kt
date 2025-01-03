package org.example.lab3.model

data class Client(val id: String, val name: String, val email: String, val ordersIds: MutableList<String> = mutableListOf())
