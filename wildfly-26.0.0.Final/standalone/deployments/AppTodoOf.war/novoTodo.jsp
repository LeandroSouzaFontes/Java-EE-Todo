<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Welcome put u need</h1>
	<form name ="frmRegister" action="novoTodo" method ="post" >
	<table>
	<tr>
		
		<td>
			<input type ="text" name ="taskName" placeholder ="Escreva sua task">
		</td>
	</tr>
	
	</table>
	<input type ="submit" value = "Registrar" onclick="validarEditTodo()">
	</form>
	<% String errorMessage = (String) request.getAttribute("errorMessage"); %>
	<% if (errorMessage != null) { %>
    <p style="color: red;"><%= errorMessage %></p>
<% } %>
	<script src = "scripts/validador.js"></script>
</body>
</html>