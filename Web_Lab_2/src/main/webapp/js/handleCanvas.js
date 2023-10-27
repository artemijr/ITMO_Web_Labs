//handleCanvas.js

/**
 * handleCanvasClick handles the canvas click event, drawing a point and sending
 * server-side coordinates for verification.
 *
 * @param {MouseEvent} event - The click event.
 */
function handleCanvasClick(event) {
    // Retrieve the canvas and its context
    const canvas = document.getElementById("coordinateCanvas");
    const context = canvas.getContext("2d");

    // Get the canvas coordinates of the click
    const rect = canvas.getBoundingClientRect();
    const x = event.clientX - rect.left;
    const y = event.clientY - rect.top;

    // Check if R is set (you might need to retrieve it from the form)
    if (validateR()) {
        // Draw a point on the canvas
        context.beginPath();
        context.arc(x, y, 3, 0, 2 * Math.PI);
        context.fillStyle = "red";
        context.fill();
        context.closePath();

        // Send the server-side coordinates for verification
        const r = document.getElementsByName("r")[0].value;
        sendCoordinatesForVerification(x, y, r);
    } else {
        // R is not set, show a notification
        showNotification("Please set the radius (R) before clicking.");
    }
}

/**
 * sendCoordinatesForVerification sends the server-side coordinates for verification.
 *
 * @param {number} xCanvas - X-coordinate on the canvas.
 * @param {number} yCanvas - Y-coordinate on the canvas.
 * @param {number} r - The radius value.
 */
function sendCoordinatesForVerification(xCanvas, yCanvas, r) {
    // Calculate the server-side x and y values from canvas coordinates
    const canvas = document.getElementById("coordinateCanvas");
    const canvasWidth = canvas.width;
    const canvasHeight = canvas.height;

    // Calculate the server-side coordinates considering the entire canvas area
    const offsetX = canvasWidth / 2;
    const offsetY = canvasHeight / 2;
    const serverX = (xCanvas - offsetX) * (2 * r / canvasWidth / 0.7);
    const serverY = (offsetY - yCanvas) * (2 * r / canvasHeight / 0.7);

    const userLocalDateTime = new Date().toLocaleString();
    const contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
    const url = `${contextPath}/AreaCheckServlet?x=${serverX}&y=${serverY}&r=${r}&userLocalDateTime=${userLocalDateTime}`;

    // Use the shared function to send the request
    sendRequest(url, (response) => {
        // Use the shared function to append the result to the table
        appendResultToTable(response);
    });
}