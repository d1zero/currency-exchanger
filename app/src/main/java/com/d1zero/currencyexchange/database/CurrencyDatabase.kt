package com.d1zero.currencyexchange.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.d1zero.currencyexchange.database.Currency
import com.d1zero.currencyexchange.database.CurrencyDao

@Database(
    entities = [Currency::class],
    version = 1
)
abstract class CurrencyDatabase : RoomDatabase() {
    abstract val dao: CurrencyDao
}