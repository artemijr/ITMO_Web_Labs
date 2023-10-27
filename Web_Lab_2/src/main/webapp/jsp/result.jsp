<jsp:useBean id="resultData" scope="request" type="main.ResultData"/>
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <title>Результат проверки</title>
</head>
<body>
<table>
    <tr>
        <td><%= resultData.getX() %>
        </td>
        <td><%= resultData.getY() %>
        </td>
        <td><%= resultData.getR() %>
        </td>
        <td><%= resultData.getResult() %>
        </td>
        <td><%= resultData.getUserLocalDateTime() %>
        </td>
        <td><%= resultData.getExecutionTime() %> ms</td>

    </tr>
</table>
</body>
</html>
