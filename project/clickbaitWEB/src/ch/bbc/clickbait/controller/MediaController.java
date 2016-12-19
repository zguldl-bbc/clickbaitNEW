package ch.bbc.clickbait.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ch.bbc.clickbait.model.Media;
import ch.bbcag.clickbaitEJB.MediaBeanLocal;

@Named
@SessionScoped
public class MediaController implements Serializable{
	
	private static final long serialVersionUID = 2360047503716009048L;
	
	@EJB
	private MediaBeanLocal mediaBean;
	
	@Inject
	private Media media;
	
	@Inject
	private UploadController uploadController;
	
	@Inject
	private PlaylistController playlistController; 
	
	
	public String deleteMediaWithID(int mediaID) {
		mediaBean.deleteMediaWithID(mediaID);
		return "playlistOwner";
	}
	
	public String createNewMedia() {
		String src = uploadController.handleFileUpload();
		int playlistID = getPlaylistController().getPlaylistID();
		
		getMedia().setMediaSrc(src);
		getMedia().setBooleanVideo(uploadController.getMediaFormat());
		
		mediaBean.createNewMedia(getMedia(), playlistID);
		return "playlistOwner";
	}
	
	public List<Media> getSixRandomImages() {
		List<Media> sixRandomImages = mediaBean.getSixRandomImages();
		return sixRandomImages;
	}
	
	public List<Media> getSixRandomVideos() {
		List<Media> sixRandomVideos = mediaBean.getSixRandomVideos();
		return sixRandomVideos;
	}
	
	public String parseTime(String src) {
		String time;
		String timeInMillis = src.substring(src.lastIndexOf("/") + 1);
		timeInMillis = timeInMillis.split("\\.")[0];
		
		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm");    
		Date resultdate = new Date(Long.parseLong(timeInMillis));
		time = sdf.format(resultdate);
		
		return time;
	}

	public Media getMedia() {
		return media;
	}

	public void setMedia(Media media) {
		this.media = media;
	}

	public PlaylistController getPlaylistController() {
		return playlistController;
	}

	public void setPlaylistController(PlaylistController playlistController) {
		this.playlistController = playlistController;
	}

}
