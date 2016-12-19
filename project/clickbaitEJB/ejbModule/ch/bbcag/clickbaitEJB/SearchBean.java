package ch.bbcag.clickbaitEJB;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ch.bbc.clickbait.model.Media;
import ch.bbc.clickbait.model.Playlist;
import ch.bbc.clickbait.model.User;

@Stateless
public class SearchBean implements SearchBeanLocal{
	
	private final static Logger LOGGER = Logger.getLogger(UserBean.class.getName());

	@PersistenceContext
	EntityManager em;

	public SearchBean() {
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<List> search(String searchTerm) {
		LOGGER.info("Searching for term '" + searchTerm + "' ...");
		List<User> users = em.createNamedQuery("user.searchUserByTerm").setParameter("searchTerm", "%" + searchTerm + "%").getResultList();
		List<Playlist> playlists = em.createNamedQuery("playlists.searchPlaylistByTerm").setParameter("searchTerm", "%" + searchTerm + "%").getResultList();
		List<Media> media = em.createNamedQuery("media.searchMediaByTerm").setParameter("searchTerm", "%" + searchTerm + "%").getResultList();
		
		for (Media m : media) {
			Integer playlistID = (Integer) em.createNamedQuery("playlist_media.getPlaylistIDByMediaID").setParameter("mediaID", m.getMediaID()).getSingleResult();
			Playlist p = (Playlist) em.createNamedQuery("playlist.isPlaylistPublic").setParameter("playlistID", playlistID).getSingleResult();
			if(!p.getBooleanPublic()) {
				media.remove(m);
			}
			if(media.isEmpty()) {
				break;
			}
		}
		
		List<List> resultList = Arrays.asList(users, playlists, media);
		
		return resultList;
	}

}