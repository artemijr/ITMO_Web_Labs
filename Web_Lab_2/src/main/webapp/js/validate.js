//validate.js

/**
 * validate checks the form inputs for validity and displays error notifications.
 *
 * @returns {boolean} - True if the form inputs are valid; otherwise, false.
 */
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

/**
 * validateR checks if the R value in the form input is set.
 *
 * @returns {boolean} - True if the R value is set; otherwise, false.
 */
function validateR() {
    const r = document.getElementsByName("r")[0].value;
    return r !== "";
}
