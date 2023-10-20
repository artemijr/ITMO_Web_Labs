//main.js

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
                const resultsElement = document.getElementById("resultsTable").querySelector("tbody");

                // Create a new row and append it to the existing results table
                const newRow = document.createElement("tr");
                newRow.innerHTML = xhr.responseText;

                resultsElement.appendChild(newRow);

                // Store the new results in LocalStorage
                const currentResults = localStorage.getItem("results") || "[]";
                const resultsArray = JSON.parse(currentResults);
                resultsArray.push(xhr.responseText);
                localStorage.setItem("results", JSON.stringify(resultsArray));
            }
        };

        xhr.send();
    }
}

function clearTable() {
    // Clear the displayed table
    const resultsElement = document.getElementById("resultsTable").querySelector("tbody");
    resultsElement.innerHTML = "";

    // Clear results from Local Storage
    localStorage.setItem("results", JSON.stringify([]));

    const xhr = new XMLHttpRequest();
    xhr.open("GET", "ClearResultsServlet", true);

    xhr.onreadystatechange = () => {
        if (xhr.readyState === 4 && xhr.status === 200) {
            // Display a confirmation message
            const notification = document.getElementById("notification");
            notification.innerHTML = xhr.responseText;
            notification.style.display = "block";
        }
    };

    xhr.send();
}


document.addEventListener("DOMContentLoaded", () => {
    // Load results from LocalStorage and append them to the table
    const savedResults = localStorage.getItem("results");
    if (savedResults) {
        const resultsArray = JSON.parse(savedResults);
        const resultsElement = document.getElementById("resultsTable").querySelector("tbody");

        for (const result of resultsArray) {
            const newRow = document.createElement("tr");
            newRow.innerHTML = result;
            resultsElement.appendChild(newRow);
        }
    }
});