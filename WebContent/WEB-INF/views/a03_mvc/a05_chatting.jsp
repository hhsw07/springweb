<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*"
    %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath }"/>
<fmt:requestEncoding value="utf-8"/>     
<!DOCTYPE html>
<%--


 --%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${path}/a00_com/bootstrap.min.css" >
<link rel="stylesheet" href="${path}/a00_com/jquery-ui.css" >
<script src="${path}/a00_com/jquery.min.js"></script>
<script src="${path}/a00_com/popper.min.js"></script>
<script src="${path}/a00_com/bootstrap.min.js"></script>
<script src="${path}/a00_com/jquery-ui.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		<%-- 
		
		--%>
		var wsocket;
		$("h2").text("채팅 시작");
		$("#enterBtn").click(function(){
			if(confirm("채팅창 접속합니다!!")){
				wsocket = new WebSocket("ws://192.168.4.34:5080/${path}/chat-ws.do");
				
			}
		});
		$("#exitBtn").click(function(){
			alert("나가기");
		});
					
	});
</script>
</head>

<body>
<div class="jumbotron text-center">
	<h2></h2>
</div>
<div class="container">
	<div class="input-group mb-3">
		<div class="input-group-prepend">
			<span class="input-group-text">아이디</span>
		</div>
		<input name="id" class="form-control" placeholder="접속할 아이디를 입력하세요" />	
	</div>
	<div style="text-align:right;">
		<input type="button" class="btn btn-info"
			value="채팅입장" id="enterBtn"/>
		<input type="button" class="btn btn-success"
			value="나가기" id="exitBtn"/>
	</div>
	
	<div class="input-group mb-3">	
		<div class="input-group-prepend">
			<span class="input-group-text">작 성 자</span>
		</div>
		<input name="writer" class="form-control" 
			placeholder="작성자입력하세요" />	
		<div class="input-group-prepend">
			<span class="input-group-text">상위글번호</span>
		</div>
		<input name="refno" class="form-control" placeholder="상위글번호" />	
	</div>		
	<div class="input-group mb-3">
		<div class="input-group-prepend">
			<span class="input-group-text">내 용</span>
		</div>
		<textarea name="content" rows="10" 
			class="form-control" placeholder="내용입력하세요" ></textarea>		 
	</div> 
	<div style="text-align:right;">
		<input type="button" class="btn btn-info"
			value="등록" id="regBtn"/>
		<input type="button" class="btn btn-success"
			value="조회 화면으로" id="goMain"/>
	</div>    
</div>
</body>
</html>