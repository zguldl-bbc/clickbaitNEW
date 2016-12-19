package ch.bbcag.clickbaitEJB;

import java.util.List;

import javax.ejb.Local;

@Local
public interface SearchBeanLocal {
	
	@SuppressWarnings("rawtypes")
	public List<List> search(String searchTerm);

}
