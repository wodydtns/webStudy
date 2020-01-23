package kr.or.ddit.web;
import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/gugudan.do")
public class GugudanServlet extends HttpServlet{
	
	
	public void service(HttpServletRequest req, 
						HttpServletResponse resp)
	throws IOException, ServletException{	
		String minStr = req.getParameter("minDan");
		String maxStr = req.getParameter("maxDan");
		int minDan = 2;
		int maxDan = 9;
		if(minStr!=null && minStr.matches("[2-9]")) {
			minDan = Integer.parseInt(minStr);
		}
		if(maxStr!=null && maxStr.matches("[2-9]")) {
			maxDan = Integer.parseInt(maxStr);
		}
		
		
		URL targetURL = getClass().getResource("gugudan.tmpl");
		File targetFile = new File(targetURL.getFile());
		FileReader reader = new FileReader(targetFile);
		BufferedReader bufReader = new BufferedReader(reader);
		StringBuffer html = new StringBuffer();
		String tmp = null;
		while((tmp = bufReader.readLine())!=null){
			html.append(tmp+"\n");
		}
		
		String headPtrn = "<th>%d단</th>";
		String bodyPtrn = "<td>%d * %d = %d</td>";
		StringBuffer header = new StringBuffer();
		for(int dan = minDan; dan <= maxDan; dan++){
			header.append(String.format(headPtrn, dan));
		}
		
		StringBuffer body = new StringBuffer();
		for(int mul=1; mul<=9; mul++){
			body.append("<tr>");
			for(int dan=minDan; dan<=maxDan; dan++){
				body.append(String.format(bodyPtrn, 
							dan, mul, (dan*mul)));
			}
			body.append("</tr>");
		}
		
		String contents = 
				html.toString().replace("%header", header.toString())
								.replace("%body", body.toString());
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.println(contents);
		out.close();
	}
}




