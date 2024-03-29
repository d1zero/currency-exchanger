package com.d1zero.currencyexchange.database

import java.time.LocalDateTime

data class Transaction(
    var id: Int,
    var currencyFromID: Int,
    var currencyToID: Int,
    var createdAt: LocalDateTime,
    var amountFrom: Double,
    var amountTo: Double
)