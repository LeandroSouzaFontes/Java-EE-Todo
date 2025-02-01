<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registrar</title>
</head>
<body>
	<h1>Welcome sir</h1>
	<form name ="frmRegister" action="register" method ="post" >
	<table>
	<tr>
		<td>
			<input type ="text" name ="name" placeholder ="Nome">
		</td>
	</tr>
	<tr>
		<td>
			<input type ="text" name ="email" placeholder ="email">
		</td>
	</tr>
	<tr>
		<td>
			<input type ="text" name ="senha" placeholder ="senha">
		</td>
	</tr>
	<tr>
		<td>
			<input type ="text" name ="confirmarSenha" placeholder ="confirmarSenha">
		</td>
	</tr>
	
	</table>
	<input type ="button" value = "Registrar" onclick="validar()">
	</form>
	<% String errorMessage = (String) request.getAttribute("errorMessage"); %>
	<% if (errorMessage != null) { %>
    <p style="color: red;"><%= errorMessage %></p>
<% } %>
	<script src = "scripts/validador.js"></script>
	
	
</body>
</html>