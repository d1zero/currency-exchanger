package com.d1zero.currencyexchange.api

import com.d1zero.currencyexchange.database.Currency

interface CurrencyApi {
    fun getCurrencies(): List<Currency>
    fun getCurrencyByID(id: Int): Currency
}

class CurrencyApiImpl : CurrencyApi {
    private var currencies = listOf(
        Currency(
            1,
            "RUB",
            true,
        ),
        Currency(
            2,
            "USD",
            false
        ),
        Currency(
            3,
            "EUR",
            false
        ),
    )

    override fun getCurrencies(): List<Currency> {
        return currencies
    }

    override fun getCurrencyByID(id: Int): Currency {
        return currencies.filter { it.id == id }[0]
    }
}