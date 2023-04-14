package com.d1zero.currencyexchange.database

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CurrencyViewModel(private val dao: CurrencyDao) : ViewModel() {
    private val _currencies = dao.getCurrencies().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(), emptyList(),
    )
    private val _state = MutableStateFlow(CurrencyState())

    val state = combine(_state, _currencies) { state, currencies ->
        state.copy(
            currencies = currencies
        )
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000), CurrencyState()
    )

    fun onEvent(event: CurrencyEvent) {
        when (event) {
            is CurrencyEvent.DeleteCurrency -> {
                viewModelScope.launch {
                    dao.deleteCurrency(event.currency)
                }
            }

            CurrencyEvent.SaveCurrency -> {
                val currencyName = state.value.currencyName
                if (currencyName.isBlank()) {
                    return
                }

                val currency = Currency(
                    name = currencyName,
                    isFavorite = false
                )
                viewModelScope.launch {
                    dao.upsertCurrency(currency)
                }
                _state.update {
                    it.copy(
                        currencyName = ""
                    )
                }
            }

            is CurrencyEvent.SetCurrencyName -> {
                _state.update {
                    it.copy(
                        currencyName = event.currencyName
                    )
                }
            }
        }
    }
}