let selectedX = null; // Store the currently selected X value

function selectX(value) {
    // Deselect the previously selected button (if any)
    const prevButton = document.getElementById("x_button_" + selectedX);
    if (prevButton) {
        prevButton.style.backgroundColor = "#4287f5"; // Standard color
    }

    // Select the new button and set the selectedX value
    selectedX = value;
    const button = document.getElementById("x_button_" + value);
    if (button) {
        button.style.backgroundColor = "#f00"; // New color
    }

    // Set the selected X value in the hidden input field
    const xInput = document.getElementsByName("x")[0];
    if (xInput) {
        xInput.value = value;
    }
}

function showNotification(message) {
    const notification = document.getElementById("notification");
    notification.innerHTML = message;
    notification.style.display = "block";

    // Hide the notification after 3 seconds (3000 milliseconds)
    setTimeout(() => {
        notification.style.display = "none";
    }, 3000);
}

function validate() {
    let isValid = true;

    const xSelect = document.getElementsByName("x")[0].value;
    if (!xSelect) {
        showNotification("Please select an X value.");
        isValid = false;
    }

    const yText = document.getElementsByName("y")[0].value;
    if (!/^[+-]?([0-9]*[.])?[0-9]+$/.test(yText) || !isFinite(parseFloat(yText)) || parseFloat(yText) < -5 || parseFloat(yText) > 3) {
        showNotification("Y value must be between -5 and 3.");
        isValid = false;
    }

    const rText = document.getElementsByName("r")[0].value;
    if (!/^[+-]?([0-9]*[.])?[0-9]+$/.test(rText) || !isFinite(parseFloat(rText)) || parseFloat(rText) < 2 || parseFloat(rText) > 5) {
        showNotification("R value must be between 2 and 5.");
        isValid = false;
    }

    return isValid;
}

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
