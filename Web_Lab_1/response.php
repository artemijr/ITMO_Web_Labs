<?php
// Retrieve values from the GET request
$x = isset($_GET['x']) ? intval($_GET['x']) : null;
$y = $_GET['y'];
$r = $_GET['r'];
$start_time = microtime(true); // Record the start time of the script execution
$userLocalDateTime = $_GET['userLocalDateTime'];

$result = ""; // Initialize the result variable

// Define the valid set of x values
$validXValues = [-3, -2, -1, 0, 1, 2, 3, 4, 5];

try {
    // Check if X, Y, and R are set and within the valid range
    if ($x !== null && is_numeric($y) && $y >= -5 && $y <= 3 && is_numeric($r) && $r >= 2 && $r <= 5 && in_array($x, $validXValues)) {
        // Perform calculations to determine if the point falls within the specified area

        // Check which quarter the point falls into
        if ($x >= 0 && $y >= 0) {
            // 1st coordinate quarter
            if ($y <= $r && $x <= $r / 2) {
                $result = "Точка входит в область определения";
            } else {
                $result = "Точка не входит в область определения";
            }
        } elseif ($x <= 0 && $y >= 0) {
            // 2nd coordinate quarter
            $result = "Точка не входит в область определения";
        } elseif ($x <= 0 && $y <= 0) {
            // 3rd coordinate quarter
            if ($y >= -$r && $x >= -$r / 2) {
                $result = "Точка входит в область определения";
            } else {
                $result = "Точка не входит в область определения";
            }
        } elseif ($x >= 0 && $y <= 0) {
            // 4th coordinate quarter (part of a circle)
            if (($x * $x + $y * $y) <= ($r * $r) / 4) {
                $result = "Точка входит в область определения";
            } else {
                $result = "Точка не входит в область определения";
            }
        }
    } else {
        // Handle invalid input
        throw new Exception("Неверный ввод. Пожалуйста, убедитесь, что вы вводите корректные значения (целые числа и допустимые значения x) из области определения.");
    }

    $end_time = microtime(true); // Record the end time of the script execution
    $execution_time_ms = number_format(($end_time - $start_time) * 1000, 2);
    // Save the result and timestamp in a session variable
    session_start();
    $_SESSION['results'][] = [
        'x' => $x,
        'y' => $y,
        'r' => $r,
        'result' => $result,
        'timestamp' => $userLocalDateTime,
        'execution_time' => $execution_time_ms, 
    ];

    // Return success status
    http_response_code(200);
} catch (Exception $e) {
    // Handle exceptions and return appropriate error status and message
    http_response_code(400);
    echo json_encode(["error" => $e->getMessage()]);
    exit;
}

// Display the result in an HTML table
echo '<table>';
echo '<thead><tr><th>X</th><th>Y</th><th>R</th><th>Результат</th><th>Локальное дата и время</th><th>Время выполнения</th></tr></thead>';
echo '<tbody>';
/**
 * @param $entry
 * @return void
 */
function extracted($entry)
{
    echo '<tr>';
    echo '<td>' . $entry['x'] . '</td>';
    echo '<td>' . $entry['y'] . '</td>';
    echo '<td>' . $entry['r'] . '</td>';
    echo '<td>' . $entry['result'] . '</td>';
    echo '<td>' . $entry['timestamp'] . '</td>';
    echo '<td>' . $entry['execution_time'] . ' ms</td>';
}

foreach ($_SESSION['results'] as $entry) {
    extracted($entry);
}
echo '</tbody>';
echo '</table>';
