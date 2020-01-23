<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.Map"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>07/background.jsp</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.4.1.min.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/main.css">
</head>
<body>
<div id="header">
	<jsp:include page="/includee/header.jsp" />
</div>
<div id="left">
	<jsp:include page="/includee/left.jsp" />
</div>
<div id="content">
<%
	String path = (String)request.getAttribute("goPage");
	if(path!=null){
		pageContext.include(path);
	}else{
		%>
		기본 텍스트
		<%
	}
	
%>
</div>
<div id="footer">
	<jsp:include page="/includee/footer.jsp" />
</div>
</body>
</html>














