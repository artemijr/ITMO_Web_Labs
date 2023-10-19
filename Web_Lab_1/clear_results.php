<?php
// Start the session
session_start();

// Clear the 'results' session variable
unset($_SESSION['results']);

// Return a message indicating that the table has been cleared
echo '<p>Таблица очищена.</p>';
?>