package ch.bbcag.clickbaitEJB;

import java.util.List;

import javax.ejb.Local;

import ch.bbc.clickbait.model.User;

@Local
public interface UserBeanLocal {
	
	public String save(User c);
	public boolean login(User c);
	public List<User> getAllUser();
	public String getUserNameByEmail(String userEmail);
	public User getAllUserInfos(String userEmail);
	public void deleteAccount(String userEmail);
	public void editUser(User userProfile);

}
