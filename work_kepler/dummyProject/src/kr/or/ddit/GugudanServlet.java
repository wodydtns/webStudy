package kr.or.ddit;

import javax.servlet.http.*;
import javax.servlet.*;

import java.io.*;
import java.net.URL;
import java.util.*;

public class GugudanServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		
		URL targetURL = getClass().getResource("gugudan.tmpl");
		File targetFile = new File(targetURL.getFile());
		FileReader reader = new FileReader(targetFile);
		BufferedReader bufReader = new BufferedReader(reader);
		StringBuffer html = new StringBuffer();
		String tmp = null;
		while((tmp = bufReader.readLine())!=null){
			html.append(tmp + "\n");
		}

		String headPtrn = "<th>%d´Ü</th>";
		String bodyPtrn = "<td>%d * %d = %d</td>";
		StringBuffer header = new StringBuffer();
		for(int dan=2;dan<=9;dan++){
			header.append(String.format(headPtrn, dan));
		}
		StringBuffer body = new StringBuffer();
		resp.setContentType("text/html;charset=UTF-8"); 
		PrintWriter out = resp.getWriter();
		
		out.close();
	}
}