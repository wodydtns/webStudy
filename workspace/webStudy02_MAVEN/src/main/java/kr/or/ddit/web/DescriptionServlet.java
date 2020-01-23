package kr.or.ddit.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet ??
 * 	: 자바 기반의 확장 CGI 방식으로 웹 요청을 처리할 수 있는 객체에 대한 명세(spec)
 * 1. 개발 단계
 * 1) HttpServlet 을 상속받은 자바 객체 정의 -> 필요한 메소드 overriding
 * 2) 컴파일 -> /WEB-INF/classes(classpath) 에 배포
 * 3) WAS(Servlet container, tomcat) 에 서블릿 등록.
 * 		web.xml -> servlet -> servlet-name, servlet-class
 * 4) 웹상의 요청(command)에 의해 특정 서블릿이 동작할 수 있도록 mapping 설정.
 * 		web.xml -> servlet-mapping -> serlvet-name, url-pattern
 * 5) startup
 * 
 * 2. WAS(Servlet container) 의 역할
 *    : container 내부에 등록된 servlet 의 lifecycle 관리.
 *    
 *    1) 설정이 없는 한 등록된 서블릿의 객체를 최초의 요청시에 생성.
 *    2) web.xml 에 서블릿을 등록할때의 설정을 캡슐화해서
 *       서블릿 하나당 servletConfig 객체를 1:1로 관리함.
 * 
 * 3. Servlet 의 메소드(callback) 종류
 *    1) lifecycle callback : 생명주기내에서 단 한번(singleton)만 호출
 *    						ex) init, destroy	
 *    2) request callback : mapping URI 로 발생하는 요청에 대해 반복 호출
 *                         ex) service, doXXX
 *    
 */
public class DescriptionServlet extends HttpServlet{
	
	
	public DescriptionServlet() {
		super();
		System.out.println(getClass().getName()+"생성");
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		System.out.println(getClass().getSimpleName()+"초기화");
//		String value = config.getInitParameter("testParam");
		Enumeration<String> names = config.getInitParameterNames();
		while (names.hasMoreElements()) {
			String name = (String) names.nextElement();
			String value = config.getInitParameter(name);
			System.out.println(value);
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 리로딩되나보자
		try(
		PrintWriter out = resp.getWriter();
		){
			out.println(getServletContext().hashCode());
		}
	}
	
	@Override
	public void destroy() {
		System.out.println(getClass().getSimpleName()+"소멸");
	}
}











