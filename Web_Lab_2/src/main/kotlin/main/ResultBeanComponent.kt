package main

import jakarta.servlet.http.HttpSession

class ResultBeanComponent {

    fun addResult(session: HttpSession, resultData: ResultData) {
        val results = getResults(session)
        results.add(resultData)
        session.setAttribute("results", results)
    }

    private fun getResults(session: HttpSession): MutableList<ResultData> {
        return session.getAttribute("results") as? MutableList<ResultData> ?: mutableListOf()
    }

    fun clearResults(session: HttpSession) {
        session.removeAttribute("results")
    }
}
