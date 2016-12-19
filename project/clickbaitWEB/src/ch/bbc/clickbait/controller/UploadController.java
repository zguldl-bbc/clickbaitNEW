package ch.bbc.clickbait.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;

import ch.bbcag.clickbaitEJB.UploadBeanLocal;

@Named
@SessionScoped
public class UploadController implements Serializable {

	private static final long serialVersionUID = -2377507709635750679L;

	@EJB
	private UploadBeanLocal uploadBean;

	private FileUploadEvent upload;
	
	private static final String FILE_LINK = "ftp://admin:123456@192.168.3.30/clickbait/";
	private int mediaFormat;

	public void uploadBuffer(FileUploadEvent event) {
		setUpload(event);
	}

	public String handleFileUpload() {
		String src = null;
		try {
			String str = getUpload().getFile().getFileName();
			
			String format = str.substring(str.lastIndexOf('.'), str.length());
			setMediaFormat(format);

			InputStream inputStream = getUpload().getFile().getInputstream();
			long genID = uploadBean.uploadFile(inputStream, format);
			src = FILE_LINK + genID + format;
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("uploading...");
		return src;
	}

	public FileUploadEvent getUpload() {
		return upload;
	}

	public void setUpload(FileUploadEvent event) {
		upload = event;
	}

	public int getMediaFormat() {
		return mediaFormat;
	}

	public void setMediaFormat(String format) {
		if(format.equals(".jpg") || format.equals(".jpeg") || format.equals(".png") || format.equals(".gif")) {
			this.mediaFormat = 0;
		} else {
			this.mediaFormat = 1;
		} 
	}
}
