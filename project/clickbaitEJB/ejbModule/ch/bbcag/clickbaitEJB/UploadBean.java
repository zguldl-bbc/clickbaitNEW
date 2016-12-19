package ch.bbcag.clickbaitEJB;

import java.io.IOException;
import java.io.InputStream;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;
import jcifs.smb.SmbFileOutputStream;

@Stateless
public class UploadBean implements UploadBeanLocal{
	
	private static final String USER_NAME = "admin";
	private static final String PASSWORD = "123456";
	private static final String NETWORK_FOLDER = "smb://192.168.3.30/clickbait/";

	@PersistenceContext
	EntityManager em;

	public UploadBean() {
	}

	@Override
	public long uploadFile(InputStream file, String format) throws IOException {
		SmbFileOutputStream sfos = null;
		long genID = System.currentTimeMillis();
		String path = null;

		try {
			String user = USER_NAME + ":" + PASSWORD;

			NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(user);
			path = NETWORK_FOLDER + genID + format;

			SmbFile sFile = new SmbFile(path, auth);
			sfos = new SmbFileOutputStream(sFile);
			//
			SmbFile sFile2 = new SmbFile(path, auth);
			SmbFileInputStream sfos2 = new SmbFileInputStream(sFile2);
			//
			
			byte[] buffer = new byte[5000000];
			int len;
			while ((len = file.read(buffer)) != -1) {
			    sfos.write(buffer, 0, len);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sfos != null) {
				sfos.close();
			}
		}
		return genID;
	}

}