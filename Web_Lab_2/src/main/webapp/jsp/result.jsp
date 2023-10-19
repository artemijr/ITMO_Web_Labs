<jsp:useBean id="resultBean" scope="request" type="main.ResultBean"/>
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
        <td><%= resultBean.getX() %>
        </td>
        <td><%= resultBean.getY() %>
        </td>
        <td><%= resultBean.getR() %>
        </td>
        <td><%= resultBean.getResult() %>
        </td>
        <td><%= resultBean.getUserLocalDateTime() %>
        </td>
        <td><%= resultBean.getExecutionTime() %> ms</td>

    </tr>
</table>
</body>
</html>
