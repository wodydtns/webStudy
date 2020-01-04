package kr.or.ddit.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String img = req.getParameter("select_img");
		File imageFile = new File(folder, img);
		FileInputStream fis = null;
		OutputStream os = null;
		//MIME : 전송 데이터의 타입을 결정
		resp.setContentType("image/jpeg");
		try {
			fis = new FileInputStream(imageFile);
			os = resp.getOutputStream();
			byte[] buffer = new byte[1024];
			int length = -1;
			while((length = fis.read(buffer)) != -1){	// -1 :EoF 문자
				os.write(buffer, 0, length);
			}
		}finally {
			if(fis != null)
				fis.close();
			if(os != null)
				os.close();
		}
	}
	
}
