package kr.or.ddit.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Out;

@WebServlet(urlPatterns = "/requestDesc.do",
			loadOnStartup = 2,
			initParams = { @WebInitParam(name = "",value="")}
			)
public class RequestDescServlet extends	HttpServlet{
	
	private Map<String,String[]> browsers = null;
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		String value = config.getInitParameter("param");
		// LinkedHashMap <key, value[]>
		browsers = new LinkedHashMap<String, String[]>(); // key value 순서로 구성하기
		browsers.put("firefox",new String[]{"파이어폭스"});
		browsers.put("chrome",new String[]{"크롬"});
		browsers.put("trident",new String[]{"익스플로러"});
		browsers.put("others",new String[]{"기타등등"});
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		URL targetURL = getClass().getResource("request_conf.tmpl");
		File targetFile = new File(targetURL.getFile());
		FileReader reader = new FileReader(targetFile);
		BufferedReader bufReader = new BufferedReader(reader);
		StringBuffer html = new StringBuffer();
		String tmp = null;
		while((tmp=bufReader.readLine())!=null) {
			html.append(tmp + "\n");
		}
		StringBuffer conf = new StringBuffer();
		String pattern = "<tr><td>%s</td><td>%s</td></tr>\n";
		
		String protocol = req.getProtocol();
		String uri = req.getRequestURI();
		String method = req.getMethod();
		String encoding = req.getCharacterEncoding(); // body의 encoding방식 
		int length = req.getContentLength(); // body의 길이
		String contentType = req.getContentType(); // 마임 type을 return / 없으면 null 
		String contextPath = req.getContextPath();
//		req.getHeader(""); -> key value로 전달됨 , 
		conf.append(String.format(pattern,"protocol",protocol));
		conf.append(String.format(pattern,"uri",uri));
		conf.append(String.format(pattern,"method",method));
		conf.append(String.format(pattern,"encoding",encoding));
		conf.append(String.format(pattern,"length",Integer.toString(length)));
		conf.append(String.format(pattern,"contentType",contentType));
		conf.append(String.format(pattern,"contextPath",contextPath));
		
		InputStream is = req.getInputStream(); // body를 읽어온다
		String serverIp = req.getLocalAddr(); // 서버의 ip주소를 읽어온다
		int serverPort = req.getLocalPort(); // 서버의 port number
		String clientIp = req.getRemoteAddr(); // 클라이언트의 ip주소를 읽어온다.
		int clientPort = req.getRemotePort(); // 클라이언트의 port - 1024보다(well-known port number) 더 높은 것일 가능성이 높음
		conf.append(String.format(pattern,"body",is));
		conf.append(String.format(pattern,"serverIp",serverIp));
		conf.append(String.format(pattern,"serverPort",serverPort));
		conf.append(String.format(pattern,"clientIp",clientIp));
		conf.append(String.format(pattern,"clientPort",clientPort));
		Locale locale = req.getLocale(); // Accept-Language header를 통해서 client의 지역을 가져올 수 있다
		
		String country = locale.getDisplayCountry(); // return : 대한민국
		String language = locale.getDisplayLanguage(); // return : 언어
		conf.append(String.format(pattern,"국가",country));
		conf.append(String.format(pattern,"언어",language));
		
		StringBuffer header_conf = new StringBuffer();
		
		Enumeration<String> en = req.getHeaderNames(); // header의 전체 내용을 가져옴
		while(en.hasMoreElements()) {
			String headerName = (String)en.nextElement();
			
			String headervalue = req.getHeader(headerName);
			header_conf.append(String.format(pattern,headerName,headervalue));
		}
		String agent = req.getHeader("user-agent").toLowerCase(); // "user-agent"를 파라미터로 사용 user-agent의 값이 return
		String browerName = null;
		
		
//		for(Entry<String, String[]> entry:browsers.entrySet()) {
//			String keyWord = entry.getKey();
//			String[] datas = entry.getValue();
//			if(agent.contains(keyWord)) {
//				browerName = datas[0];
//				break;
//			}
//		}
		if(browerName == null) browerName = browsers.get("others")[0];
		
		String contents = html.toString().replace("%text", conf).toString().replace("%header", header_conf).toString().replace("%brower",browerName);
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out =null;
		try {
			out = resp.getWriter();
			out.println(contents);
			
		}finally {
			out.close();
		}
	}
	
}
