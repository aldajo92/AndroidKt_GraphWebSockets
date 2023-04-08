package com.aldajo92.graphwebsockets

data class MedicalData(
    val time: Long,
    val oxygen: Float,
    val glucose: Float,
    val heartRate: Float,
    val temperature: Float
)