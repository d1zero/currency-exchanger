package com.d1zero.currencyexchange.api

import android.os.Build
import androidx.annotation.RequiresApi
import com.d1zero.currencyexchange.database.Transaction
import java.time.LocalDateTime
import java.util.*

interface TransactionApi {
    fun getTransactions(): List<Transaction>
}

class TransactionApiImpl : TransactionApi {
    @RequiresApi(Build.VERSION_CODES.O)
    private var transactions = listOf(
        Transaction(
            1,
            1,
            2,
            LocalDateTime.of(
                2023,
                3,
                16,
                16,
                25,
                20
            ),
            100.00,
            1.23
        ),
        Transaction(
            1,
            2,
            3,
            LocalDateTime.of(
                2023,
                3,
                15,
                16,
                25,
                20
            ),
            100.00,
            1.23
        ),
        Transaction(
            1,
            1,
            3,
            LocalDateTime.of(
                2023,
                3,
                16,
                16,
                25,
                20
            ),
            100.00,
            1.23
        ),
    )

    @RequiresApi(Build.VERSION_CODES.O)
    override fun getTransactions(): List<Transaction> {
        return transactions
    }

}