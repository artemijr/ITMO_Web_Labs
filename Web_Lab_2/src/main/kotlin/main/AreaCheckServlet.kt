package main

import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

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