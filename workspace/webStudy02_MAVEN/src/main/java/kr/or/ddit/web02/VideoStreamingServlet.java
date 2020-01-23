package kr.or.ddit.web02;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = "/video.do", 
	initParams = {@WebInitParam(name = "videoPath", 
	value = "d:/contents") }
)
public class VideoStreamingServlet extends HttpServlet{
	
	File folder;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		String imagePath = config.getInitParameter("videoPath");
		folder = new File(imagePath);
		System.out.println(folder.getAbsolutePath());
	}
	
	@Override
	protected void doGet(HttpServletRequest req, 
			HttpServletResponse resp) throws ServletException, IOException {
		String video = req.getParameter("video");
		if(video==null || video.trim().length()==0) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		File videoFile = new File(folder, video);
		if(!videoFile.exists()) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		
		FileInputStream fis = null;
		OutputStream os = null;
//		MIME : 전송 데이터의 타입을 결정.
		String mime = getServletContext().getMimeType(video);
		if(mime==null || !mime.startsWith("video/")) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		resp.setContentType(mime);
		try {
			fis = new FileInputStream(videoFile);
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













