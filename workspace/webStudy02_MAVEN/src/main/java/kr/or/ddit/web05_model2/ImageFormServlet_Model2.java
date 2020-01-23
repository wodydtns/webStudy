package kr.or.ddit.web05_model2;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.utils.CookieUtils;
import kr.or.ddit.utils.TemplateUtils;

@WebServlet(urlPatterns = "/model2/imageForm.do", 
		initParams = {@WebInitParam(name = "imagePath", 
								value = "/kr/or/ddit/test")}
		)
public class ImageFormServlet_Model2 extends HttpServlet{
	File folder;
	ServletContext servletContext;
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		String fsp = getClass().getResource(config.getInitParameter("imagePath")).getFile();
		folder = new File(fsp);
		servletContext = getServletContext();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		Cookie[] cookies = req.getCookies();
//		String imageName = null;
//		if(cookies!=null) {
//			for(Cookie tmp : cookies) {
//				if("imageCookie".equals(tmp.getName())) {
//					imageName = tmp.getValue();
//					break;
//				}
//			}
		
//		}
		String imageName = new CookieUtils(req).getCookieValue("imageCookie");
//		2. 구멍 치환
		String[] imageNames = folder.list((dir, name)->{
	//			name.endsWith(".jpg") || name.endWith(".gif")
				String mime = servletContext.getMimeType(name);
				return mime!=null && mime.startsWith("image/");
			});
		
		req.setAttribute("imageNames", imageNames);
		req.setAttribute("imageName", imageName);
		
		req.getRequestDispatcher("/WEB-INF/views/imageForm.jsp")
		.include(req, resp);
	}
}
















