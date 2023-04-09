package com.aldajo92.graphwebsockets

import android.util.Log
import androidx.lifecycle.ViewModel
import io.socket.client.IO
import io.socket.client.Socket
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.net.URISyntaxException

class MainViewModel : ViewModel() {

    private var mSocket: Socket? = null

    private val _dataState = MutableStateFlow(0)
    val dataState = _dataState.asStateFlow()

    private val _dataListState = MutableStateFlow(listOf<Float>())
    val dataListState = _dataListState.asStateFlow()

    private val dataList = mutableListOf<Float>()

    fun initSocket() {
        try {
            mSocket = IO.socket(
                "https://f47b-167-0-177-4.ngrok-free.app" // <- Replace with your ngrok url
            )
        } catch (e: URISyntaxException) {
            Log.e("MainViewModel", "Error: $e")
        }

        mSocket?.on("sensor_message") {
            val data = it[0] as String
            try {
                _dataState.value = data.toFloat().toInt()
                dataList.add(data.toFloat())
                if (dataList.size > 10) {
                    dataList.removeAt(0)
                }
                _dataListState.value = dataList
                Log.i("MainViewModel", "data: $data")
                Log.i("MainViewModel", "dataList: $dataList")
            } catch (e: NumberFormatException) {
                Log.e("MainViewModel", "Error: $e")
            }
        }

        mSocket?.connect();
    }

}
