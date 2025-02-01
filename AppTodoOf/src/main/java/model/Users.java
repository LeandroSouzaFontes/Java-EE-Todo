package model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "users")  // opcional, caso deseje definir o nome da tabela
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_user;

    private String name_user;
    private String email_user;
    private String password_user;

    // Aqui o mappedBy indica que o relacionamento é gerenciado pelo atributo "user" da entidade Todos.
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Todos> todos = new ArrayList<>();

    // Construtor padrão
    public Users() {
    }

    // Getters e Setters
    public Long getId_user() {
        return id_user;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }

    public String getName_user() {
        return name_user;
    }

    public void setName_user(String name_user) {
        this.name_user = name_user;
    }

    public String getEmail_user() {
        return email_user;
    }

    public void setEmail_user(String email_user) {
        this.email_user = email_user;
    }

    public String getPassword_user() {
        return password_user;
    }

    public void setPassword_user(String password_user) {
        this.password_user = password_user;
    }

    public List<Todos> getTodos() {
        return todos;
    }

    public void setTodos(List<Todos> todos) {
        this.todos = todos;
    }

    // Métodos auxiliares para manter o relacionamento bidirecional

    public void addTodo(Todos todo) {
        todos.add(todo);
        todo.setUser(this);
    }

    public void removeTodo(Todos todo) {
        todos.remove(todo);
        todo.setUser(null);
    }
}