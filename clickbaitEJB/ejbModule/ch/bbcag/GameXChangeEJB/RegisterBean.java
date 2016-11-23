package ch.bbcag.GameXChangeEJB;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ch.bbc.gamexchange.model.User;

@Stateless
public class RegisterBean implements RegisterBeanLocal {

	private final static Logger LOGGER = Logger.getLogger(RegisterBean.class.getName());

	@PersistenceContext
	EntityManager em;

	/**
	 * Default constructor.
	 */
	public RegisterBean() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String save(User user) {
		try {
			em.createNativeQuery("INSERT INTO clickbaitdb.user (userName, userEmail, userPassword, birthDate, infos) VALUES ('" + user.getUserName() + "','" + user.getUserEmail() + "','" + user.getUserPassword()  + "','" + user.getBirthDate() + "','" + user.getInfos() + "');").executeUpdate();
//			em.persist(user);
		} catch (Exception e) {
			LOGGER.warning("User could not be registered: " + e);
		}
		LOGGER.info("User " + user.getUserEmail() + " has been registered.");
		return "";
	}

	@Override
	public boolean login(User user) {
		try {
			if (em.createNativeQuery("SELECT * FROM clickbaitdb.user where userEmail='"  + user.getUserEmail() + "' AND userPassword='" + user.getUserPassword() + "';").getResultList().size() > 0) {
				LOGGER.info("User " + user.getUserEmail() + " successfully logged in.");
				return true;
			} else {
				LOGGER.info("Email or password incorrect.");
				return false;
			}
		} catch (Exception e) {
			LOGGER.warning("User could not be logged in: " + e);
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUser() {
		List<User> user = em.createNamedQuery("Customer.findAll").getResultList();
		for (int i = 0; i < user.size(); i++) {
			User u = user.get(i);
			System.out.println("email: " + u.getUserEmail() + " password: " + u.getUserPassword());
		}
		return user;
	}

}