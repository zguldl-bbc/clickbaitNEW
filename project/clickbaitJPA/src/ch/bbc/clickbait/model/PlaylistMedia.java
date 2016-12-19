package ch.bbc.clickbait.model;

import java.io.Serializable;

import javax.inject.Named;
import javax.persistence.*;


/**
 * The persistent class for the playlist_media database table.
 * 
 */
@Named
@Entity
@NamedQueries({ 
	@NamedQuery(name="playlist_media.getMediaIDByPlaylistID", query ="SELECT pm.mediaID FROM PlaylistMedia pm WHERE pm.playlistID = :playlistID"),
	@NamedQuery(name="playlist_media.getPlaylistIDByMediaID", query ="SELECT pm.playlistID FROM PlaylistMedia pm WHERE pm.mediaID = :mediaID")
})
@Table(name="playlist_media", schema="clickbaitdb")
public class PlaylistMedia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int mediaID;

	private int playlistID;

	public PlaylistMedia() {
	}

	public int getMediaID() {
		return this.mediaID;
	}

	public void setMediaID(int mediaID) {
		this.mediaID = mediaID;
	}

	public int getPlaylistID() {
		return this.playlistID;
	}

	public void setPlaylistID(int playlistID) {
		this.playlistID = playlistID;
	}

}