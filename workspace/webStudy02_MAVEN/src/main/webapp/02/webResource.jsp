<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>02/webResource.jsp</title>
</head>
<body>
<pre>
	웹 리소스  접근 경로 표기방식
	1. 절대경로 : 고정된 루트로부터 전체 경로를 표기.
		ex) http://localhost/webStudy01/images/Koala.jpg
		클라이언트 사이드 절대경로 : /webStudy01/images/Koala.jpg
		서버 사이드 절대경로 : /images/Koala.jpg
	2. 상대경로 : 기준 위치에 따라 경로 표기가 달라짐.
		기준 위치  : 현재 브라우저의 주소창의 주소.
		ex) ../images/Koala.jpg
		
	URI(Identifier) vs URL(Locator)
	1. URN(Naming)
	2. URC(Contents)
	3. URL(Locator)
		
</pre>
<img src="http://localhost/webStudy01/images/Koala.jpg">
<img src="../images/Koala.jpg" />
</body>
</html>












