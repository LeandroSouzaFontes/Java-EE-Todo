//package connection;
//
//import javax.annotation.PostConstruct;
//import javax.annotation.PreDestroy;
//import javax.ejb.Remove;
//import javax.ejb.Stateful;
//import javax.enterprise.inject.Default;
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//import javax.persistence.PersistenceContext;
//
//import qualifier.MinhaFactory;
//
//@Stateful
//@Default
//
//public class ConnectionFactory {
//
//	@PersistenceContext(unitName = "meuPu")
//	private EntityManagerFactory emf;
//	private EntityManager em;
//
//	public EntityManager getConnection() {
//		return emf.createEntityManager();
//	}
//
//	@PostConstruct
//	public void init() {
//		em = emf.createEntityManager();
//	}
//
//	@Remove
//	@PreDestroy
//	public void destroy() {
//		emf.close();
//	}
//
//	public EntityManagerFactory getEmf() {
//		return emf;
//	}
//
//	public void setEmf(EntityManagerFactory emf) {
//		this.emf = emf;
//	}
//
//	public EntityManager getEm() {
//		return em;
//	}
//
//	public void setEm(EntityManager em) {
//		this.em = em;
//	}
//
//}
