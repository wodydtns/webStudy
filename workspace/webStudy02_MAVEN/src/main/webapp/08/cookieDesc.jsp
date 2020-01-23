<%@page import="java.net.URLDecoder"%>
<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>08/cookieDesc.jsp</title>
</head>
<body>
<h4>Cookie</h4>
<pre>
	: Http Stateless 의 단점을 보완하기 위한 
	  최소한의 상태정보를 클라이언트쪽에 저장하는 구조.
	  쿠키는 기본적으로 header 를 통해 전달되는 데이터
	  (set-cookie, cookie)
	 
	 쿠키 사용 단계
	 1. 쿠키 생성
	 2. 응답에 실어 클라이언트쪽으로 전송 - response header에 넣음 -header로 들어가기 때문에 String
	 
	 3. 브라우저가 자기 쿠키 저장소에 저장(각 브라우저는 각자만의 저장소가 있다-> 브라우저를 대상으로 세션을 유지한다)
	 4. 다음번 요청에 쿠키를 포함시켜 서버로 재전송(domain,path,secure 값이 동일할 경우 재전송)
	 
	 5. 요청에 포함된 쿠키를 통해 상태를 복원
	 
	 쿠키 속성
	 1. name (required)
	 2. value (required) : 특수문자 고려(encoding/decoding)
	 3. domain (host) : 쿠키의 재전송 여부를 결정하는 기준
	 	c.f) domain 구조 (레벨 구조), 하위 레벨이 상위 레벨에 포함 구조.
	 	3레벨(GTLD)  www.naver.com
	 	4레벨(NTLD)  www.naver.co.kr,  news.naver.co.kr
	 	ex) .naver.com : 해당 기관에 소속된 모든 호스트를 대상으로 재전송.
	 4. path : 	쿠키의 재전송 여부를 결정하는 기준
	 			생략하면, 쿠키의 생성 경로가 그대로 반영(기본값).
	 			쿠키의 path 로 발생하는 요청이나,
	 			path 이하의 경로로 발생하는 요청에 대해서만 재전송.
	 5. expires/max-age : 쿠키의 저장 시간을 제어(기본값= 현재 세션의 유지시간과 동일).
	 	max-age (0) : 쿠키 삭제(주의! 나머지 속성이 동일한 경우에 한함.)
	 	max-age (-1) : 브라우저 종료시 삭제됨.
	 6. secure				
	 
	 <%
	 	String value = URLEncoder.encode("한글 쿠키", "UTF-8");
	 	Cookie sampleCookie = new Cookie("sampleCookie", value);
	 	sampleCookie.setSecure(true);
	 	response.addCookie(sampleCookie);

// 	 	value = URLEncoder.encode("domain Test cookie", "UTF-8");
// 	 	Cookie domainTestCookie = new Cookie("domainCookie", value);
// 	 	domainTestCookie.setDomain("www.naver.com");
// 	 	response.addCookie(domainTestCookie);
	 	
// 	 	Cookie allDomainCookie = new Cookie("allDomain", "AllDomainCookie");
// 	 	allDomainCookie.setDomain(".chy.com");
// 	 	response.addCookie(allDomainCookie);
		Cookie allPathCookie = new Cookie("allPathCookie", "all~Path");
		allPathCookie.setPath(request.getContextPath()+"/07");
		response.addCookie(allPathCookie);
		
		Cookie longLiveCookie = new Cookie("longLiveCookie", "Long~~");
		longLiveCookie.setMaxAge(0);
		longLiveCookie.setPath("/");
		response.addCookie(longLiveCookie);
		
	 %>
	 <a href="viewCookie.jsp">동일 경로에서 쿠키 확인하러 가기</a>
	 <a href="<%=request.getContextPath() %>/07/viewCookie.jsp">다른 경로에서 쿠키 확인하러 가기</a>
	
</pre>
</body>
</html>











