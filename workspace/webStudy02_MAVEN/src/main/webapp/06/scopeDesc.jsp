<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>06/scopeDesc.jsp</title>
</head>
<body>
<h4> Scope (영역) 을 통한 데이터 공유 </h4>
<pre>
	: 생존 범위가 다른 4개의 기본객체가 가진 메모리(Map&lt;String,Object&gt;).
	: 해당 공간을 통해 공유되는 데이터를 attribute 라 함.
	** 모든 map 은 생존 범위가 다른 객체와 동일한 생존성(공유의 범위)을 가짐.
	page Scope : pageContext 의 map
	request Scope : request 의 map
	session Scope : session 의 map
	application Scope : ServletContext 의 map
	
	setAttribute, getAttribute, 
	removeAttribute(flash attribute 에 사용됨.)
	
	<%
		pageContext.setAttribute("pageAttr", "페이지 속성");
		request.setAttribute("requestAttr", "요청 속성");
		session.setAttribute("sessionAttr", "세션 속성");
		application.setAttribute("applicationAttr", "서블릿컨텍스트 속성");
		
// 		pageContext.include("/06/attrView.jsp");
		response.sendRedirect(request.getContextPath() + "/06/attrView.jsp");
	%>
<%-- 	<a href="<%=request.getContextPath() %>/06/attrView.jsp">어트리뷰트 뷰</a> --%>
</pre>
</body>
</html>











