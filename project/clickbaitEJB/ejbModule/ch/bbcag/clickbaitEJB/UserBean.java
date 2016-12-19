package ch.bbcag.clickbaitEJB;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ch.bbc.clickbait.model.User;

@Stateless
public class UserBean implements UserBeanLocal {

	private final static Logger LOGGER = Logger.getLogger(UserBean.class.getName());

	@PersistenceContext
	EntityManager em;

	public UserBean() {
	}

	@Override
	public String save(User user) {
		try {
			em.persist(user);
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

	@SuppressWarnings("unchecked")
	@Override
	public String getUserNameByEmail(String userEmail) {
		List<User> user = null;
		try {
			user = em.createNamedQuery("user.getUserNameByEmail").setParameter("userEmail", userEmail).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user.get(0).getUserName();
	}

	@Override
	public User getAllUserInfos(String userEmail) {
		User user = (User) em.createNamedQuery("user.getUserNameByEmail").setParameter("userEmail", userEmail).getSingleResult();
		return user;
	}

	@Override
	public void deleteAccount(String userEmail) {
		em.createNamedQuery("user.deleteAccountWithEmail").setParameter("userEmail", userEmail).executeUpdate();
	}

	@Override
	public void editUser(User userProfile) {
		System.out.println("hoi");
		em.createNamedQuery("user.update").
		setParameter("userName", userProfile.getUserName()).
		setParameter("userPassword", userProfile.getUserPassword()).
		setParameter("infos", userProfile.getInfos()).
		setParameter("userEmail", userProfile.getUserEmail()).
		executeUpdate();
	}

}