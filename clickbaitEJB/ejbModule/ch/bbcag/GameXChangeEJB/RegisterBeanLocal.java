package ch.bbcag.GameXChangeEJB;

import java.util.List;

import javax.ejb.Local;

import ch.bbc.gamexchange.model.User;

@Local
public interface RegisterBeanLocal {
	
	public String save(User c);
	public boolean login(User c);
	public List<User> getAllUser();

}
