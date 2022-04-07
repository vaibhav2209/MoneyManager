package com.example.moneymanager.module.home.domain.model

import com.example.moneymanager.utilities.Constants

fun Transaction.convertToMap(): MutableMap<String, Any> {
    val map = mutableMapOf<String, Any>()
    map[Constants.AMOUNT] = amount
    map[Constants.TRANSACTION_NAME] = t_name
    map[Constants.TRANSACTION_TYPE] = t_type
    map[Constants.CATEGORY] = category
    map[Constants.DATE] = date
    map[Constants.NOTE] = note
    map[Constants.PAYMENT_METHOD] = payment_method
    map[Constants.PAYMENT_METHOD] = payment_method
    map[Constants.TAGS] = tags
    map[Constants.Month] = month
    return map
}