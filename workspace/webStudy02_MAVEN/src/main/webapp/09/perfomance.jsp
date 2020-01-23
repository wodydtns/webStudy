<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="kr.or.ddit.member.dao.MemberDAOImpl"%>
<%@page import="kr.or.ddit.member.dao.IMemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>09/performance.jsp</title>
</head>
<body>
	<h4>Performance Check(Response Time)</h4>
	<pre>
		response time : processing time(0.00001%) + latency time(99.9999%)
		<%
// 			a001 사용자 한 번 조회 , 한 번 출력 : 30ms
// 			a001 사용자 100번 조회 , 100번 출력 : 1200ms
// 			a001 사용자 1번 조회 , 100번 출력 : 28ms
// 			a001 사용자 1번 조회 , 100번 출력(pooling 상태에서 실행) : 50ms -> 
			IMemberDAO memberDAO = MemberDAOImpl.getInstance();
			long start = System.currentTimeMillis();
			for(int i=1;i<=100;i++){
				MemberVO member =memberDAO.selectMember("a001");
				out.println(member.getMem_name()+","+member.getMem_hp());
			}
			long end = System.currentTimeMillis();
			out.println(String.format("소요시간: %d ms",(end-start)));
		%>
	</pre>
</body>
</html>