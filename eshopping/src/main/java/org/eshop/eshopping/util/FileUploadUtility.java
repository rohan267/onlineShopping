package org.eshop.eshopping.util;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtility {

	private static final String ABSOLUTE_PATH="/Users/rohanparekh/Documents/workspace/onlineShopping-Scratch/onlineShopping/eshopping/src/main/webapp/assets/images/";
	
	private static String REAL_PATH="";

	private static final Logger LOG = LoggerFactory.getLogger(FileUploadUtility.class);
	public static void uploadFile(HttpServletRequest request, MultipartFile file, String code) {
		//get real path
		
		REAL_PATH=request.getSession().getServletContext().getRealPath("/assets/images/");
		LOG.info("Real path : " + request.getSession().getServletContext().getRealPath("/assets/images/"));
		
		if(!new File(ABSOLUTE_PATH).exists()) {
			new File(ABSOLUTE_PATH).mkdirs();
		}
		
		if(!new File(REAL_PATH).exists()) {
			new File(REAL_PATH).mkdirs();
		}
		
		
		try {
			// server upload
			file.transferTo(new File(REAL_PATH+code+".jpg"));
			
			// project directory upload
			file.transferTo(new File(ABSOLUTE_PATH+code+".jpg"));
		}catch(IOException _iox) {
			LOG.error(_iox.getMessage());
		}
			
	}
}
