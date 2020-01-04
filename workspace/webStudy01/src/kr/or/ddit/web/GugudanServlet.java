package kr.or.ddit.web;

import javax.servlet.http.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;

import java.io.*;
import java.net.URL;

@WebServlet(urlPatterns = "/gugudan.do")
public class GugudanServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		
		String minStr = req.getParameter("minDan");
		String maxStr = req.getParameter("maxDan");
		int minDan = 2;
		int maxDan = 9;
		if(minStr != null && minStr.matches("[2-9]")) {
			minDan = Integer.parseInt(minStr);
		}
		if(maxStr != null && maxStr.matches("[2-9]")) {
			maxDan = Integer.parseInt(maxStr);
		}
		
		URL targetURL = getClass().getResource("gugudan.tmpl");
		System.out.println(targetURL.getFile());
		File targetFile = new File(targetURL.getFile());
		FileReader reader = new FileReader(targetFile);
		BufferedReader bufReader = new BufferedReader(reader);
		StringBuffer html = new StringBuffer();
		String tmp = null;
		while((tmp = bufReader.readLine()) != null){
			html.append(tmp + "\n");
		}
		String headPtrn = "<th>%d단</th>";
		String bodyPtrn = "<td>%d x %d = %d</td>";
		StringBuffer head = new StringBuffer();
		StringBuffer body = new StringBuffer();
		
		for(int i = minDan; i <= maxDan; i++){
			head.append(String.format(headPtrn, i));
		}
		for(int mul = 1; mul <= 9; mul++){
			body.append("<tr>");		
			for(int dan = minDan; dan <= maxDan; dan++){
				body.append(String.format(bodyPtrn, dan, mul, (dan * mul)));
			}
			body.append("</tr>");
		}
		String contents = html.toString().replace("%header", head.toString())
				.replace("%body", body.toString());
		
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.println(contents);
		out.close();
		
	}

}