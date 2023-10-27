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
        val results = (request.session.getAttribute("results") as? List<*>)?.filterIsInstance<ResultData>()

        if (!results.isNullOrEmpty()) {
            val tableHtml = results.joinToString(separator = "") { resultData ->
                "<tr><td>${resultData.x}</td><td>${resultData.y}</td><td>${resultData.r}</td><td>${resultData.result}</td><td>${resultData.userLocalDateTime}</td><td>${resultData.executionTime} ms</td></tr>"
            }

            val htmlResponse = "<table><tbody>$tableHtml</tbody></table>"
            response.writer.write(htmlResponse)
        }
    }
}