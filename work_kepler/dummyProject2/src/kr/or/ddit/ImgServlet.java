package kr.or.ddit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ImgServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		URL targetURL = getClass().getResource("Desert.jpg");
		File targetFile = new File(targetURL.getFile());
		FileReader reader = new FileReader(targetFile);
		BufferedReader bufReader = new BufferedReader(reader);
		
		
		resp.setContentType("image/jpeg;charset=UTF-8"); 
		PrintWriter out = resp.getWriter();
		out.println(reader);
		out.close();
	}
}
