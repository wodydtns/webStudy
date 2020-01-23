<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>06/pageContextDesc.jsp</title>
</head>
<body>
<h4>PageContext pageContext</h4>
<pre>
	: 하나의 JSP 페이지에 대한 모든 정보를 가지며, 가장 먼저 생성되는 객체.
	1. 나머지를 기본객체 확보
	<%=request == pageContext.getRequest() %>
	<%=out == pageContext.getOut() %>
	<%=pageContext.getServletContext() == application %>
	<%=application == getServletContext() %>
	<%=application.hashCode() %>
	2. 페이지 이동(Request Dispatch)
	<%
// 		pageContext.forward("/04/requestDesc.jsp");
		pageContext.include("/04/requestDesc.jsp");
// 		request.getRequestDispatcher("/04/requestDesc.jsp")
// 				.include(request, response);
	%>
	3. 에러 데이터 확보
	4. Scope 핸들링
	<%
		pageContext.setAttribute("now", new Date(), PageContext.SESSION_SCOPE);
		session.setAttribute("now", new Date());
		session.getAttribute("now");
		pageContext.getAttribute("now", PageContext.SESSION_SCOPE);
	%>
</pre>
</body>
</html>













