package com.d1zero.currencyexchange.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun StickyHeader(
    modifier: Modifier = Modifier,
    string: String
) {
    Box(
        modifier
            .background(Color.LightGray)
            .padding(16.dp)
    ) {
        Text(text = string)
    }
}
