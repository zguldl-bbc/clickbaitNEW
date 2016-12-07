package ch.bbc.gamexchange.controller;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import ch.bbc.gamexchange.model.User;
import ch.bbcag.GameXChangeEJB.UserBeanLocal;

@Named
@SessionScoped
public class UserController implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private UserBeanLocal registerBean;
	
	@Inject
	private User user;
	
	private boolean loggedIn = false;

	public String save() {
		registerBean.save(user);
		user.setBirthDate(null);
		user.setInfos("");
		user.setUserName("");
		user.setUserPassword("");
		return "";
	}
	
	public String hello() {
		return "playlist";
	}

	public String login() {
		boolean flag = registerBean.login(user);
		user.setUserPassword("");
		if(!flag) {
			setLoggedIn(false);
			return null;
		} else {
			setLoggedIn(true);
			return "playlists";
		}
	}

	public String logout() {
		setLoggedIn(false);
		((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false)).invalidate();
		return "index";
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
