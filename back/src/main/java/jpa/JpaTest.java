package jpa;


import dao.EntityManagerHelper;
import entity.Organisateur;
import entity.User;
import entity.UtilisateurParticulier;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JpaTest {


	private EntityManager manager;

	public JpaTest(EntityManager manager) {
		this.manager = manager;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev");

		EntityManager manager = factory.createEntityManager();

		JpaTest test = new JpaTest(manager);

		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		try {
			// TODO create and persist entity
			test.createUsers();
		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();

		test.listUsers();
			
   	 	manager.close();
		EntityManagerHelper.closeEntityManagerFactory();
		System.out.println(".. done");
	}

	public void createUsers() {
		int numOfUsers = manager.createQuery("Select a From User a", User.class)
				.getResultList()
				.size();

		if (numOfUsers == 0) {
			UtilisateurParticulier user = new UtilisateurParticulier();
			user.setEmail("john@doe.com");
			user.setAdresse("1 rue de la paix");
			user.setCodePostal("75000");
			user.setVille("Paris");
			user.setNom("Doe");
			user.setPrenom("John");
			user.setTelephone("0123456789");

			manager.persist(user);
		}
	}

	public void listUsers() {
		java.util.List<User> resultList = manager.createQuery("Select a From User a", User.class).getResultList();
		System.out.println("num of users:" + resultList.size());
		for (User next : resultList) {
			System.out.println("next user: " + next);
		}
	}
}
