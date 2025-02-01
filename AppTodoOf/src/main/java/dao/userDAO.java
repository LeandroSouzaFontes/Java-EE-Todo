//package dao;
//
//import java.util.List;
//
//import javax.persistence.EntityManager;
//
//
//import connection.ConnectionFactory;
//import model.Users;
//
//public class userDAO {
//	
//	public Users save(Users user) {
//		//Realiza a conexão com o banco e o factory
//		EntityManager em =new ConnectionFactory().getConnection();
//		try {
//			//inicia a transição de dados para o bd
//			em.getTransaction().begin();
//			//verifica se o id é falso, se for ele vai inserir os dados no banco 
//			//já que o id é auto incremento
//			if(user.getId_user()== null) {
//				//inserção dos dados
//				em.persist(user);
//
//			}else {
//				//se tiver o id selecionado ele vai dá um update, alterando o que foi selecionado
//				em.merge(user);
//			}
//			//commita no momento exato que foi inserido
//			em.getTransaction().commit();
//		}catch(Exception e){
//			//se der merda vai voltar a transação
//			em.getTransaction().rollback();
//			System.out.println(e);
//
//		}finally{
//			em.close();
//		}
//		return user;
//	}
//	//buscar os dados pelo ID
//	public Users findById(Integer id) {
//		
//		EntityManager em =new ConnectionFactory().getConnection();
//		
//		Users user = null;
//		
//		try {
//			user =  em.find(Users.class, id);
//		} catch (Exception e) {
//			System.out.println(e);
//		}finally {
//			em.close();
//		}
//	
//		return user;
//	}
//	
//	//Método utilizando para achar todos os insert
//	public List<Users> findAll(Integer id){
//		EntityManager em =new ConnectionFactory().getConnection();
//		List<Users> users = null;
//		
//		try {
//			users = em.createQuery("from Users u").getResultList();
//		} catch (Exception e) {
//			System.out.println(e);
//		}finally {
//			em.close();
//		}
//		return users;
//	}
//	
//	public Users remove(Integer id) {
//		EntityManager em =new ConnectionFactory().getConnection();
//		Users user = null;
//		try {
//			em.getTransaction().begin();
//			user = em.find(Users.class, id);
//			em.remove(user);
//			em.getTransaction().commit();
//			
//		}catch(Exception e) {
//			System.out.println(e.getMessage());
//			em.getTransaction().rollback();
//		}finally {
//			em.close();
//		}
//		return user;
//	}
//	
//}
