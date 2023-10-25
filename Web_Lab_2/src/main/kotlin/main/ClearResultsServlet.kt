package main

import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@WebServlet(name = "ClearResultsServlet", value = ["/ClearResultsServlet"])
class ClearResultsServlet : HttpServlet() {
    /**
     * Handles a GET request to clear the results table and session-stored ResultBean objects.
     *
     * @param request The HttpServletRequest.
     * @param response The HttpServletResponse for sending a response back to the client.
     */
    override fun doGet(request: HttpServletRequest, response: HttpServletResponse) {
        response.characterEncoding = "UTF-8"

        // Clear the table by removing the session attribute
        val session = request.session
        session.removeAttribute("results")

        // Send a response indicating the table is cleared
        response.writer.write("<p>Таблица очищена.</p>")
    }
}