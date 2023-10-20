package main
/**
 * ResultBean is a data class representing the result of point coordinates verification.
 * It contains properties such as x, y, r, result, userLocalDateTime, and executionTime.
 */
class ResultBean {
    // Properties for storing result data
    var x = 0.0
    var y = 0.0
    var r = 0.0
    var result: String? = null
    var userLocalDateTime: String? = null
    var executionTime = 0.0
}
