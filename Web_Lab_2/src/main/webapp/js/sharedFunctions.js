


/**
 * sendRequest sends an HTTP request to the server with the provided URL.
 *
 * @param {string} url - The URL to send the request to.
 * @param {Function} successCallback - A callback function to handle a successful response.
 */
function sendRequest(url, successCallback) {
    const xhr = new XMLHttpRequest();
    xhr.open("GET", url, true);

    xhr.onreadystatechange = () => {
        if (xhr.readyState === 4 && xhr.status === 200) {
            successCallback(xhr.responseText);
        }
    };

    xhr.send();
}

/**
 * appendResultToTable appends a result to the results table.
 *
 * @param {string} result - The result to append.
 */
function appendResultToTable(result) {
    const resultsElement = document.getElementById("resultsTable").querySelector("tbody");
    const newRow = document.createElement("tr");
    newRow.innerHTML = result;
    resultsElement.appendChild(newRow);
}
