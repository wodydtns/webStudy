package kr.or.ddit.web;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.utils.TemplateUtils;

@WebServlet(urlPatterns = "/02/imageForm.do",
			initParams = {@WebInitParam(name="imagePath", value="/kr/or/ddit/test")}
			)
public class ImageFormServlet extends HttpServlet{
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
//		1. 템플릿읽기
		StringBuffer html = TemplateUtils.readTemplate("/kr/or/ddit/web02/imageForm.tmpl");
//		2. 구멍치환
		String[] imageNames = folder.list((dir, name)->{
//			name.endsWith(".jpg") || name.endWith(".gif")
			String mime = servletContext.getMimeType(name);
			return mime!=null && mime.startsWith("image/");
		});
		
		String ptrn = "<option value='%1$s'>%1$s</option>\n";
		StringBuffer option = new StringBuffer();
		for(String imageName : imageNames) {
			option.append(String.format(ptrn, imageName));
		}
		
		String contents = html.toString().replace("%option", option.toString());
//		3. 응답 전송
		resp.setContentType("text/html;charset=UTF-8");
		try(
			PrintWriter out = resp.getWriter();
		){
			out.println(contents);
		}
	}
}
