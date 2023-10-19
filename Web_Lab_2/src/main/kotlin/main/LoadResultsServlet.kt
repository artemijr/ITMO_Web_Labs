package main

import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@WebServlet(name = "LoadResultsServlet", value = ["/LoadResultsServlet"])
class LoadResultsServlet : HttpServlet() {
    override fun doGet(request: HttpServletRequest, response: HttpServletResponse) {
        response.characterEncoding = "UTF-8"

        val clearTable = request.getParameter("clearTable")
        if (clearTable != null && clearTable == "true") {
            // Clear the table and session attribute
            request.session.removeAttribute("results")
        }

        val results = (request.session.getAttribute("results") as? List<*>)?.filterIsInstance<ResultBean>()

        if (!results.isNullOrEmpty()) {
            val tableHtml = results.joinToString(separator = "") { result ->
                "<tr><td>${result.x}</td><td>${result.y}</td><td>${result.r}</td><td>${result.result}</td><td>${result.userLocalDateTime}</td><td>${result.executionTime} ms</td></tr>"
            }

            val htmlResponse =
                "<table><thead><tr><th>X</th><th>Y</th><th>R</th><th>Результат</th><th>Локальное дата и время</th><th>Время выполнения</th></tr></thead><tbody>$tableHtml</tbody></table>"
            response.writer.write(htmlResponse)
        } else {
            response.writer.write("<p>Нет результатов, чтобы показать.</p>")
        }
    }
}