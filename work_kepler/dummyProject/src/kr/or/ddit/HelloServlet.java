package kr.or.ddit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet {
	private static int count = 1;
	public HelloServlet() {
		super();
		System.out.println(getClass().getSimpleName()+"생성," + (count++));
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		URL targetURL = getClass().getResource("sample.tmpl");
		System.out.println(targetURL.getFile());
		File targetFile = new File(targetURL.getFile()); // 파일 시스템 상 절대 경로 받기
		FileReader reader= new FileReader(targetFile); // 파일 읽기
		BufferedReader bufReader = new BufferedReader(reader); // 한 줄씩 읽기 위한 스트림
		StringBuffer html = new StringBuffer(); // 읽어온 데이터를 누적시킬 공간
		String tmp = null;
		while((tmp = bufReader.readLine())!=null){ // 더 이상 읽을 내용이 없을 경우 null로 while 문 탈출
			html.append(tmp+"\n");
		}
		String contents = html.toString().replace("%now", new Date().toString());
		
		PrintWriter out = resp.getWriter();
		out.println(contents);
		out.close();
		
	}
	
}
