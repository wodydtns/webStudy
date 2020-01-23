<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>05/cacheControl.jsp</title>
</head>
<body>
<h4> 캐시 제어 방법 </h4>
<pre>
	Web Cache data : C/S 사이의 속도 차이를 보완하기 위해 
	              클라이언트측에 저장된 임시 데이터. 
	캐시 제어 헤더 : Pragma(HTTP/1.0), Cache-Control(HTTP/1.1), Expires(공통)
	<%
		response.setHeader("Pragma", "no-cache"); // 1.0
		response.setHeader("Cache-Control", "no-cache"); // 1.1
		response.addHeader("Cache-Control", "no-store"); // firefox
		Calendar cal = Calendar.getInstance();
		cal.add( Calendar.DATE , 7);
		long expires = 0;
		response.setDateHeader("Expires", expires);
	%>              
</pre>
</body>
</html>











