<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>05/includeDesc.jsp</title>
</head>
<body>
<h4>include(내포)의 종류</h4>
<pre>
	: 시점, 대상
	1. 동적 include : runtime 에 실행 결과를 내포.
		<%--
			RequestDispatcher rd = 
				request.getRequestDispatcher("/04/requestDesc.jsp");
			rd.include(request, response);
		--%>
	2. 정적 include : 실행 이전에 소스가 파싱될때, 소스 자체를 내포.
		<%@ include file="/04/requestDesc.jsp" %>
		<%=names %>
</pre>
</body>
</html>














