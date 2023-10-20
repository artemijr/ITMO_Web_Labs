package main

import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import java.math.BigDecimal
import java.math.RoundingMode

@WebServlet(name = "AreaCheckServlet", value = ["/AreaCheckServlet"])
class AreaCheckServlet : HttpServlet() {
    /**
     * Handles a GET request to check if a point falls within a specified area.
     *
     * @param request The HttpServletRequest containing parameters such as x, y, r, and userLocalDateTime.
     * @param response The HttpServletResponse for sending a response back to the client.
     */
    override fun doGet(request: HttpServletRequest, response: HttpServletResponse) {
        response.characterEncoding = "UTF-8"

        // Get the input parameters
        val xStr = request.getParameter("x")
        val yStr = request.getParameter("y")
        val rStr = request.getParameter("r")
        val userLocalDateTime = request.getParameter("userLocalDateTime")

        var result = ""
        val startTime = System.nanoTime()

        try {
            // Parse input values to doubles
            val x = xStr?.toDoubleOrNull()
            val y = yStr?.toDoubleOrNull()
            val r = rStr?.toDoubleOrNull()

            if (x != null && y != null && r != null) {
            // Check which quarter the point falls into

                if (x >= 0 && y >= 0) {
                    // Check the first quarter: x >= 0 and y >= 0
                    result = if (y <= r && x <= r / 2) {
                        "Точка входит в область определения"
                    } else {
                        "Точка не входит в область определения"
                    }
                } else if (x <= 0 && y >= 0) {
                    // Check the second quarter: x <= 0 and y >= 0
                    result = "Точка не входит в область определения"
                } else if (x <= 0 && y <= 0) {
                    result = if (y >= -r && x >= -r / 2 && -r*x*2-r*r<=y*r) {
                        "Точка входит в область определения"
                    } else {
                        "Точка не входит в область определения"
                    }
                } else if (x >= 0 && y <= 0) {
                    // Check the fourth quarter: x >= 0 and y <= 0
                    result = if ((x * x + y * y) <= (r * r)) {
                        "Точка входит в область определения"
                    } else {
                        "Точка не входит в область определения"
                    }
                }

            } else {
                throw Exception("Неверный ввод. Пожалуйста, убедитесь, что вы вводите корректные значения.")
            }

            val endTime = System.nanoTime()
            val executionTimeMs = ((endTime - startTime) / 1e6)

            // Round values to 3 decimal places
            val fX = BigDecimal(x).setScale(3, RoundingMode.FLOOR).toDouble()
            val fY = BigDecimal(y).setScale(3, RoundingMode.FLOOR).toDouble()
            val fR = BigDecimal(r).setScale(3, RoundingMode.FLOOR).toDouble()
            val fExecutionTime = BigDecimal(executionTimeMs).setScale(3, RoundingMode.FLOOR).toDouble()

            // Create a ResultBean and set attributes
            val resultBean = ResultBean()
            resultBean.x = fX
            resultBean.y = fY
            resultBean.r = fR
            resultBean.result = result
            resultBean.userLocalDateTime = userLocalDateTime
            resultBean.executionTime = fExecutionTime

            request.setAttribute("resultBean", resultBean)

            // Forward to the result JSP page
            request.getRequestDispatcher("jsp/result.jsp").forward(request, response)
        } catch (e: Exception) {
            // Handle exceptions by sending a 400 Bad Request response
            response.sendError(400, e.message)
        }
    }
}