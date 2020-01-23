<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- <meta http-equiv="Refresh" content="5;url=https://www.naver.com"> -->
<title>05/autoRequest.jsp</title>
</head>
<body onload="init();">
<h4> 자동 요청 발생 방법 </h4>
<pre>
	1. 서버사이드 방식 : Refresh 응답 헤더
		서버의 현재 시각 : <%=String.format("%tc", Calendar.getInstance()) %>
		<%--
			response.setIntHeader("Refresh", 5);
		--%>
	2. 클라이언트 사이드 방식
		클라이언트의 현재 시각 : <span id="timer"></span>
		1) HTML : meta
		2) Javascript
		   a) interval : 주기적인 반복작업
		   b) timeout : 시간 지연후 한번의 작업
		   <input type="button" value="timer stop" onclick="clickHandler();"/>
		   <input type="button" value="timer start" onclick="init();"/>
</pre>
<script type="text/javascript">
	var timerJob=null;
	function clickHandler(){
		if(timerJob!=null){
			clearInterval(timerJob);
			timerJob = null;
		}
	}
	function init(){
		timerTag = document.querySelector("#timer");
		if(timerJob==null){
			timerJob = setInterval(function(){
				var now = new Date();
				timerTag.innerHTML = now.toLocaleString();
			}, 1000);
		}
		setTimeout(function(){
			location.href="https://www.naver.com";
		}, 5000);
		console.log(location.origin);
	}
</script>
</body>
</html>














