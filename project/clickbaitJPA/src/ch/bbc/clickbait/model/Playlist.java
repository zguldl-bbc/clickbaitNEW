package ch.bbc.clickbait.model;

import java.io.Serializable;

import javax.inject.Named;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the customer database table.
 * 
 */
@Named
@Entity
@NamedQueries({ 
	@NamedQuery(name = "playlist.findAll", query = "SELECT p FROM Playlist p"),
	@NamedQuery(name="playlist.isOwnerOfPlaylist", query ="SELECT p FROM Playlist p WHERE p.playlistOwner = :userEmail AND p.playlistID = :playlistID"),
	@NamedQuery(name="playlist.findByOwner", query ="SELECT p FROM Playlist p WHERE p.playlistOwner = :userEmail"),
	@NamedQuery(name="playlist.deletePlaylistWithID", query ="DELETE FROM Playlist p WHERE p.playlistID = :id"),
	@NamedQuery(name="playlists.searchPlaylistByTerm", query ="SELECT p FROM Playlist p WHERE p.playlistName LIKE :searchTerm AND p.booleanPublic = 1"),
	@NamedQuery(name="playlist.getPlaylistWithID", query ="SELECT p FROM Playlist p WHERE p.playlistID = :playlistID"),
	@NamedQuery(name="playlist.isPlaylistPublic", query ="SELECT p FROM Playlist p WHERE p.playlistID = :playlistID")
})
@Table(name="playlist", schema="clickbaitdb")
public class Playlist implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int playlistID;

	private String playlistName;

	private String dirSrc;

	private boolean booleanPublic;

	private String genPrivateCode;
	
	private String playlistOwner;

	public Playlist() {
	}

	public String getPlaylistName() {
		return playlistName;
	}
	
	public int getPlaylistID() {
		return playlistID;
	}

	public void setPlaylistName(String playlistName) {
		this.playlistName = playlistName;
	}

	public String getDirSrc() {
		return dirSrc;
	}

	public void setDirSrc(String dirSrc) {
		this.dirSrc = dirSrc;
	}

	public boolean getBooleanPublic() {
		return booleanPublic;
	}

	public void setBooleanPublic(boolean booleanPublic) {
		this.booleanPublic = booleanPublic;
	}

	public String getGenPrivateCode() {
		return genPrivateCode;
	}

	public void setGenPrivateCode(String genPrivateCode) {
		this.genPrivateCode = genPrivateCode;
	}

	public String getPlaylistOwner() {
		return playlistOwner;
	}

	public void setPlaylistOwner(String playlistOwner) {
		this.playlistOwner = playlistOwner;
	}

	
}