package ch.bbcag.clickbaitEJB;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ch.bbc.clickbait.model.Media;
import ch.bbc.clickbait.model.PlaylistMedia;

@Stateless
public class MediaBean implements MediaBeanLocal{
	
	private final static Logger LOGGER = Logger.getLogger(UserBean.class.getName());

	@PersistenceContext
	EntityManager em;

	public MediaBean() {
	}

	@Override
	public String deleteMediaWithID(int mediaID) {
		try {
			em.createNamedQuery("media.deleteMediaByID").setParameter("id", mediaID).executeUpdate();
		} catch (Exception e) {
			LOGGER.warning("Media with id " + mediaID + " could not be delete");
			e.printStackTrace();
			return "";
		}
		LOGGER.info("Media with id " + mediaID + " has been deleted");
		return "";
	}

	@Override
	public String createNewMedia(Media media, int playlistID) {
		PlaylistMedia playlistMedia = new PlaylistMedia();
		playlistMedia.setPlaylistID(playlistID);
		
		try {
			em.persist(media);
			Media m = (Media) em.createNamedQuery("media.getMediaByMediaSrc").setParameter("mediaSrc", media.getMediaSrc()).getSingleResult();
			playlistMedia.setMediaID(m.getMediaID());
			em.persist(playlistMedia);
		} catch (Exception e) {
			LOGGER.warning("Media could not be uploaded: " + e);
		}
		LOGGER.info("Media " + media.getMediaTitle() + " has been uploaded");
		return "";
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Media> getSixRandomImages() {
		List<Media> media = em.createNamedQuery("media.getAllMedia").getResultList();
		List<Media> randomSix = new ArrayList<Media>();
		
		for (int i = 0; i < 10; i++) {
			int randomInt = 0 + (int)(Math.random() * ((media.size()-1 - 0) + 1));
			if(media.get(randomInt).getBooleanVideo() == 1) {
				i--;
			} else {
				randomSix.add(media.get(randomInt));
			}
		}
		return randomSix;
	}

	@Override
	public List<Media> getSixRandomVideos() {
		List<Media> media = em.createNamedQuery("media.getAllMedia").getResultList();
		List<Media> randomSix = new ArrayList<Media>();
		
		for (int i = 0; i < 10; i++) {
			int randomInt = 0 + (int)(Math.random() * ((media.size()-1 - 0) + 1));
			if(media.get(randomInt).getBooleanVideo() == 0) {
				i--;
			} else {
				randomSix.add(media.get(randomInt));
			}
		}
		return randomSix;
	}

}