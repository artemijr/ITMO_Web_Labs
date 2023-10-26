package main

import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import java.math.BigDecimal
import java.math.RoundingMode

@WebServlet(name = "AreaCheckServlet", value = ["/AreaCheckServlet"])
class AreaCheckServlet : HttpServlet() {
    override fun doGet(request: HttpServletRequest, response: HttpServletResponse) {
        response.characterEncoding = "UTF-8"

        // Get the input parameters
        val xStr = request.getParameter("x")
        val yStr = request.getParameter("y")
        val rStr = request.getParameter("r")
        val userLocalDateTime = request.getParameter("userLocalDateTime")

        val startTime = System.nanoTime()

        val result = processPoint(xStr, yStr, rStr)

        // Round values to 3 decimal places
        val fX = BigDecimal(xStr).setScale(3, RoundingMode.FLOOR).toDouble()
        val fY = BigDecimal(yStr).setScale(3, RoundingMode.FLOOR).toDouble()
        val fR = BigDecimal(rStr).setScale(3, RoundingMode.FLOOR).toDouble()

        val endTime = System.nanoTime()
        val executionTimeMs = ((endTime - startTime) / 1e6)

        val fExecutionTime = BigDecimal(executionTimeMs).setScale(3, RoundingMode.FLOOR).toDouble()

        // Create a ResultBean and set attributes
        val resultBean = ResultBean(fX, fY, fR, result, userLocalDateTime, fExecutionTime)

        // Store the ResultBean in the session
        val session = request.session
        val results = session.getAttribute("results") as? MutableList<ResultBean> ?: mutableListOf()

        results.add(resultBean)
        session.setAttribute("results", results)

        request.setAttribute("resultBean", resultBean)

        // Forward to the result JSP page
        request.getRequestDispatcher("jsp/result.jsp").forward(request, response)
    }

    private fun processPoint(xStr: String?, yStr: String?, rStr: String?): String {
        try {
            // Parse input values to doubles
            val x = xStr?.toDoubleOrNull()
            val y = yStr?.toDoubleOrNull()
            val r = rStr?.toDoubleOrNull()

            if (x != null && y != null && r != null) {
                return when {
                    isPointInFirstQuarter(x, y, r) -> "Точка входит в область определения"
                    isPointInSecondQuarter(x, y) -> "Точка не входит в область определения"
                    isPointInThirdQuarter(x, y, r) -> "Точка входит в область определения"
                    isPointInFourthQuarter(x, y, r) -> "Точка входит в область определения"
                    else -> "Точка не входит в область определения"
                }
            } else {
                throw Exception("Неверный ввод. Пожалуйста, убедитесь, что вы вводите корректные значения.")
            }
        } catch (e: Exception) {
            return "Error: ${e.message}"
        }
    }

    private fun isPointInFirstQuarter(x: Double, y: Double, r: Double): Boolean {
        return x >= 0 && y >= 0 && y <= r && x <= r / 2
    }

    private fun isPointInSecondQuarter(x: Double, y: Double): Boolean {
        return x <= 0 && y >= 0
    }

    private fun isPointInThirdQuarter(x: Double, y: Double, r: Double): Boolean {
        return x <= 0 && y <= 0 && y >= -r && x >= -r / 2 && -r * x * 2 - r * r <= y * r
    }

    private fun isPointInFourthQuarter(x: Double, y: Double, r: Double): Boolean {
        return x >= 0 && y <= 0 && (x * x + y * y) <= (r * r)
    }
}
