package com.d1zero.currencyexchange.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.d1zero.currencyexchange.dto.Currency
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CurrencyList(
    modifier: Modifier = Modifier,
    currencies: List<Currency>
) {
    Box(
        Modifier
            .fillMaxWidth()
            .padding(0.dp, 0.dp, 0.dp, 16.dp)
    ) {

        val listState = rememberLazyListState()
        val scope = rememberCoroutineScope()

        LazyColumn(
            state = listState,
            contentPadding = PaddingValues(bottom = 80.dp),
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

        val showButton = listState.firstVisibleItemIndex > 0

        AnimatedVisibility(
            visible = showButton,
            enter = fadeIn(),
            exit = fadeOut(),
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            ScrollToTopButton(onClick = {
                scope.launch {
                    listState.scrollToItem(0)
                }
            }, modifier = Modifier.padding(50.dp))
        }
    }

}

@Composable
fun ScrollToTopButton(onClick: () -> Unit, modifier: Modifier) {
    Box(
        modifier
    ) {
        Button(
            onClick = { onClick() }, modifier = Modifier
                .shadow(10.dp, shape = CircleShape)
                .clip(shape = CircleShape)
                .size(64.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.White,
                contentColor = Color.Green
            )
        ) {
            Icon(Icons.Filled.KeyboardArrowUp, "arrow up")
        }
    }
}

@Composable
fun CharacterHeader(
    modifier: Modifier = Modifier,
    char: Char
) {
    Box(modifier.background(Color.DarkGray)) {
        Text(text = char.toString())
    }
}

@Composable
fun CurrencyListItem(
    modifier: Modifier = Modifier,
    currencyItem: Currency,
) {
    Box(modifier) {
        Text(text = currencyItem.name)
    }
}
