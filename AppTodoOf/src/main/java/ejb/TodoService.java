package ejb;

import java.util.List;
import java.util.Optional;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.jboss.security.auth.spi.Users.User;

import model.Todos;
import model.Users;

/**
 * Session Bean implementation class TodoService
 */
@Stateless
@LocalBean
public class TodoService {
	@PersistenceContext
    private EntityManager eManager;
    public TodoService() {
        //  Auto-generated constructor stub
    }
    
    
    public Optional<Todos> createTodo(Todos todo) {
    	
    	try {
    		
    		eManager.persist(todo);
    	}catch(Exception e) {
    		return Optional.empty();
    		}
    	return Optional.of(todo);
    }
    
    
    			public void updateTodo(Todos todo) {
    			    try {
    			        // Fazendo o merge do Todo, que irá atualizar os campos no banco
    			        eManager.merge(todo);
    			    } catch (Exception e) {
    			        System.out.println("Erro ao atualizar o Todo: " + e.getMessage());
    			    }
    			}

    	
       
    
    
    public Todos deleteTodo(Long todoId) {
    	
    	Todos todo = null;
    	try {
			todo = eManager.find(Todos.class, todoId);
			eManager.remove(todo);
			
		}catch(Exception e) {
            System.out.println("Erro ao excluir o todo: " + e.getMessage());
		}
		return todo;
    }
    
    public List<Todos> getTodosByUser(Users user) {
    	String selectTodos = "select t from Todos t where t.user = :user";       
    	TypedQuery<Todos> typedQuery = eManager.createQuery(selectTodos, Todos.class)
                .setParameter("user", user);  // Passa o objeto completo do usuário
        return typedQuery.getResultList();
    }
    
    public Todos idTodo(Long id) {
    	return eManager.find(Todos.class, id);
    }
    

}
