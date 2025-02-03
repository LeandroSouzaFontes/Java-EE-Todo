<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login for todo</title>
</head>
<body>
<h1>Welcome sir, pls put u data</h1>
<form name ="frmLogin" action ="logar" method ="post">
<label><strong>Email</strong></label><br>
<input type ="text"name ="email" placeholder ="Insira o seu email">
<br><label><strong>Senha</strong></label><br>
<input type ="text"name ="senha" placeholder ="Insira o sua senha">

<input type ="submit" value ="Logar">
</form>
</body>
</html>