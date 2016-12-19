package ch.bbcag.clickbaitEJB;

import javax.ejb.Local;

import java.io.IOException;
import java.io.InputStream;

@Local
public interface UploadBeanLocal {
	
	public long uploadFile(InputStream file, String format) throws IOException;

}
