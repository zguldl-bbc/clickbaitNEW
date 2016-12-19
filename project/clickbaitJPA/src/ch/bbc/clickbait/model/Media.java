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
	@NamedQuery(name="media.searchMediaByTerm", query ="SELECT m FROM Media m WHERE m.mediaTitle LIKE :searchTerm"),
	@NamedQuery(name="media.getMediaByID", query ="SELECT m FROM Media m WHERE m.mediaID = :mediaID"),
	@NamedQuery(name="media.deleteMediaByID", query ="DELETE FROM Media m WHERE m.mediaID = :id"),
	@NamedQuery(name="media.getMediaByMediaSrc", query ="SELECT m FROM Media m WHERE m.mediaSrc = :mediaSrc"),
	@NamedQuery(name="media.getAllMedia", query ="SELECT m FROM Media m")
})
@Table(name="media", schema="clickbaitdb")
public class Media implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int mediaID;

	private String mediaTitle;

	private String mediaDescription;

	private String mediaSrc;

	private int booleanVideo;

	public Media() {
	}

	public String getMediaTitle() {
		return mediaTitle;
	}

	public void setMediaTitle(String mediaTitle) {
		this.mediaTitle = mediaTitle;
	}

	public String getMediaDescription() {
		return mediaDescription;
	}

	public void setMediaDescription(String mediaDescription) {
		this.mediaDescription = mediaDescription;
	}

	public String getMediaSrc() {
		return mediaSrc;
	}

	public void setMediaSrc(String mediaSrc) {
		this.mediaSrc = mediaSrc;
	}

	public int getBooleanVideo() {
		return booleanVideo;
	}

	public void setBooleanVideo(int booleanVideo) {
		this.booleanVideo = booleanVideo;
	}

	public int getMediaID() {
		return mediaID;
	}

	public void setMediaID(int mediaID) {
		this.mediaID = mediaID;
	}

}