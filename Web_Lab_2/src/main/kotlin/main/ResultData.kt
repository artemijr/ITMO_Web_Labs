package main

import java.io.Serializable


data class ResultData(
    val x: Double = 0.0,
    val y: Double = 0.0,
    val r: Double = 0.0,
    val result: String? = null,
    val userLocalDateTime: String? = null,
    val executionTime: Double = 0.0
) : Serializable
