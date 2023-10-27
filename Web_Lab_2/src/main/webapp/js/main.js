/**
 * fetchResults sends an HTTP request to the server to perform point verification.
 * It processes the server's response and updates the results table.
 */
function fetchResults() {
    if (validate()) {
        const x = document.getElementsByName("x")[0].value;
        const y = document.getElementsByName("y")[0].value;
        const r = document.getElementsByName("r")[0].value;
        const userLocalDateTime = new Date().toLocaleString();
        const contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
        const url = `${contextPath}/AreaCheckServlet?x=${x}&y=${y}&r=${r}&userLocalDateTime=${userLocalDateTime}`;

        // Use the shared function to send the request
        sendRequest(url, (response) => {
            // Use the shared function to append the result to the table
            appendResultToTable(response);
        });
    }
}


/**
 * clearTable clears the displayed results table and removes results from Local Storage.
 */
function clearTable() {
    const resultsElement = document.getElementById("resultsTable").querySelector("tbody");
    resultsElement.innerHTML = "";

    // Send a request to clear the session-stored results
    const contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
    const url = `${contextPath}/ClearResultsServlet`;

    // Use the shared function to send the request
    sendRequest(url, (response) => {
        // Display a confirmation message
        showNotification(response);
    });
}

/**
 * Load results from Local Storage and append them to the table on page load.
 */
document.addEventListener("DOMContentLoaded", () => {
    fetchServerResults();
});

function fetchServerResults() {
    const contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
    const url = `${contextPath}/LoadResultsServlet`;

    // Use the shared function to send the request
    sendRequest(url, (response) => {
        // Use the shared function to append the result to the table
        appendResultToTable(response);
    });
}