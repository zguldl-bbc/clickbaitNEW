package ch.bbc.gamexchange.controller;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import ch.bbc.gamexchange.model.User;
import ch.bbcag.GameXChangeEJB.RegisterBeanLocal;

@Named
@SessionScoped
public class RegisterController implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private RegisterBeanLocal registerBean;
	
	@Inject
	private User user;
	
	private boolean loggedIn = false;

	public String save() {
		registerBean.save(user);
		return "";
	}

	public String login() {
		boolean flag = registerBean.login(user);
		if(!flag) {
			setLoggedIn(false);
			return null;
		} else {
			setLoggedIn(true);
			return "video";
		}
	}
	
	public List<User> getAllUser() {
		List<User> customer = registerBean.getAllUser();
		return customer;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
}
