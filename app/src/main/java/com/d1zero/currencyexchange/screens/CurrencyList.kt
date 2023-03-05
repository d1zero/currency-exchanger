package com.d1zero.currencyexchange.screens

import android.graphics.drawable.shapes.OvalShape
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.d1zero.currencyexchange.R
import com.d1zero.currencyexchange.dto.Currency

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CurrencyList(
    modifier: Modifier = Modifier,
    currencies: List<Currency>
) {
    Box(
        Modifier
            .fillMaxWidth()
            .padding(0.dp, 16.dp, 0.dp, 60.dp)
    ) {

        val listState = rememberLazyListState()
        val favoriteCurrenciesState = rememberLazyListState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.surface),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            if (currencies.any { currency -> currency.isFavorite }) {
                Text(text = "Избранное")
            }

            LazyRow(
                state = favoriteCurrenciesState,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 20.dp)
            ) {
                items(currencies) { currency ->
                    if (currency.isFavorite) {
                        Box(
                            modifier = Modifier
                                .padding(end = 10.dp)
                                .clip(shape = RoundedCornerShape(20.dp))
                                .background(Color.Red)
                        ) {
                            Text(
                                modifier = Modifier
                                    .padding(10.dp), text = currency.name
                            )
                        }
                    }
                }
            }

            Text(text = "Доступные валюты")

            LazyColumn(
                state = listState,
                modifier = Modifier.fillMaxWidth()
            ) {
                val grouped = currencies.groupBy { it.name[0] }

                grouped.forEach { (initial, currencies) ->
                    stickyHeader { CharacterHeader(Modifier.fillParentMaxWidth(), initial) }
                    items(currencies) { currency ->
                        CurrencyListItem(modifier.fillMaxWidth(), currency)
                    }
                }
            }
        }
    }


}

@Composable
fun CharacterHeader(
    modifier: Modifier = Modifier,
    char: Char
) {
    Box(
        modifier
            .background(Color.LightGray)
            .padding(16.dp)
    ) {
        Text(text = char.toString())
    }
}

@Composable
fun CurrencyListItem(
    modifier: Modifier = Modifier,
    currencyItem: Currency,
) {
    Box(
        modifier
            .background(Color.Transparent)
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = currencyItem.name)
            Image(
                painter = painterResource(id = R.drawable.star_border),
                contentDescription = "star"
            )
        }
    }
}
