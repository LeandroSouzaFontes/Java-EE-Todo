<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.Todos"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<title>Lista de Todos</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1>Lista de Todos</h1>
	<a href="novoTodo.jsp" class="botaoLink">Novo Todo</a>

	<table id="tabela">
		<thead>
			<tr>
				<th>Id</th>
				<th>Nome da Tarefa</th>
				<th>Visualizar</th>
				<th>Editar</th>
				<th>Excluir</th>
			</tr>
		</thead>
		<tbody>
			<%
			// Obtendo a lista de todos passados pelo Controller
			List<Todos> lista = (List<Todos>) request.getAttribute("todosList");
			if (lista != null) {
				for (Todos todo : lista) {
			%>
			<tr>
				<td><%=todo.getId_todos()%></td>
				<td><%=todo.getTaskName()%></td>
				<td><a href="visualizarTodo?id=<%=todo.getId_todos()%>"
					class="botaoLink">Visualizar</a></td>
				<td>
    			<form action="editarTodo" method="get" 
    			onsubmit="return confirm('Deseja editar o contato?');">
        	<input type="hidden" name="id" value="<%= todo.getId_todos() %>" />
        	<button type="submit" class="botaoLink">Editar</button>
    			</form>
</td>

				
				<td>
					<form action="excluirTodo" method="get"
						onsubmit="return confirm('Deseja realmente excluir este Todo?');">
						<!-- Envia o id do Todo como parâmetro oculto -->
						<input type="hidden" name="id" value="<%=todo.getId_todos()%>" />
						<button type="submit" class="botaoExcluir">Excluir</button>
					</form>
				</td>
			</tr>
			<%
			}
			}
			%>


			<a href ="index.jsp" class="botaoExcluir">Logout</a>

		</tbody>
	</table>
</body>
</html>
