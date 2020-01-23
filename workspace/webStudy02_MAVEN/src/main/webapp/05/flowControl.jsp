<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>05/flowControl.jsp</title>
</head>
<body>
<h4> 웹 어플리케이션의 흐름 제어 (페이지 이동)</h4>
<pre>
	** Stateless : request 가 서버로 전송이 되고, 그에 대한 1:1 구조의
		응답이 다시 클라이언트로 전송되면, connection 이 끊기고, 
		양쪽 peer 에서 request, response 에 대한 정보가 사라짐.
	: A -> B 로 가는 방법
	1. Request Dispatch 방식 : 원본 요청이 그대로 도착지로 전달되는 구조.
							서버내에서만 이동하는 구조(서버 사이드 위임 처리).
		1) forward
		2) include
		<%
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/innerView.jsp");
			rd.forward(request, response);
		%>	
	2. Redirect 방식 : Stateless
			B로 이동하기전 body 가 없고, status code(302), header(Location)
			를 가지고 response 하나가 commit - 원본 요청을 삭제됨;
			클라이언트가 Location 의 방향으로 새로운 요청을 발생시킴.
			**** B에서는 원본 요청에 대한 정보를 알수 없음.
		<%--
			response.sendRedirect(request.getContextPath() + "/WEB-INF/views/innerView.jsp");
		--%>	
</pre>
</body>
</html>














