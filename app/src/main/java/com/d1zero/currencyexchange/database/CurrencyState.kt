package com.d1zero.currencyexchange.database

data class CurrencyState(
    val currencies: List<Currency> = emptyList(),
    val currencyName: String = "",
)
