package com.d1zero.currencyexchange.database

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface CurrencyDatabaseDao {
    @Query("SELECT * from currency_list")
    fun getAll(): LiveData<List<Currency>>

    @Query("SELECT * from currency_list where id = :id")
    fun getById(id: Int): Currency?

    @Insert
    suspend fun insert(item: Currency)

    @Update
    suspend fun update(item: Currency)

    @Delete
    suspend fun delete(item: Currency)

    @Query("DELETE FROM currency_list")
    suspend fun deleteAllTodos()
}
