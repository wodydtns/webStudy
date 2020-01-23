package kr.or.ddit.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Iterator;
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

import kr.or.ddit.utils.TemplateUtils;

/**
 * HttpServletRequest 
 * : http 프로토콜에 따라 패키징된 웹 요청(request)에 대한 정보를 캡슐화한 객체.
 * 
 * http request 구조
 * 1. Request Line : Protocol/ver. URL. http Method
 * 		http Method - 요청의 목적이면서 포장 방식
 * 		GET(R) : 조회
 * 		POST(C) : 생성
 * 		PUT/PATCH(U) : 갱신
 * 		DELETE(D) : 삭제
 * 		OPTIONS : pre-flight 요청에서 사용
 * 		HEAD : response body 가 없는 응답을 받을때 사용. 
 * 		TRACE : 서버 디버깅용
 * 2. Request Header : client 와 request message 에 대한 부가정보(metadata)
 * 		header name : header value (String)
 * 		enumeration = getHeaderNames()
 * 		value = getHeader(name)
 * 3. Request Body(Message Body, Content Body) : only POST
 * 
 * 요청 파라미터 전송 방법(Body 사용 여부)
 * 
 * 요청 파라미터?? 클라이언트가 서버로 전송하는 명시적인 입력 데이터
 * parameter name = parameter value
 * 1. GET(Body X) : request line 의 URL 에 QueryString의 형태로 전송
 * 	ex) gugudan.do?queryString
 *      queryString 형태 :  섹터1&섹터2...&섹터n
 *      sector : 한쌍의 파라미터 : param_name=param_value
 *      ex) gugudan.do?minDan=3&maxDan=7
 *  단점 : 보안 취약, 전송 데이터 형태와 길이 제한.   
 * 2. POST(Body O) : 전송 데이터 다양화, 
 * 					MIME 텍스트의 형태로 전송 데이터 종류 식별
 * 
 * ** 한글을 비롯한 특수문자가 전송되는 경우.
 * HTTP(1.1) , IETF, %Ef%EC -> URLEncoding 방식으로 네트웤을 통해 전송됨
 * 								(RF-2396).
 * ** 특수문자를 꺼내기 전에 characterset 설정.
 * 1. GET : line 에  QueryString 형태로 전송.
 * 		서버의 설정 필요
 *       server.xml -> URIEncoding=UTF-8 (fix)
 *                     useBodyEncodingForURI=true (setCharacterEncoding 사용)
 * 2. POST : body 영역을 통해 전송.
 * 		parameter getter  를 사용하기 전에 setCharacterEncoding(only body) 사용
 * 
 *
 */
@WebServlet(urlPatterns = "/requestDesc.do", 
			loadOnStartup = 2,
			initParams = { @WebInitParam(name = "param", value = "value") }	
		)
public class RequestDescServlet extends HttpServlet{
	private Map<String, String[]> browsers = null;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		String value = config.getInitParameter("param");
		browsers = new LinkedHashMap<String, String[]>();
		browsers.put("firefox", new String[]{"파이어폭스", "VERSION"});
		browsers.put("chrome", new String[]{"VERSION", "크롬"});
		browsers.put("trident", new String[]{"익스플로러"});
		browsers.put("others", new String[]{"기타등등"});
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		StringBuffer html = TemplateUtils.readTemplate("/kr/or/ddit/web/request.tmpl");
		
		String ptrn = "<tr><td>%s</td><td>%s</td></tr>\n";
		StringBuffer requestHeaderSpec = new StringBuffer();
		String protocol = req.getProtocol();
		String uri = req.getRequestURI();
		String method = req.getMethod();
		requestHeaderSpec.append(String.format(ptrn, 
				"Protocol(Line)", protocol));
		requestHeaderSpec.append(String.format(ptrn, 
				"URI(Line)", uri));
		requestHeaderSpec.append(String.format(ptrn, 
				"Method(Line)", method));
		
		String encoding = req.getCharacterEncoding();
		int length = req.getContentLength();
		String contentType = req.getContentType();
		String contextPath = req.getContextPath();
		requestHeaderSpec.append(String.format(ptrn, 
				"ChracterEncoding(Header)", encoding));
		requestHeaderSpec.append(String.format(ptrn, 
				"ContentLength(Header)", length+""));
		requestHeaderSpec.append(String.format(ptrn, 
				"ContentType(Header)", contentType));
		requestHeaderSpec.append(String.format(ptrn, 
				"ContextPath(Header)", contextPath));
		
		
		StringBuffer headers = new StringBuffer();
		Enumeration<String> en = req.getHeaderNames();
		while (en.hasMoreElements()) {
			String headerName = (String) en.nextElement();
			String headerValue = req.getHeader(headerName);	
			headers.append(String.format(ptrn, headerName, headerValue));
		}
		
		String agent = req.getHeader("user-agent").toLowerCase();
		String browserName = null;
		Iterator<String> it = browsers.keySet().iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			String[] datas = browsers.get(key);
			
		}
		
		for(Entry<String, String[]> entry : browsers.entrySet()) {
			String keyWord = entry.getKey();
			String[] datas = entry.getValue();
			if(agent.contains(keyWord)) {
				browserName = datas[0];
				break;
			}
		}
		if(browserName==null) browserName = browsers.get("others")[0];
		
		InputStream is = req.getInputStream(); // only post
		String serverIP = req.getLocalAddr();
		int serverPort = req.getLocalPort();
		String clientIP = req.getRemoteAddr();
		int clientPort = req.getRemotePort();
		requestHeaderSpec.append(String.format(ptrn, 
				"ServerIP(Header)", serverIP));
		requestHeaderSpec.append(String.format(ptrn, 
				"ServerPort(Header)", serverPort));
		requestHeaderSpec.append(String.format(ptrn, 
				"ClientIP(Header)", clientIP));
		requestHeaderSpec.append(String.format(ptrn, 
				"ClientPort(Header)", clientPort));
		
		Locale locale = req.getLocale();
		String country = locale.getDisplayCountry();
		String language = locale.getDisplayLanguage();
		requestHeaderSpec.append(String.format(ptrn, "국가", country));
		requestHeaderSpec.append(String.format(ptrn, "언어", language));
		
		
		String content = html.toString()
					.replace("%requestHeaderSpec", requestHeaderSpec.toString())
					.replace("%headers", headers.toString())
					.replace("%name", browserName);
		
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = resp.getWriter();
			out.println(content);
		}finally {
			if(out!=null) out.close();
		}
		
	}

}


















