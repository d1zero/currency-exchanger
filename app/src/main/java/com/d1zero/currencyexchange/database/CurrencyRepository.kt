package com.d1zero.currencyexchange.database

import androidx.lifecycle.LiveData


class CurrencyRepository(private val todoDatabaseDao: CurrencyDatabaseDao) {

    val readAllData: LiveData<List<Currency>> = todoDatabaseDao.getAll()

    suspend fun addCurrency(currencyItem: Currency) {
        todoDatabaseDao.insert(currencyItem)
    }

    suspend fun updateCurrency(currencyItem: Currency) {
        todoDatabaseDao.update(currencyItem)
    }

    suspend fun deleteCurrency(currencyItem: Currency) {
        todoDatabaseDao.delete(currencyItem)
    }

    suspend fun deleteCurrencies() {
        todoDatabaseDao.deleteAllTodos()
    }
}
