package ch.bbc.clickbait.controller;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import ch.bbc.clickbait.model.Media;
import ch.bbc.clickbait.model.Playlist;
import ch.bbc.clickbait.model.User;
import ch.bbcag.clickbaitEJB.SearchBeanLocal;

@Named
@SessionScoped
public class SearchController implements Serializable{

	private static final long serialVersionUID = -6784142338205921692L;
	
	@EJB
	private SearchBeanLocal searchBean;
	
	private String searchTerm;
	
	private List<User> users;
	private List<Playlist> playlists;
	private List<Media> media;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String search() {
		List<List> resultList = searchBean.search(getSearchTerm());
		
		setUsers(resultList.get(0));
		setPlaylists(resultList.get(1));
		setMedia(resultList.get(2));
		
		return "searchResult";
	}

	public String getSearchTerm() {
		return searchTerm;
	}

	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}
	
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Playlist> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(List<Playlist> playlists) {
		this.playlists = playlists;
	}

	public List<Media> getMedia() {
		return media;
	}

	public void setMedia(List<Media> media) {
		this.media = media;
	}
}
