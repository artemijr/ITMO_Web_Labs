/**
 * LoadResultsServlet is responsible for loading and displaying results, and optionally clearing them.
 */
package main

import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@WebServlet(name = "LoadResultsServlet", value = ["/LoadResultsServlet"])
class LoadResultsServlet : HttpServlet() {
    /**
     * Handles the GET request for loading and displaying results.
     *
     * @param request The HTTP request object.
     * @param response The HTTP response object.
     */
    override fun doGet(request: HttpServletRequest, response: HttpServletResponse) {
        response.characterEncoding = "UTF-8"

        // Retrieve results from the session, if available
        val results = (request.session.getAttribute("results") as? List<*>)?.filterIsInstance<ResultBean>()

        if (!results.isNullOrEmpty()) {
            // If there are results, format them into an HTML table
            val tableHtml = results.joinToString(separator = "") { result ->
                "<tr><td>${result.x}</td><td>${result.y}</td><td>${result.r}</td><td>${result.result}</td><td>${result.userLocalDateTime}</td><td>${result.executionTime} ms</td></tr>"
            }

            val htmlResponse =
                "<table><tbody>$tableHtml</tbody></table>"
            response.writer.write(htmlResponse)
        }
    }
}