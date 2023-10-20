/**
 * ControllerServlet is responsible for routing requests based on the presence of
 * point coordinates and the radius parameter.
 */
package main

import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@WebServlet(name = "ControllerServlet", value = ["/ControllerServlet"])
class ControllerServlet : HttpServlet() {
    /**
     * Handles the GET request.
     *
     * @param request The HTTP request object.
     * @param response The HTTP response object.
     */
    override fun doGet(request: HttpServletRequest, response: HttpServletResponse) {
        response.characterEncoding = "UTF-8"

        // Check if the request contains point coordinates and radius
        val x = request.getParameter("x")
        val y = request.getParameter("y")
        val r = request.getParameter("r")

        if (x != null && y != null && r != null) {
            // If coordinates and radius are provided, forward the request to AreaCheckServlet
            request.getRequestDispatcher("AreaCheckServlet").forward(request, response)
        } else {
            // If not all required parameters are provided, forward the request to the JSP page (index.jsp)
            request.getRequestDispatcher("index.jsp").forward(request, response)
        }
    }
}
