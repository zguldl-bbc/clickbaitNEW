package ch.bbc.gamexchange.controller;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ch.bbc.gamexchange.model.Playlist;
import ch.bbcag.GameXChangeEJB.PlaylistBeanLocal;

@Named
@SessionScoped
public class PlaylistController implements Serializable{

	private static final long serialVersionUID = 6306201396190405421L;
	
	@EJB
	private PlaylistBeanLocal playlistBean;
	
	@Inject
	private Playlist playlist;
	
	@Inject
	private UserController userController; 
	
	public String createNewPlaylist() {
		String userEmail = getUserController().getUser().getUserEmail();
		
		playlist.setDirSrc("C:\\User\\250\\" + playlist.getPlaylistName());
		playlist.setPlaylistOwner(userEmail);

		playlistBean.createNewPlaylist(playlist);
		playlist.setPlaylistName("");
		playlist.setBooleanPublic(false);
		return "";
	}
	
	public String showPlaylist(String id) {
		return "video";
	}
	
	public String deletePlaylistWithID(int id) {
		playlistBean.deletePlaylistWithID(id);
		return "";
	}
	
	public List<Playlist> getAllPlaylists() {
		String userEmail = getUserController().getUser().getUserEmail();
		
		List<Playlist> list = playlistBean.getAllPlaylists(userEmail);
		return list;
	}

	public Playlist getPlaylist() {
		return playlist;
	}

	public void setPlaylist(Playlist playlist) {
		this.playlist = playlist;
	}

	public UserController getUserController() {
		return userController;
	}

	public void setUserController(UserController userController) {
		this.userController = userController;
	}

}
