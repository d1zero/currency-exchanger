package com.d1zero.currencyexchange.database

sealed interface CurrencyEvent {
    object SaveCurrency : CurrencyEvent
    data class SetCurrencyName(val currencyName: String) : CurrencyEvent
    data class DeleteCurrency(val currency: Currency) : CurrencyEvent
}