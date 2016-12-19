package ch.bbcag.clickbaitEJB;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ch.bbc.clickbait.model.Media;
import ch.bbc.clickbait.model.Playlist;

@Stateless
public class PlaylistBean implements PlaylistBeanLocal{
	
	private final static Logger LOGGER = Logger.getLogger(UserBean.class.getName());

	@PersistenceContext
	EntityManager em;

	public PlaylistBean() {
	}

	@Override
	public String createNewPlaylist(Playlist playlist) {
		try {
			em.persist(playlist);
		} catch (Exception e) {
			LOGGER.warning("Playlist could not be created: " + e);
		}
		LOGGER.info("User " + playlist.getPlaylistName() + " has been registered.");
		return "";
	}

	@Override
	public String deletePlaylistWithID(int id) {
		try {
			em.createNamedQuery("playlist.deletePlaylistWithID").setParameter("id", id).executeUpdate();
		} catch (Exception e) {
			LOGGER.warning("Playlist with id" + id + " could not be delete");
		}
		LOGGER.info("Playlist with id " + id + " has been deleted");
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Playlist> getAllPlaylistFromUserID(String userEmail) {
		List<Playlist> playlists = em.createNamedQuery("playlist.findByOwner").setParameter("userEmail", userEmail).getResultList();
		return playlists;
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public List<Media> showPlaylistContentFrom(int playlistID) {
		List<Integer> mediaIDs = em.createNamedQuery("playlist_media.getMediaIDByPlaylistID").setParameter("playlistID", playlistID).getResultList();
		List<Media> media = new ArrayList<Media>();
		for (Integer i : mediaIDs) {
			media.addAll(em.createNamedQuery("media.getMediaByID").setParameter("mediaID", i).getResultList());
		}
		return media;
	}

	@Override
	public boolean isOwnerOfMedia(int mediaID, String userEmail) {
		Integer playlistID = (Integer) em.createNamedQuery("playlist_media.getPlaylistIDByMediaID").setParameter("mediaID", mediaID).getSingleResult();
		Playlist p = (Playlist) em.createNamedQuery("playlist.getPlaylistWithID").setParameter("playlistID", playlistID).getSingleResult();
		if(p.getPlaylistOwner().equals(userEmail)) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean isOwnerOfPlaylist(int playlistID, String userEmail) {
		Playlist p = (Playlist) em.createNamedQuery("playlist.isOwnerOfPlaylist").setParameter("userEmail", userEmail).setParameter("playlistID", playlistID).getSingleResult();
		if(p.getPlaylistOwner().equals(userEmail)) {
			return true;
		}
		return false;
	}

}
