package main

import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@WebServlet(name = "ControllerServlet", value = ["/ControllerServlet"])
class ControllerServlet : HttpServlet() {
    override fun doGet(request: HttpServletRequest, response: HttpServletResponse) {
        response.characterEncoding = "UTF-8"

        // Check if the request contains point coordinates and radius
        val x = request.getParameter("x")
        val y = request.getParameter("y")
        val r = request.getParameter("r")

        if (x != null && y != null && r != null) {
            // Forward the request to AreaCheckServlet
            request.getRequestDispatcher("AreaCheckServlet").forward(request, response)
        } else {
            // Forward the request to the JSP page
            request.getRequestDispatcher("index.jsp").forward(request, response)
        }
    }
}