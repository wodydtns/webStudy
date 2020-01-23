package kr.or.ddit.web05_model2;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

@WebServlet("/07/modulization.do")
public class PageModulizationServlet extends HttpServlet{
	Properties commandMap;
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		commandMap = new Properties();
		try(
			InputStream in = getClass().getResourceAsStream("serviceKind.xml");
		){
			commandMap.loadFromXML(in);
		}catch (IOException e) {
//			throw new ServletException(e);
			throw new RuntimeException(e);
		}
	
		
	}
	
	@Override
	protected void service(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");	
		String command = request.getParameter("command");
		String path = null;
		if(StringUtils.isNotBlank(command)){
			if(!commandMap.containsKey(command)){
				response.sendError(HttpServletResponse.SC_BAD_REQUEST);
				return;
			}
			path = commandMap.getProperty(command);
		}
		
		if(path!=null)
			request.setAttribute("goPage", path.trim());
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/background.jsp");
		rd.forward(request, response);
	}
	
}















