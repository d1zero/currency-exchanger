package com.d1zero.currencyexchange.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.d1zero.currencyexchange.database.Currency
import com.github.tehras.charts.line.LineChart
import com.github.tehras.charts.line.LineChartData
import com.github.tehras.charts.line.renderer.line.SolidLineDrawer
import com.github.tehras.charts.line.renderer.point.FilledCircularPointDrawer
import com.github.tehras.charts.line.renderer.yaxis.SimpleYAxisDrawer
import com.github.tehras.charts.piechart.animation.simpleChartAnimation
import com.github.tehras.charts.line.renderer.xaxis.SimpleXAxisDrawer

@Composable
fun Graphic(
    currencies: List<Currency>,
) {
    var currencyID = 1
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(8.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            val listState = rememberLazyListState()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colors.surface),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Доступные валюты")

                LazyColumn(
                    state = listState,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(currencies) { currency ->
                        Text(
                            modifier = Modifier.clickable {
                                currencyID = currency.id
                            },
                            text = currency.name,
                        )
                    }
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            CurrencyCostGraph(currencyID)
        }
    }
}

@Composable
fun CurrencyCostGraph(id: Int) {
    LineChart(
        linesChartData = listOf(
            LineChartData(
                points = listOf(
                    LineChartData.Point(
                        1f,
                        "13.03"
                    ),
                    LineChartData.Point(
                        2.5f,
                        "14.03"
                    ),
                    LineChartData.Point(
                        3f,
                        "15.03"
                    ),
                    LineChartData.Point(
                        1f,
                        "16.03"
                    ),
                    LineChartData.Point(
                        3f,
                        "17.03"
                    ),
                    LineChartData.Point(
                        2f,
                        "18.03"
                    ),
                    LineChartData.Point(
                        1f,
                        "19.03"
                    ),

                    ),
                lineDrawer = SolidLineDrawer()
            )
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp),
        animation = simpleChartAnimation(),
        pointDrawer = FilledCircularPointDrawer(),
        xAxisDrawer = SimpleXAxisDrawer(),
        yAxisDrawer = SimpleYAxisDrawer(),
        labels = listOf("13.03", "14.03", "15.03", "16.03", "17.03", "18.03", "19.03")
    )
}