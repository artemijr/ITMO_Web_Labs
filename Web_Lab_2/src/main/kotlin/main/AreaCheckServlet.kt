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

        val xStr = request.getParameter("x")
        val yStr = request.getParameter("y")
        val rStr = request.getParameter("r")
        val userLocalDateTime = request.getParameter("userLocalDateTime")

        var result = ""
        val startTime = System.nanoTime()

        try {
            val x = xStr?.toDoubleOrNull()
            val y = yStr?.toDoubleOrNull()
            val r = rStr?.toDoubleOrNull()

            if (x != null && y != null && r != null) {
                // Check which quarter the point falls into
                if (x >= 0 && y >= 0) {
                    result = if (y <= r && x <= r / 2) {
                        "Точка входит в область определения"
                    } else {
                        "Точка не входит в область определения"
                    }
                } else if (x <= 0 && y >= 0) {
                    result = "Точка не входит в область определения"
                } else if (x <= 0 && y <= 0) {
                    result = if (y >= -r && x >= -r / 2) {
                        "Точка входит в область определения"
                    } else {
                        "Точка не входит в область определения"
                    }
                } else if (x >= 0 && y <= 0) {
                    result = if ((x * x + y * y) <= (r * r) ) {
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

            val fX= BigDecimal(x).setScale(3, RoundingMode.FLOOR).toDouble()
            val fY = BigDecimal(y).setScale(3, RoundingMode.FLOOR).toDouble()
            val fR = BigDecimal(r).setScale(3, RoundingMode.FLOOR).toDouble()
            val fExecutionTime = BigDecimal(executionTimeMs).setScale(3, RoundingMode.FLOOR).toDouble()



            val resultBean = ResultBean()
            resultBean.x = fX
            resultBean.y = fY
            resultBean.r = fR
            resultBean.result = result
            resultBean.userLocalDateTime = userLocalDateTime
            resultBean.executionTime = fExecutionTime

            request.setAttribute("resultBean", resultBean)

            request.getRequestDispatcher("jsp/result.jsp").forward(request, response)
        } catch (e: Exception) {
            response.sendError(400, e.message)
        }
    }
}