<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>06/implicitObject.jsp</title>
</head>
<body>
<h4>기본객체(내장 객체)</h4>
<pre>
	: 필요에 의해 명시적으로 선언하지 않아도 자동 지원되는 객체.
	0. pageContext (PageContext) : 하나의 JSP 와 관련된 모든 걸 가진 객체.
	1. request (HttpServletReqeust) : 
			클라이언트와 요청에 관한 모든 정보를 가진 객체.
	2. response (HttpServletResponse) :
			서버에서 클라이언트로 전송될 응답에 대한 모든 정보를 가진 객체.	
	3. out (JspWriter) : 응답으로 전송할 데이터를 가진 버퍼에 대한 컨트롤 객체.	
	<%="데이터" %><% out.println("데이터"); %>		
	4. session (HttpSession) : 세션에 대한 모든 정보를 가진 객체.
	5. application (ServletContext) : 현재 컨텍스트에 대한 정보를 가진 객체.
	6. config (SerlvetConfig) 
	7. page (Object) : 현재 JSP 의 객체(==this).
	8. exception (Throwable) : 발생한 예외에 대한 정보를 가진 객체. 
							   예외를 처리할 목적의 jsp 에서 사용.
							   page 지시자의 isErrorPage="true" 일때 사용.
</pre>
</body>
</html>


















