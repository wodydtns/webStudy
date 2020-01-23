package kr.or.ddit.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.utils.CookieUtils;
import kr.or.ddit.utils.CookieUtils.TextType;
import sun.font.CreatedFontTracker;


public class ClasspathImageServlet extends HttpServlet{
	
	File folder;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		String imagePath = config.getInitParameter("imagePath");
		URL folderURL = ClasspathImageServlet.class.getResource(imagePath);
		folder = new File(folderURL.getFile());
		System.out.println(folder.getAbsolutePath());
	}
	
	@Override
	protected void doGet(HttpServletRequest req, 
			HttpServletResponse resp) throws ServletException, IOException {
		String image = req.getParameter("image");
		if(image==null || image.trim().length()==0) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		File imageFile = new File(folder, image);
		if(!imageFile.exists()) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		
		FileInputStream fis = null;
		OutputStream os = null;
//		MIME : 전송 데이터의 타입을 결정.
		String mime = getServletContext().getMimeType(image);
		if(mime==null || !mime.startsWith("image/")) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		resp.setContentType(mime);
		
		Cookie imageCookie = CookieUtils.createCookie("imageCookie", image,req.getContextPath(),TextType.PATH,60*60*24*3);
//		Cookie imageCookie = new Cookie("imageCookie", image);
//		imageCookie.setPath(req.getContextPath());
//		imageCookie.setMaxAge(60*60*24*3);
		resp.addCookie(imageCookie);
		try {
			fis = new FileInputStream(imageFile);
			os = resp.getOutputStream();
			byte[] buffer = new byte[1024];
			int length = -1;
			while((length = fis.read(buffer))!=-1) { // -1 : EoF 문자
				os.write(buffer, 0, length);
			}
		}finally {
			if(fis!=null)
				fis.close();
			if(os!=null)
				os.close();
		}
	}
}













