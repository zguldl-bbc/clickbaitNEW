package ch.bbc.clickbait.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Named;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the user database table.
 * 
 */
@Named
@Entity
@NamedQueries({ 
	@NamedQuery(name = "user.findAll", query = "SELECT u FROM User u"),
	@NamedQuery(name="user.searchUserByTerm", query ="SELECT u FROM User u WHERE u.userName LIKE :searchTerm"),
	@NamedQuery(name="user.getUserNameByEmail", query ="SELECT u FROM User u WHERE u.userEmail = :userEmail"),
	@NamedQuery(name="user.deleteAccountWithEmail", query ="DELETE FROM User u WHERE u.userEmail = :userEmail"), 
	@NamedQuery(name="user.update", query = "UPDATE User u SET u.userName = :userName, u.userPassword = :userPassword, u.infos = :infos WHERE u.userEmail = :userEmail")
})
@Table(name="user", schema="clickbaitdb")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final SimpleDateFormat SDF = new SimpleDateFormat("dd.MM.yyyy");

	@Id
	private int userID;

	@Temporal(TemporalType.DATE)
	private Date birthDate;

	private String infos;

	private String userEmail;

	private String userName;

	private String userPassword;

	public User() {
	}
    
	public String getBirthDateAsString() {
		return SDF.format(getBirthDate());
	}
	
	public int getUserID() {
		return this.userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public Date getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getInfos() {
		return this.infos;
	}

	public void setInfos(String infos) {
		this.infos = infos;
	}

	public String getUserEmail() {
		return this.userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

}