package com.d1zero.currencyexchange.api

import com.d1zero.currencyexchange.dto.Currency
import java.util.*

interface CurrencyApi {
    fun getCurrencies(): List<Currency>
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
}