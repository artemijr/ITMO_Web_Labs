package main

import jakarta.servlet.http.HttpSession

/**
 * ResultBeanComponent is responsible for managing and storing ResultData objects in the server session.
 */
class ResultBeanComponent {

    fun addResult(session: HttpSession, resultData: ResultData) {
        val results = getResults(session)
        results.add(resultData)
        session.setAttribute("results", results)
    }

    fun getResults(session: HttpSession): MutableList<ResultData> {
        return session.getAttribute("results") as? MutableList<ResultData> ?: mutableListOf()
    }

    fun clearResults(session: HttpSession) {
        session.removeAttribute("results")
    }
}
