package com.d1zero.currencyexchange.screens

import android.app.Application
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Observer
import androidx.lifecycle.viewmodel.compose.viewModel
import com.d1zero.currencyexchange.R
import com.d1zero.currencyexchange.database.Currency
import com.d1zero.currencyexchange.database.CurrencyViewModel
import com.d1zero.currencyexchange.database.CurrencyViewModelFactory

@Composable
fun CurrencyList(
    modifier: Modifier = Modifier,
    navigateToConverter: () -> Unit,
) {
    val context = LocalContext.current
    val mCurrencyViewModel: CurrencyViewModel = viewModel(
        factory = CurrencyViewModelFactory(context.applicationContext as Application)
    )

    val currencies = mCurrencyViewModel.readAllData.observeAsState(listOf()).value

    Box(
        Modifier
            .fillMaxWidth()
            .padding(0.dp, 16.dp, 0.dp, 60.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.surface),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Text(text = "Доступные валюты")

            CurrencyListRender(
                currencies = currencies,
                modifier = modifier,
                navigateToConverter = navigateToConverter
            )
        }
    }
}

@Composable
fun CurrencyListRender(
    currencies: List<Currency>,
    modifier: Modifier,
    navigateToConverter: () -> Unit,
) {
    val listState = rememberLazyListState()

    if (!currencies.none { it.isFavorite }) {
        Text(text = "Избранные валюты")
        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxWidth()
        ) {
            items(currencies.filter { it.isFavorite }) { currency ->
                CurrencyListItem(modifier.fillMaxWidth(), currency, navigateToConverter)
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CurrencyListItem(
    modifier: Modifier = Modifier,
    currencyItem: Currency,
    navigateToConverter: () -> Unit,
) {
    Box(
        modifier
            .background(Color.Transparent)
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        val context = LocalContext.current
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .combinedClickable(onClick = navigateToConverter),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = currencyItem.name)
            if (currencyItem.isFavorite) {
                Image(
                    painter = painterResource(id = R.drawable.star),
                    contentDescription = "star",
                    modifier = Modifier
                        .clickable {
                            Toast.makeText(context, "Not favorite now", Toast.LENGTH_SHORT).show()
                        }
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.star_border),
                    contentDescription = "star",
                    modifier = Modifier
                        .clickable {
                            Toast.makeText(context, "Favorite now", Toast.LENGTH_SHORT).show()
                        }
                )
            }
        }
    }
}
