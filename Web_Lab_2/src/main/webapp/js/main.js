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
        const xhr = new XMLHttpRequest();
        xhr.open("GET", url, true);

        xhr.onreadystatechange = () => {
            if (xhr.readyState === 4 && xhr.status === 200) {
                // Display the result in the results table
                const resultsElement = document.getElementById("resultsTable").querySelector("tbody");
                const newRow = document.createElement("tr");
                newRow.innerHTML = xhr.responseText;
                resultsElement.appendChild(newRow);
            }
        };

        xhr.send();
    }
}


/**
 * clearTable clears the displayed results table and removes results from Local Storage.
 */
function clearTable() {
    const resultsElement = document.getElementById("resultsTable").querySelector("tbody");
    resultsElement.innerHTML = "";

    // Send a request to clear the session-stored results
    const xhr = new XMLHttpRequest();
    xhr.open("GET", "ClearResultsServlet", true);

    xhr.onreadystatechange = () => {
        if (xhr.readyState === 4 && xhr.status === 200) {
            // Display a confirmation message
            showNotification(xhr.responseText);

            // Clear the session-stored results from the ResultBean array in the main.js
            const contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
            const url = `${contextPath}/ClearResultsServlet`;
            const clearResultsXhr = new XMLHttpRequest();
            clearResultsXhr.open("GET", url, true);
            clearResultsXhr.send();
        }
    };

    xhr.send();
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
    const xhr = new XMLHttpRequest();
    xhr.open("GET", url, true);

    xhr.onreadystatechange = () => {
        if (xhr.readyState === 4 && xhr.status === 200) {
            const resultsElement = document.getElementById("resultsTable").querySelector("tbody");
            resultsElement.innerHTML = xhr.responseText;
        }
    };

    xhr.send();
}