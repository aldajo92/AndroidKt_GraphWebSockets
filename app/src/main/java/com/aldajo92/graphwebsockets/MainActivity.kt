package com.aldajo92.graphwebsockets

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

class MainActivity : ComponentActivity() {

    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel.initSocket()

        setContent {
            val data by mainViewModel.dataState.collectAsState(0)
            val listData by mainViewModel.dataListState.collectAsState(listOf())

            val listDataSet = listData.createDataSetWithColor(
                datasetColor = android.graphics.Color.BLUE,
                label = "Data"
            )
            Text(text =data.toString())
            LineChartCard(
                lineData = LineData(listDataSet)
            )
        }
    }
}

@Composable
fun LineChartCard(modifier: Modifier = Modifier, lineData: LineData) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(2f) // (width:height) 2:1 -> 2/1
            .padding(16.dp)
    ) {
        LineChartComponent(
            modifier = Modifier.fillMaxSize(),
            lineData = lineData
        )
    }
}

@Composable
fun LineChartComponent(modifier: Modifier = Modifier, lineData: LineData) {
    // (x,y) -> Entry -> List<Entry> -> LineDataSet -> LineData -> LineChart
    AndroidView(
        modifier = modifier,
        factory = { context ->
            LineChart(context)
                .setupLineChart()
                .apply {
                    data = lineData
                }
        },
        update = { view ->
            view.data = lineData
            view.invalidate()
        }
    )
}

fun LineChart.setupLineChart(): LineChart = this.apply {
    setTouchEnabled(true)
    isDragEnabled = true
    setScaleEnabled(true)
    setPinchZoom(true)
    description.isEnabled = false

    // set up x-axis
    xAxis.apply {
        position = XAxis.XAxisPosition.BOTTOM
        // axisMinimum = -10f
        // axisMaximum = 10f
    }

    // set up y-axis
    axisLeft.apply {
        // axisMinimum = -5f
        // axisMaximum = 5f
        setDrawGridLines(false)
    }

    axisRight.isEnabled = false
}

fun List<Float>.createDataSetWithColor(
    datasetColor: Int = android.graphics.Color.GREEN,
    label: String = "No Label"
): LineDataSet {
    // List<Float> -> List<Entry>
    val entries = this.mapIndexed { index, value ->
        Entry(index.toFloat(), value)
    }
    // List<Entry> -> LineDataSet
    return LineDataSet(entries, label).apply {
        color = datasetColor
        setDrawFilled(false)
        setDrawCircles(false)
        mode = LineDataSet.Mode.CUBIC_BEZIER
    }
}
