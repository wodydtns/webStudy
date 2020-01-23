<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>06/sessionTimer.jsp</title>
</head>
<body>
<h4> 세션 타이머 </h4>
<h4 id="timerArea"></h4>
<div id="msgArea">
	세션 타임을 연장할래?
	<input type="button" value="응" class="ctrlBtn" id="yesBtn"/>
	<input type="button" value="아니" class="ctrlBtn" id="noBtn"/>
</div>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/sessionTimer.js"></script>
<script type="text/javascript">
	$("#timerArea").uiInit({
		msgArea : $("#msgArea"),
		ctrlBtn : $(".ctrlBtn"),
		yesBtnId : "yesBtn"
	});
	init(<%=session.getMaxInactiveInterval() %>);
</script>
</body>
</html>













