package ch.bbcag.clickbaitEJB;

import java.util.List;

import javax.ejb.Local;

import ch.bbc.clickbait.model.Media;

@Local
public interface MediaBeanLocal {

	public String deleteMediaWithID(int mediaID);
	public String createNewMedia(Media media, int playlistID);
	public List<Media> getSixRandomImages();
	public List<Media> getSixRandomVideos();

}
