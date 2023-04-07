package com.d1zero.currencyexchange.database

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class CurrencyViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<Currency>>
    private val repository: CurrencyRepository

    init {
        val currencyDao = CurrencyDatabase.getInstance(application).currencyDao()
        repository = CurrencyRepository(currencyDao)
        readAllData = repository.readAllData
    }

    fun addCurrency(currencyItem: Currency) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addCurrency(currencyItem)
        }
    }

    fun updateCurrency(currencyItem: Currency) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateCurrency(currencyItem = currencyItem)
        }
    }

    fun deleteCurrency(currencyItem: Currency) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteCurrency(currencyItem = currencyItem)
        }
    }

    fun deleteAllCurrencies() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteCurrencies()
        }
    }
}

class CurrencyViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        if (modelClass.isAssignableFrom(CurrencyViewModel::class.java)) {
            return CurrencyViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
