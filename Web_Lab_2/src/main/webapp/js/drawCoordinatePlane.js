//drawCoordinatePlane.js

function drawCoordinatePlane() {
    const canvas = document.getElementById("coordinateCanvas");
    const context = canvas.getContext("2d");

    // Clear the canvas
    context.clearRect(0, 0, canvas.width, canvas.height);

    const centerX = canvas.width / 2;
    const centerY = canvas.height / 2;

    // Draw the areas in each quadrant
    context.fillStyle = "blue";

    // First quadrant
    context.fillRect(centerX, centerY, 0.35 * centerX, -centerY*0.7);

    // Third quadrant (triangle)
    context.beginPath();
    context.moveTo(centerX - 0.35 * centerX, centerY);
    context.lineTo(centerX, centerY);
    context.lineTo(centerX, centerY + 0.7 * centerY);
    context.closePath();
    context.fill();

    // Fourth quadrant (circle)
    context.beginPath();
    context.arc(centerX, centerY, 0.7 * centerX, 0, 0.5 * Math.PI);
    context.lineTo(centerX, centerY);
    context.closePath();
    context.fill();

    // Divisions marked at R, R/2, -R, -R/2
    context.fillStyle = "black";
    context.fillText("R", centerX + 0.7 * centerX, centerY - 5);
    context.fillText("R/2", centerX + 0.35 * centerX, centerY - 5);
    context.fillText("-R", centerX - 0.7 * centerX, centerY - 5);
    context.fillText("-R/2", centerX - 0.35 * centerX, centerY - 5);

    context.fillText("R", centerX + 5, centerY - 0.7 * centerY);
    context.fillText("R/2", centerX + 5, centerY - 0.35 * centerY);
    context.fillText("-R", centerX + 5, centerY + 0.7 * centerY);
    context.fillText("-R/2", centerX + 5, centerY + 0.35 * centerY);

    // Draw the coordinate axes
    context.beginPath();
    context.moveTo(centerX, 0);
    context.lineTo(centerX, canvas.height);
    context.moveTo(0, centerY);
    context.lineTo(canvas.width, centerY);
    context.strokeStyle = "black";
    context.lineWidth = 2;
    context.stroke();

    // Labels for X and Y axes
    context.font = "bold 12px Arial";
    context.fillText("X", canvas.width - 10, centerY - 5);
    context.fillText("Y", centerX + 5, 15);

    // Arrowheads for X and Y axes
    drawArrowhead(context, canvas.width , centerY, -Math.PI / -2);
    drawArrowhead(context, centerX, 0, 0);

}

// Call this function to initially draw the coordinate plane
drawCoordinatePlane();

function drawArrowhead(context, x, y, angle) {
    const arrowSize = 10;
    context.save();
    context.translate(x, y);
    context.rotate(angle);

    context.beginPath();
    context.moveTo(0, 0);
    context.lineTo(-arrowSize, arrowSize);
    context.lineTo(arrowSize, arrowSize);
    context.closePath();
    context.fill();

    context.restore();
}
