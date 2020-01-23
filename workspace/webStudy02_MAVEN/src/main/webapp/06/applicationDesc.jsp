<%@page import="java.io.InputStream"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>06/applicationDesc.jsp</title>
</head>
<body>
<h4>ServletContext application</h4>
<pre>
	: 현재 컨텍스트(웹어플리케이션, 서버)에 대한 정보를 가진 객체
	
	1. MIME 확인 : <%=application.getMimeType("test.jpg") %>
	2. 로그 기록 : 
	<%
		application.log("테스트 로그 메시지");
	%>
	3. 서버에 대한 정보 확인
		1) Dynamic Web Module version 확인
			<%=application.getMajorVersion()+"."+application.getMinorVersion() %>
		2) 서버에 대한 정보
			<%=application.getServerInfo() %>
		3) <%=application.getContextPath() %>		
	4. ***** 웹 리소스 확보 : fileSystemPath getRealPath(url)
		InputStream getResourceAsStream(url)
		
		/images/Desert.jpg
		<%=application.getRealPath("/images/Desert.jpg") %>
		/06
		<%=application.getRealPath("/06") %>
		<%
// 			파일시스템 절대경로를 통해 매체 객체 생성
// 		File srcFile = new File(application.getRealPath("/images/Desert.jpg"));
		File targetFolder = new File(application.getRealPath("/06"));
		
		File targetFile = new File(targetFolder, "Desert.jpg");
		byte[] buffer = new byte[1024];
		int length = -1;
		try(
// 			FileInputStream fis = new FileInputStream(srcFile);
			InputStream fis = application.getResourceAsStream("/images/Desert.jpg");	
			FileOutputStream fos = new FileOutputStream(targetFile);	
		){
			while((length = fis.read(buffer))!=-1){
				fos.write(buffer, 0, length);
			}
		}
		%>
		
</pre>
<img src="<%=application.getContextPath() %>/06/Desert.jpg" />
<form>
	<input type="hidden" name="target" />
</form>
<ul>
<%
File rootFolder = new File(application.getRealPath("/04"));
String[] children = rootFolder.list();
for(String child : children){
	%>
	<li><%=child %></li>
	<%
}
%>
</ul>
</body>
</html>














