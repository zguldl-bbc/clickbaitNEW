package ch.bbcag.clickbaitEJB;

import java.util.List;

import javax.ejb.Local;

import ch.bbc.clickbait.model.Media;
import ch.bbc.clickbait.model.Playlist;

@Local
public interface PlaylistBeanLocal {
	
	public String createNewPlaylist(Playlist playlist);
	public String deletePlaylistWithID(int id);
	public List<Playlist> getAllPlaylistFromUserID(String userEmail);
	public List<Media> showPlaylistContentFrom(int playlistID);
	public boolean isOwnerOfMedia(int mediaID, String userEmail);
	public boolean isOwnerOfPlaylist(int playlistID, String userEmail);

}
