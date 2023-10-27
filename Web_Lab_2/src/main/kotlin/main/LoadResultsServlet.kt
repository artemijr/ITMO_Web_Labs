package main

import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

/**
 * LoadResultsServlet is responsible for loading and displaying results from the server.
 */
    @WebServlet(name = "LoadResultsServlet", value = ["/LoadResultsServlet"])
    class LoadResultsServlet : HttpServlet() {
        override fun doGet(request: HttpServletRequest, response: HttpServletResponse) {
            response.characterEncoding = "UTF-8"

            // Retrieve results from the session using ResultBeanComponent
            val resultBeanComponent = ResultBeanComponent()
            val results = resultBeanComponent.getResults(request.session)

            if (results.isNotEmpty()) {
                val tableHtml = results.joinToString(separator = "") { resultData ->
                    "<tr><td>${resultData.x}</td><td>${resultData.y}</td><td>${resultData.r}</td><td>${resultData.result}</td><td>${resultData.userLocalDateTime}</td><td>${resultData.executionTime} ms</td></tr>"
                }

                val htmlResponse = "<table><tbody>$tableHtml</tbody></table>"
                response.writer.write(htmlResponse)
            }
        }
    }
