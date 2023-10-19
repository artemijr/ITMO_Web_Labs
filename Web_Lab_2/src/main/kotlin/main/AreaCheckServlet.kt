package main

import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@WebServlet(name = "AreaCheckServlet", value = ["/AreaCheckServlet"])
class AreaCheckServlet : HttpServlet() {
    override fun doGet(request: HttpServletRequest, response: HttpServletResponse) {
        response.characterEncoding = "UTF-8"
        val x = request.getParameter("x").toInt()
        val y = request.getParameter("y").toDouble()
        val r = request.getParameter("r").toDouble()
        val userLocalDateTime = request.getParameter("userLocalDateTime")

        val validXValues = listOf(-3, -2, -1, 0, 1, 2, 3, 4, 5)

        var result = ""
        val startTime = System.nanoTime()

        try {
            if (x in validXValues && y in -5.0..3.0 && r in 2.0..5.0) {
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
                    result = if ((x * x + y * y) <= (r * r) / 4) {
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

            val resultBean = ResultBean()
            resultBean.x = x
            resultBean.y = y
            resultBean.r = r
            resultBean.result = result
            resultBean.userLocalDateTime = userLocalDateTime
            resultBean.executionTime = executionTimeMs

            request.setAttribute("resultBean", resultBean)

            request.getRequestDispatcher("jsp/result.jsp").forward(request, response)
        } catch (e: Exception) {
            response.sendError(400, e.message)
        }
    }
}