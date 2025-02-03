<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.Todos" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Editar Tarefa</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <h1>Editar Tarefa</h1>

    <%
        Todos todo = (Todos) request.getAttribute("todo");
    %>

    <form action="editarTodo" method="post">
    <input type="hidden" name="id" value="<%= todo.getId_todos() %>" />
    <label for="taskName">Nome da Tarefa:</label>
    <input type="text" id="taskName" name="taskName" value="<%= todo.getTaskName() %>" required />

    <button type="submit" class="botaoLink">Salvar Alterações</button>
</form>


</body>
</html>
