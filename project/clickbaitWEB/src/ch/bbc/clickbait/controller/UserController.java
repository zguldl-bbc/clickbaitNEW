package ch.bbc.clickbait.controller;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import ch.bbc.clickbait.model.User;
import ch.bbcag.clickbaitEJB.UserBeanLocal;

@Named
@SessionScoped
public class UserController implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private UserBeanLocal userBean;
	
	@Inject
	private User user;
	
	private User userProfile = new User();
	private User userEdit = new User();
	private boolean loggedIn = false;

	public String save() {
		userBean.save(user);
		user.setBirthDate(null);
		user.setInfos("");
		user.setUserName("");
		user.setUserPassword("");
		return "";
	}

	public String login() {
		boolean flag = userBean.login(user);
		user.setUserPassword("");
		if(!flag) {
			setLoggedIn(false);
			return null;
		} else {
			setLoggedIn(true);
			return "playlists";
		}
	}
	
	public String getUserNameByEmail(String userEmail) {
		String userName = userBean.getUserNameByEmail(userEmail);
		return userName;
	}

	public String logout() {
		setLoggedIn(false);
		((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false)).invalidate();
		return "index";
	}
	
	public void getAllUserInfos() {
		User user = userBean.getAllUserInfos(getUser().getUserEmail());
		getUserProfile().setUserName(user.getUserName());
		getUserProfile().setUserPassword(user.getUserPassword());
		getUserProfile().setBirthDate(user.getBirthDate());
		getUserProfile().setInfos(user.getInfos());
	}
	
	public String editUser() {
		if(!userEdit.getUserName().equals("")) {
			getUserProfile().setUserName(userEdit.getUserName());
		}
		if(!userEdit.getUserPassword().equals("")) {
			getUserProfile().setUserPassword(userEdit.getUserPassword());
		}
		if(!userEdit.getInfos().equals("")) {
			getUserProfile().setInfos(userEdit.getInfos());
		}
		
		getUserProfile().setUserEmail(getUser().getUserEmail());
		
		userBean.editUser(getUserProfile());
		return "profile";
	}
	
	public String deleteAccount() {
		logout();
		userBean.deleteAccount(getUser().getUserEmail());
		return "index";
	}
	
	public List<User> getAllUser() {
		List<User> customer = userBean.getAllUser();
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

	public User getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(User userProfile) {
		this.userProfile = userProfile;
	}

	public User getUserEdit() {
		return userEdit;
	}

	public void setUserEdit(User userEdit) {
		this.userEdit = userEdit;
	}
}
