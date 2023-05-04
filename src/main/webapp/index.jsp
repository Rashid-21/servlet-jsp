<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>

<form action="hello-servlet" method="get">
    <h1>Please, enter name to find user: </h1>
    <input name="id">
</form>


<form action="hello-servlet" method="post">
    <h3>Name:</h3>
    <input name="name">
    <h3>Surname:</h3>
    <input name="surname">
    <h3>Age:</h3>
    <input name="age">
    <button type="submit" value="submit">Save</button>
</form>

</body>
</html>


