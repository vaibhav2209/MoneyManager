package com.example.moneymanager.module.home.domain.model

data class Transaction(
    val amount: String = "",
    val category: String = "",
    val date: String = "",
    val note: String = "",
    val payment_method: String = "",
    val t_name: String = "",
    val t_type: String = "",
    val tags: List<String> = emptyList()
)
