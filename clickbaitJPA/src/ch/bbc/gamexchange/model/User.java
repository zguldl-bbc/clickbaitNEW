package ch.bbc.gamexchange.model;

import java.io.Serializable;

import javax.inject.Named;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;

/**
 * The persistent class for the customer database table.
 * 
 */
@Named
@Entity
@NamedQueries({ 
//		@NamedQuery(name = "user.findAll", query = "SELECT u FROM User u"),
//		@NamedQuery(name="user.findByEmailAndPassword", query ="SELECT u FROM User u WHERE u.userEmail = :email AND u.userPassword = :password")
	})

public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int userID;

	private String userName;

	private String userEmail;

	private String userPassword;

	private String birthDate;

	private String infos;

	public User() {
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getInfos() {
		return infos;
	}

	public void setInfos(String infos) {
		this.infos = infos;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

}