package model;

import javax.persistence.*;

@Entity
@Table(name = "todos") // opcional, para definir o nome da tabela
public class Todos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_todos;

    private String taskName;

    // Mapeamento ManyToOne: cada Todo pertence a um User.
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    // Construtor padrão
    public Todos() {
    }

    // Getters e Setters
    public Long getId_todos() {
        return id_todos;
    }

    public void setId_todos(Long id_todos) {
        this.id_todos = id_todos;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}