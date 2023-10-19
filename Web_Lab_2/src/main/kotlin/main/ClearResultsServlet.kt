package main

import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@WebServlet(name = "ClearResultsServlet", value = ["/ClearResultsServlet"])
class ClearResultsServlet : HttpServlet() {
    override fun doGet(request: HttpServletRequest, response: HttpServletResponse) {
        response.characterEncoding = "UTF-8"

        // Clear the table by removing the session attribute
        val session = request.session
        session.removeAttribute("results")

        response.writer.write("<p>Таблица очищена.</p>")
    }
}