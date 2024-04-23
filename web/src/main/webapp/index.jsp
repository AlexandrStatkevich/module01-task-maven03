<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>List User Processing</title>
</head>
<body>
<h1><%= "Обработка данных листа пользователей" %>
</h1>
<br/>
<form action="userList" method="POST">
    <h3>Получить список всех пользователей <br/></h3>
        <input type="submit" value="ПОЛУЧИТЬ"/>
</form>
<br/>
<form action="userById" method="POST">
    <h3>Получить пользователя по id <br/></h3>
        <h3> ID: <input type="text" name="id"></h3>
    <input type="submit" value="ПОЛУЧИТЬ"/>
</form>
<br/>
<form action="replaceUserNameById" method="POST">
    <h3>Заменить пользователю имя по id <br/></h3>
        <h3>ID: <input type="text" name="id"> </h3>
        <h3>НОВОЕ ИМЯ: <input type="text" name="name"> </h3>
    <input type="submit" value="ЗАМЕНИТЬ"/>
</form>
</body>
</html>
