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
		System.out.println(getClass().getSimpleName()+"����," + (count++));
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		URL targetURL = getClass().getResource("sample.tmpl");
		System.out.println(targetURL.getFile());
		File targetFile = new File(targetURL.getFile()); // ���� �ý��� �� ���� ��� �ޱ�
		FileReader reader= new FileReader(targetFile); // ���� �б�
		BufferedReader bufReader = new BufferedReader(reader); // �� �پ� �б� ���� ��Ʈ��
		StringBuffer html = new StringBuffer(); // �о�� �����͸� ������ų ����
		String tmp = null;
		while((tmp = bufReader.readLine())!=null){ // �� �̻� ���� ������ ���� ��� null�� while �� Ż��
			html.append(tmp+"\n");
		}
		String contents = html.toString().replace("%now", new Date().toString());
		
		PrintWriter out = resp.getWriter();
		out.println(contents);
		out.close();
		
	}
	
}
