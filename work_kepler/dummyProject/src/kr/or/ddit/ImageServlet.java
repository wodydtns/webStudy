package kr.or.ddit;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ImageServlet extends HttpServlet{

	public ImageServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("image/jpg");
		URL targetURL = getClass().getResource("./test/Desert.jpg");
		File targetFile = new File(targetURL.getFile());
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(targetFile));
		ServletOutputStream sos = resp.getOutputStream();
		
		byte[] by = new byte[1024];
		while(bis.read(by)!=0){
			sos.write(by);
		}
		
		bis.close();
		sos.close();
	}

	
}
