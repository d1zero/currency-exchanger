package com.d1zero.currencyexchange.screens

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.d1zero.currencyexchange.R
import com.d1zero.currencyexchange.api.CurrencyApiImpl
import com.d1zero.currencyexchange.components.StickyHeader
import com.d1zero.currencyexchange.dto.Transaction
import java.time.format.DateTimeFormatter
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun History(
    modifier: Modifier = Modifier,
    transactions: List<Transaction>,
    navigateToConverter: () -> Unit,
) {
    Box(
        Modifier
            .fillMaxWidth()
            .padding(0.dp, 16.dp, 0.dp, 60.dp)
    ) {
        val listState = rememberLazyListState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.surface),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "История обменов")

            Row() {
                Image(
                    painter = painterResource(id = R.drawable.filter),
                    contentDescription = "filter",
                    modifier = Modifier
                        .clickable {
                            navigateToConverter()
                        }
                )
            }

            LazyColumn(
                state = listState,
                modifier = Modifier.fillMaxWidth()
            ) {
                val grouped = transactions.groupBy { it.createdAt }
                grouped.forEach { (initial, transactions) ->
                    stickyHeader {
                        StickyHeader(
                            Modifier.fillParentMaxWidth(),
                            initial.format(
                                DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale("ru", "RU"))
                            )
                        )
                    }
                    items(transactions.sortedByDescending { it.createdAt }) { transaction ->
                        HistoryListItem(modifier.fillMaxWidth(), transaction)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HistoryListItem(
    modifier: Modifier = Modifier,
    transactionItem: Transaction,
) {
    val currencyApi = CurrencyApiImpl()
    val currencyFrom = currencyApi.getCurrencyByID(transactionItem.currencyFromID)
    val currencyTo = currencyApi.getCurrencyByID(transactionItem.currencyToID)
    Box(
        modifier
            .background(Color.Transparent)
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = transactionItem.amountFrom.toString().split(".0")[0] + " "
                        + currencyFrom.name + " " + "->" + " " +
                        transactionItem.amountTo.toString().split(".0")[0] + " "
                        + currencyTo.name
            )
        }
    }
}