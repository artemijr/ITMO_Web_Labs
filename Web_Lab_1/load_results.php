<?php
// Start the session
session_start();

if (!empty($_SESSION['results'])) {
    echo '<table>';
    echo '<thead><tr><th>X</th><th>Y</th><th>R</th><th>Результат</th><th>Локальное дата и время</th><th>Время выполнения</th></tr></thead>';
    echo '<tbody>';
    foreach ($_SESSION['results'] as $entry) {
        extracted($entry);
        echo '</tr>';
    }
    echo '</tbody>';
    echo '</table>';
} else {
    echo '<p>Нет результатов, чтобы показать.</p>';
}
