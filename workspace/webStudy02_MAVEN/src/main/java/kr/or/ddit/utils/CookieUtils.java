package kr.or.ddit.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * 쿠키 생성과 핸들링을 지원하는 유틸리티
 * @author pc04
 *
 */
public class CookieUtils { ///throws IOException : 더 포괄적인 예외를 처리하는 것이 좋은 구조
	public static String encoding = "UTF-8";
	private Map<String,Cookie> cookieMap;
	private HttpServletRequest request;
	// 전략 패턴을 구현하는 방법2. 생성자 주입(필수적으로 받아야한다면) -> 의존관계가 역전되어있다 -> DIP
	public CookieUtils(HttpServletRequest request) {
		super();
		this.request = request;
		Cookie[] cookies = request.getCookies();
		cookieMap = new LinkedHashMap<String, Cookie>();
		if(cookies!=null) {
			for(Cookie tmp:cookies) {
				cookieMap.put(tmp.getName(),tmp);
			}
		}
	}

	/*
	 * // 전략 패턴을 구현하는 방법1.setter 인젝션
	 *  public void setRequest(HttpServletRequest request) { this.request = request; }
	 */
	public static Cookie createCookie(String name,String value) throws IOException {
		value = URLEncoder.encode(value,encoding);
		Cookie cookie = new Cookie(name,value);
		return cookie;
	}
	// 데이터의 식별성을 갖기 위한 enum객체
	public static enum TextType{
		DOMAIN,PATH
	}
	public Map<String, Cookie> getCookieMap() {
		return cookieMap;
	}
	/**
	 * @param name : 검색할 쿠키명
	 * @return : 검색된 쿠키, 존재하지 않으면,null반환
	 */
	public Cookie getCookie(String name) {
		return cookieMap.get(name);
	}
	/**
	 * @param name : 검색할 쿠키명
	 * @return : 검색된 쿠키의 값, 없으면 null반환, 있으면 UTF-8
	 * @throws IOException
	 */
	public String getCookieValue(String name) throws IOException{
		Cookie cookie = getCookie(name);
		String value = null;
		if(cookie!=null) {
			value = URLDecoder.decode(cookie.getValue(),encoding);
		}
		return value;
	}
	
	public static Cookie createCookie(String name,String value,String text,TextType kind) throws IOException{
		Cookie cookie = createCookie(name,value);
		if(TextType.DOMAIN.equals(kind)) {
			cookie.setDomain(text);
		}else if(TextType.PATH.equals(kind)){
			cookie.setPath(text);
		}
		return cookie;
	}
	/**
	 * - Alt Shift J
	 * 쿠키 생성
	 * @param name : 쿠키명
	 * @param value : 쿠키값(기본 인코딩UTF-8)
	 * @param domain :  도메인
	 * @param path : 경로
	 * @return : 생성된 쿠키
	 * @throws IOException
	 */
	public static Cookie createCookie(String name,String value,String domain,String path) throws IOException{
		Cookie cookie = createCookie(name,value);
		cookie.setDomain(domain);
		cookie.setPath(path);
		return cookie;
	}
	/**
	 * @param name : 쿠키명
	 * @param value : 쿠키값(기본 인코딩UTF-8)
	 * @param maxAge : 초단위 만료 시간
	 * @return : 생성된 쿠키
	 * @throws IOException 
	 */
	public static Cookie createCookie(String name,String value,int maxAge) throws IOException{
		Cookie cookie = createCookie(name,value);
		cookie.setMaxAge(maxAge);
		return cookie;
	}
	/**
	 * @param name : 쿠키명
	 * @param value :쿠키값(기본 인코딩UTF-8)
	 * @param text :  설정값
	 * @param kind : text를 도메인 or Path로 설정하는 enum
	 * @param maxAge : 쿠키의 초단위 만료 시간
	 * @return : 생성된 쿠키
	 * @throws IOException
	 */
	public static Cookie createCookie(String name,String value,String text, TextType kind,int maxAge) throws IOException{
		Cookie cookie = createCookie(name,value);
		if(TextType.DOMAIN.equals(kind)) {
			cookie.setDomain(text);
		}else if(TextType.PATH.equals(kind)){
			cookie.setPath(text);
		}
		cookie.setMaxAge(maxAge);
		return cookie;
	}
	/**
	 * @param name : 쿠키명
	 * @param value : 쿠키값(기본 인코딩 : UTF-8)
	 * @param domain : 도메인
	 * @param path : 패스
	 * @param maxAge : 쿠키의 초단위 만료 시간
	 * @return : 생성된 쿠키
	 * @throws IOException
	 */
	public static Cookie createCookie(String name,String value,String domain, String path,int maxAge) throws IOException{
		Cookie cookie = createCookie(name, value);
		cookie.setDomain(domain);
		cookie.setPath(path);
		cookie.setPath(path);
		return cookie;
	}



}
