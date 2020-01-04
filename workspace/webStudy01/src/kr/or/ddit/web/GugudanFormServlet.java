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

@WebServlet("/gugudanForm.do")
public class GugudanFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		URL targetURL = getClass().getResource("gugudanForm.tmpl");
		System.out.println(targetURL.getFile());
		File targetFile = new File(targetURL.getFile());
		FileReader reader = new FileReader(targetFile);
		BufferedReader bufReader = new BufferedReader(reader);
		StringBuffer html = new StringBuffer();
		String tmp = null;
		while((tmp = bufReader.readLine()) != null){
			html.append(tmp + "\n");
		}
		StringBuffer option = new StringBuffer();
		String optPtrn = "<option value='%1$d'>%1$d단</option>\n";
		for(int dan = 2; dan <= 9; dan++) {
			option.append(String.format(optPtrn, dan));
		}
		
		String contents = html.toString().replace("%option", option.toString());
		
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = resp.getWriter();
			out.println(contents);
		}finally {
			if(out != null) {out.close();}
		}
				
	}


}
