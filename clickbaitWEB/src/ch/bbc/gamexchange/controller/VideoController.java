package ch.bbc.gamexchange.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named
@SessionScoped
public class VideoController implements Serializable {

	private static final long serialVersionUID = -129367998854052759L;

	@Inject
	private RegisterController registerController;

	public String checkSession() {
		if (!getRegisterController().isLoggedIn()) {
			return "index";
		} else {
			return "";
		}
	}

	public void logout() {
		getRegisterController().setLoggedIn(false);
		((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false)).invalidate();
	}

	public RegisterController getRegisterController() {
		return registerController;
	}

	public void setRegisterController(RegisterController registerController) {
		this.registerController = registerController;
	}

}
