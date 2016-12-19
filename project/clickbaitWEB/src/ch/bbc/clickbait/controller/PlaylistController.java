package ch.bbc.clickbait.controller;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ch.bbc.clickbait.model.Media;
import ch.bbc.clickbait.model.Playlist;
import ch.bbcag.clickbaitEJB.PlaylistBeanLocal;

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
	
	private List<Playlist> foreignUserPlaylists;
	private List<Media> playlistContent;
	private int playlistID;
	
	public String createNewPlaylist() {
		String userEmail = getUserController().getUser().getUserEmail();
		
		playlist.setDirSrc("C:\\User\\250\\" + playlist.getPlaylistName());
		playlist.setPlaylistOwner(userEmail);

		playlistBean.createNewPlaylist(playlist);
		playlist.setPlaylistName("");
		playlist.setBooleanPublic(false);
		return "";
	}
	
	public boolean isOwnerOfMedia(int mediaID) {
		String userEmail = getUserController().getUser().getUserEmail();
		boolean isOwner = playlistBean.isOwnerOfMedia(mediaID, userEmail);
		if(isOwner) {
			return true;
		}
		return false;
	}
	
	public boolean isOwnerOfPlaylist(int playlistID) {
		String userEmail = getUserController().getUser().getUserEmail();
		boolean isOwner = playlistBean.isOwnerOfPlaylist(playlistID, userEmail);
		if(isOwner) {
			return true;
		}
		return false;
	}
	
	public String showPlaylistFromUser(String userEmail) {
		setForeignUserPlaylists(playlistBean.getAllPlaylistFromUserID(userEmail));
		return "playlistForeign";
	}
	
	public String showPlaylistContentFrom(int playlistID, boolean flag) {
		setPlaylistContent(playlistBean.showPlaylistContentFrom(playlistID));
		if(flag) {
			setPlaylistID(playlistID);
			return "contentOwner";
		}
		return "contentForeign";
	}
	
	public String deletePlaylistWithID(int id) {
		playlistBean.deletePlaylistWithID(id);
		return "";
	}
	
	public List<Playlist> getAllPlaylists() {
		String userEmail = getUserController().getUser().getUserEmail();
		
		List<Playlist> list = playlistBean.getAllPlaylistFromUserID(userEmail);
		return list;
	}
	
	public boolean isVideo(int i) {
		if(i == 1) {
			return true;
		}
		return false;
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

	public List<Playlist> getForeignUserPlaylists() {
		return foreignUserPlaylists;
	}

	public void setForeignUserPlaylists(List<Playlist> foreignUserPlaylists) {
		this.foreignUserPlaylists = foreignUserPlaylists;
	}

	public List<Media> getPlaylistContent() {
		return playlistContent;
	}

	public void setPlaylistContent(List<Media> playlistContent) {
		this.playlistContent = playlistContent;
	}

	public int getPlaylistID() {
		return playlistID;
	}

	public void setPlaylistID(int playlistID) {
		this.playlistID = playlistID;
	}
}
