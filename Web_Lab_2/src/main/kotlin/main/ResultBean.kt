package main

/**
 * ResultBean is a data class representing the result of point coordinates verification.
 * It contains properties such as x, y, r, result, userLocalDateTime, and executionTime.
 */
data class ResultBean(
    val x: Double = 0.0,
    val y: Double = 0.0,
    val r: Double = 0.0,
    val result: String? = null,
    val userLocalDateTime: String? = null,
    val executionTime: Double = 0.0
)
