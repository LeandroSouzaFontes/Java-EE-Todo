package ejb;

import java.util.Optional;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import model.Users;

/**
 * Session Bean implementation class UserService
 */
@Stateless
@LocalBean
public class UserService {
	@PersistenceContext
	private EntityManager eManager;
    public UserService( ) {
    	
    }
    


public Optional<Users>  register(Users user) {
	

	try {
		eManager.persist(user);
	}catch(Exception e) {
		return Optional.empty();
	}
	return Optional.of(user) ;
}

public Users login(String email, String password) {
	   try {
           TypedQuery<Users> query = eManager.createQuery(
               "SELECT u FROM Users u WHERE u.email_user = :email AND u.password_user = :password", 
               Users.class
           );
           query.setParameter("email", email);
           query.setParameter("password", password);
           
           return query.getSingleResult(); // Retorna o usuário se encontrado
       } catch (Exception e) {
           System.out.println("Usuário não encontrado ou erro: " + e.getMessage());
           return null;
       }
}
}