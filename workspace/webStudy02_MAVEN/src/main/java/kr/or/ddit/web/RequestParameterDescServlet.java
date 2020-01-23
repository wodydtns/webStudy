package kr.or.ddit.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.utils.TemplateUtils;

@WebServlet("/param.do")
public class RequestParameterDescServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("2. 구체적인 요청 처리(GET 요청)");
		
		StringBuffer html = TemplateUtils.readTemplate("/kr/or/ddit/web/param.tmpl");
//		req.setCharacterEncoding("UTF-8");
		
		String ptrn = "<tr><td>%s</td><td>%s</td></tr>\n";
		StringBuffer paramPair1 = new StringBuffer();
		Map<String, String[]> paramterMap = req.getParameterMap();
		for(Entry<String, String[]> entry : paramterMap.entrySet()) {
			String key = entry.getKey(); //(param_name)
			String[] values = entry.getValue();//(param_values)
			paramPair1.append(String.format(ptrn, 
								key, Arrays.toString(values)));
		}
		
		StringBuffer paramPair2 = new StringBuffer();
		Enumeration<String> names = req.getParameterNames();
		while (names.hasMoreElements()) {
			String paramName = (String) names.nextElement();
			String[] values = req.getParameterValues(paramName);
			paramPair2.append(String.format(ptrn, 
						paramName, Arrays.toString(values)));
		}
		
		String content = html.toString().replace("%paramPair1", paramPair1.toString())
									    .replace("%paramPair2", paramPair2.toString());
		resp.setContentType("text/html;charset=UTF-8");
		try(
			PrintWriter out = resp.getWriter();	
		){
			out.println(content);			
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("2. 구체적인 요청 처리(POST 요청)");
		StringBuffer html = TemplateUtils.readTemplate("/kr/or/ddit/web/param.tmpl");
		req.setCharacterEncoding("UTF-8");
		
		String ptrn = "<tr><td>%s</td><td>%s</td></tr>\n";
		StringBuffer paramPair1 = new StringBuffer();
		Map<String, String[]> paramterMap = req.getParameterMap();
		for(Entry<String, String[]> entry : paramterMap.entrySet()) {
			String key = entry.getKey(); //(param_name)
			String[] values = entry.getValue();//(param_values)
			paramPair1.append(String.format(ptrn, 
								key, Arrays.toString(values)));
		}
		
		StringBuffer paramPair2 = new StringBuffer();
		Enumeration<String> names = req.getParameterNames();
		while (names.hasMoreElements()) {
			String paramName = (String) names.nextElement();
			String[] values = req.getParameterValues(paramName);
			paramPair2.append(String.format(ptrn, 
						paramName, Arrays.toString(values)));
		}
		
		String content = html.toString().replace("%paramPair1", paramPair1.toString())
									    .replace("%paramPair2", paramPair2.toString());
		resp.setContentType("text/html;charset=UTF-8");
		try(
			PrintWriter out = resp.getWriter();	
		){
			out.println(content);			
		}
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("1. 요청 접수");
		super.service(req, resp);
		System.out.println("3. 응답 전송 완료");
	}
	
}








