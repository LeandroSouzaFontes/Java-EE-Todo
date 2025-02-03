package controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ejb.TodoService;
import ejb.UserService;
import model.Todos;
import model.Users;

@WebServlet(urlPatterns = {"/Controller", "/register", "/logar", "/ToDo", "/novoTodo", "/excluirTodo", "/visualizarTodo", "/editarTodo", "/logout"})
public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @EJB
    private UserService userService;
    
    @EJB
    private TodoService todoService;
    
    public Controller() {
        super();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        
        if (path.equals("/editarTodo")) {
            editarTodo(request, response);
            return;
        } else if (path.equals("/excluirTodo")) {
            excluirTodo(request, response);
            return;
        } else if (path.equals("/visualizarTodo")) {
            visualizarTodo(request, response);
            return;
        } else if (path.equals("/ToDo")) {
            todoList(request, response);
            return;
        }else if (path.equals("/logout")) {
            logout(request, response); 
            return;
        }
        else {
            response.sendRedirect("index.jsp");
            return;
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        
        if (path.equals("/register")) {
            registrarUser(request, response);
            return;
        } else if (path.equals("/logar")) {
            logarUser(request, response);
            return;
        } else if (path.equals("/novoTodo")) {
            novoTodo(request, response);
            return;
            
        }else if (path.equals("/editarTodo")) {
            editarTodoPost(request, response); // Método para processar o POST de edição
            return;
        } 
        else {
            response.sendRedirect("index.jsp");
            return;
        }
    }
    
    private void registrarUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        
        if (name == null || name.trim().isEmpty() ||
            email == null || email.trim().isEmpty() ||
            senha == null || senha.trim().isEmpty()) {
            request.setAttribute("errorMessage", "Todos os campos são obrigatórios.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }
        
        Users user = new Users();
        user.setName_user(name);
        user.setEmail_user(email);
        user.setPassword_user(senha);
        
        Optional<Users> registeredUser = userService.register(user);
        if (registeredUser.isPresent()) {
            response.sendRedirect("login.jsp");
            return;
        } else {
            request.setAttribute("errorMessage", "Este e-mail já está cadastrado!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }
    }
    
    private void logarUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        
        if (email == null || email.trim().isEmpty() ||
            senha == null || senha.trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Email e senha são obrigatórios.");
            return;
        }
        
        Users user = userService.login(email, senha);
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            // Após o login, redireciona para o controlador que carrega a lista de todos.
            response.sendRedirect("ToDo");
            return;
        } else {
            request.setAttribute("errorMessage", "Usuário ou senha inválidos!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
    }
    
    private void todoList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("user");
        
        if (user != null) {
            List<Todos> todosList = todoService.getTodosByUser(user);
            request.setAttribute("todosList", todosList);
            request.getRequestDispatcher("todos.jsp").forward(request, response);
            return;
        } else {
            response.sendRedirect("login.jsp");
            return;
        }
    }
    
    private void novoTodo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String taskName = request.getParameter("taskName");
        if (taskName == null || taskName.trim().isEmpty()) {
            request.setAttribute("errorMessage", "O nome da tarefa é obrigatório.");
            request.getRequestDispatcher("novoTodo.jsp").forward(request, response);
            return;
        }
        
        Todos newTodo = new Todos();
        newTodo.setTaskName(taskName);
        
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("user");
        newTodo.setUser(user);
        
        todoService.createTodo(newTodo);
        // Após criar o novo todo, redireciona para o controlador que atualiza a lista.
        response.sendRedirect("ToDo");
        return;
    }
    
    private void excluirTodo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long todoId = Long.valueOf(request.getParameter("id"));
            todoService.deleteTodo(todoId);
        } catch(NumberFormatException e) {
            // Você pode logar o erro ou definir uma mensagem de erro na requisição, se necessário.
        }
        // Após excluir, redireciona para atualizar a lista de todos.
        response.sendRedirect("ToDo");
        return;
    }
    
    private void visualizarTodo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long todoId = Long.valueOf(request.getParameter("id"));
            Todos todo = todoService.idTodo(todoId);
            if (todo != null) {
                request.setAttribute("todo", todo);
                request.getRequestDispatcher("visualizarTodo.jsp").forward(request, response);
                return;
            }
        } catch (NumberFormatException e) {
            // Log da exceção ou tratamento adicional, se necessário.
        }
        response.sendRedirect("ToDo");
        return;
    }
    
    private void editarTodo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long todoId = Long.valueOf(request.getParameter("id"));
            Todos todo = todoService.idTodo(todoId);

            if (todo != null) {
                request.setAttribute("todo", todo);
                request.getRequestDispatcher("editarTodo.jsp").forward(request, response);
            } else {
                response.sendRedirect("todos.jsp");  // Redireciona se o Todo não for encontrado
            }
        } catch (Exception e) {
            response.sendRedirect("todos.jsp");  // Caso ocorra um erro, redireciona para a lista
        }
    }

    private void editarTodoPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long todoId = Long.valueOf(request.getParameter("id"));
            String taskName = request.getParameter("taskName");

            if (taskName != null && !taskName.trim().isEmpty()) {
                Todos todo = todoService.idTodo(todoId);
                if (todo != null) {
                    todo.setTaskName(taskName);  // Atualiza o nome da tarefa
                    todoService.updateTodo(todo); // Persiste a alteração no banco de dados
                    response.sendRedirect("ToDo"); // Redireciona para a lista de Todos
                } else {
                    response.sendRedirect("ToDo"); // Caso o Todo não seja encontrado
                }
            } else {
                request.setAttribute("errorMessage", "O nome da tarefa não pode estar vazio!");
                request.getRequestDispatcher("editarTodo.jsp").forward(request, response);
            }
        } catch (Exception e) {
            response.sendRedirect("ToDo"); // Redireciona caso ocorra algum erro
        }
    }
    
    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); 
        if (session != null) {
            session.invalidate(); 
        }
        
        response.sendRedirect("index.jsp");
    }
    }


