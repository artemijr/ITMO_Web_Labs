package main

import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse


/**
 * ClearResultsServlet is responsible for clearing the results table and session-stored ResultData objects.
 */
@WebServlet(name = "ClearResultsServlet", value = ["/ClearResultsServlet"])
class ClearResultsServlet : HttpServlet() {
    override fun doGet(request: HttpServletRequest, response: HttpServletResponse) {
        response.characterEncoding = "UTF-8"

        // Clear the table by removing the session attribute using ResultBeanComponent
        val resultBeanComponent = ResultBeanComponent()
        resultBeanComponent.clearResults(request.session)

        // Send a response indicating the table is cleared
        response.writer.write("<p>Таблица очищена.</p>")
    }
}
