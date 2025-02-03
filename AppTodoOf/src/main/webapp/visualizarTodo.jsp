<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.Todos" %>
<%@ page import="javax.servlet.jsp.jstl.core.Config" %>
<%@ page import="ejb.TodoService" %>
<%@ page import="javax.ejb.EJB" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Visualizar Todo</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <h1>Visualizar Todo</h1>

    <%
        // Recupera o Todo do atributo da requisição
        Todos todo = (Todos) request.getAttribute("todo");
        
        if (todo != null) {
    %>
    
    <table>
        <tr>
            <td><strong>ID:</strong></td>
            <td><%= todo.getId_todos() %></td>
        </tr>
        <tr>
            <td><strong>Nome da Tarefa:</strong></td>
            <td><%= todo.getTaskName() %></td>
        </tr>
        <tr>
            <td><strong>Usuário:</strong></td>
            <td><%= todo.getUser().getName_user() %></td>
        </tr>
    </table>
    
    <a href="ToDo" class="botaoLink">Voltar para a lista</a>

    <%
        } else {
    %>
        <p>Tarefa não encontrada!</p>
        <a href="ToDo" class="botaoLink">Voltar para a lista</a>
    <%
        }
    %>

</body>
</html>
