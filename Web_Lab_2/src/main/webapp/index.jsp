<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ru">

<head>
    <meta charset="utf-8">
    <title>Проверка точки</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">

</head>
<body id="page">
<header>
    <h1>Проверка попадания точки в график</h1>
    <h2>Родионов Артем Алексеевич <span>P3221</span></h2>
    <h2>Вариант - <span>3127</span></h2>
</header>
<div class="content">
    <!-- Notification div -->
    <div id="notification"
         style="display: none; position: absolute; top: 10px; right: 10px; background-color: #f44336; color: white; padding: 10px; border-radius: 5px;">
    </div>

    <form onsubmit="return validate();">
        <table>
            <thead>
            <tr>
                <th colspan="3">Введите данные для определения вхождения точки в представленную область</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>
                    <h3>X</h3>
                    <p>
                        <input id="x_button_-3" type="button" value="-3" onclick="selectX(-3)">
                        <input id="x_button_-2" type="button" value="-2" onclick="selectX(-2)">
                        <input id="x_button_-1" type="button" value="-1" onclick="selectX(-1)">
                        <input id="x_button_0" type="button" value="0" onclick="selectX(0)">
                        <input id="x_button_1" type="button" value="1" onclick="selectX(1)">
                        <input id="x_button_2" type="button" value="2" onclick="selectX(2)">
                        <input id="x_button_3" type="button" value="3" onclick="selectX(3)">
                        <input id="x_button_4" type="button" value="4" onclick="selectX(4)">
                        <input id="x_button_5" type="button" value="5" onclick="selectX(5)">
                        <input type="hidden" name="x" value="">
                    </p>
                </td>
                <td>
                    <h3>Y</h3>
                    <p><label>
                        <input type="text" maxlength="5" size="6" name="y" placeholder=" от -5 до 3">
                    </label></p>
                </td>
                <td>
                    <canvas id="coordinateCanvas" width="300" height="300" style="border: 1px solid black;"></canvas>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <h3>R</h3>
                    <p><label>
                        <input type="text" maxlength="5" size="6" name="r" placeholder=" от 2 до 5">
                    </label></p>
                </td>
                <td style="background: #0000;">
                    <button class="checkButton buttonLetter" type="button" onclick="fetchResults();">
                        Выполнить проверку
                    </button>
                    <button class="clearButton buttonLetter" type="button" onclick="clearTable();">
                        Очистить таблицу
                    </button>
                </td>
            </tr>
            </tbody>
        </table>
    </form>

    <div id="resultsTable">
        <table>
            <thead>
            <tr>
                <th>X</th>
                <th>Y</th>
                <th>R</th>
                <th>Результат</th>
                <th>Локальное дата и время</th>
                <th>Время выполнения</th>
            </tr>
            </thead>
            <tbody>
            <!-- Results will be displayed here -->
            </tbody>
        </table>
    </div>

</div>
<script src="js/main.js"></script>
<script src="js/selectX.js"></script>
<script src="js/validate.js"></script>
<script src="js/drawCoordinatePlane.js"></script>
<script src="js/showNotification.js"></script>

</body>
</html>