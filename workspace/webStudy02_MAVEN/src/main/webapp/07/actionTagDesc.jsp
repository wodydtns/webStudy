<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>07/actionTagDesc.jsp</title>
</head>
<body>
<h4>jsp Action 태그 </h4>
<pre>
	: JSP 스펙에 따라 기본 제공되는 일종의 커스텀 태그.
	커스텀 태그 사용 형태(서버 사이드 실행 코드)
	&lt;접두어:태그명 속성들.../&gt;
<%-- 	<jsp:forward page="/04/requestDesc.jsp"></jsp:forward> --%>
	<jsp:include page="/04/requestDesc.jsp"></jsp:include>
	
	이 텍스트의 위치 확인.
</pre>
</body>
</html>













