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
<style type="text/css">
	.input-group-text{width:100%;}
	.input-group-prepend{width:20%;}
	#chatArea{
		width:80%; height:200px; border: 1px solid green;
		overflow-y:auto; text-align:left;
	}
	
</style>

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
		$("#sendBtn").click(function(){
			sendMsg();
		});
		$("#msg").keyup(function(e){
			if(e.keyCode==13){
				sendMsg();
			}
		});
		function sendMsg(){
			alert("전송");
			var id = $("#id").val();	// 보내는 사람 id
			var msg = $("#msg").val();	// 보내는 메시지
			// 서버 handler에 데이터 전송..
			// handleTextMessage(WebSocketSession session, TextMessage message)
			// 를 호출 메시지는 TextMessage 객체를 통해서 전달
			wsocket.send("msg:"+id+": "+msg);
			$("#msg").val("");
			$("#msg").focus();
		}
		
		$("#enterBtn").click(function(){
			start();
		});
				
		function start(){
			if(confirm("채팅창 접속합니다!!")){
				// 내 IP 주소
				// new WebSocket("")
				// ws:// protocol : 웹 소켓통신 언어로 데이터 처리.
				// ws://192.168.4.34:5080/${path} : 특정한 서버 주소
				// /chat-ws.do : 연동되는 socket통신 handler 모듈의 url 패턴
				//		url 패턴 : <websocket:mapping handler="chatHandler" path="/chat-ws.do"/>
				//		@Controller("chatHandler")
				// (내 PC:192.168.4.34) (선생님 PC:211.238.140.48) (민기 PC:192.168.4.20)
				wsocket = new WebSocket("ws://192.168.4.34:5080/${path}/chat-ws.do");
				//wsocket = new WebSocket("ws://211.238.140.48:5080/${path}/chat-ws.do");
				//wsocket = new WebSocket("ws://192.168.4.20:5080/${path}/chat-ws.do");
				// 객체가 생성되는 순간 접속이 처리된다.
				// # 기본 처리 메서드 선언..
				// 1. 접속 처리 후, 처리할 메서드.
				// 	  서버의 handler를 처리한 후에 처리할 내용..
				wsocket.onopen=function(evt){
					// alert("서버에 연결되었습니다.");
					console.log(evt);
					recieveMsg("연결되었습니다.");
				};
				// 2. 메세지를 전송해주고, 전송 받을 때 처리 내용.
				wsocket.onmessage=function(evt){
					var data = evt.data;
					if(data.substring(0,4) == "msg:"){
						revMsg = data.substring(4);
						recieveMsg(revMsg);
					}
				};
				// 3. 접속 종료 후, 처리할 메서드.
				wsocket.onclose=function(){
					// 서버단에서 접속 종료 후, 처리할 front단 내용
					alert("접속 종료합니다.");
					$("#chatMessageArea").text("");
					$("#id").val("");
					$("#id").focus();
				};
			}
		};
		// 메시지 처리 메소드(전송 온 메시지를 화면에서 추가 처리)
		function recieveMsg(revMsg){
			$("#chatMessageArea").append(revMsg+"<br>");
			var ht = parseInt($("#chatArea").height());
			var mx = parseInt($("#chatMessageArea").height());
			console.log(ht+":"+(mx));
			$("#chatArea").scrollTop(mx);
			/*
			chatMessageArea의 div가 메시지가 계속 추가되게 처리..
			추가된 높이의 크기만큼 chatArea div의 scrolling 부분을
			변경 처리..
			*/
		}
		
		$("#exitBtn").click(function(){
			wsocket.send("msg:"+$("#id").val()+":연결 접속 종료했습니다.");
			wsocket.close(); 
			// handler의 접속 종료와 연결 되어 있음..afterConnectionClosed()
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
		<input id="id" class="form-control" placeholder="접속할 아이디를 입력하세요" />
		<input type="button" class="btn btn-info"
			value="채팅입장" id="enterBtn"/>
		<input type="button" class="btn btn-success"
			value="나가기" id="exitBtn"/>	
	</div>
	<div class="input-group mb-3">	
		<div class="input-group-prepend">
			<span class="input-group-text">메시지</span>
		</div>
		<input id="msg" class="form-control" 
			placeholder="보낼 메시지 입력하세요." />	
		<input type="button" class="btn btn-info"
			value="전송" id="sendBtn"/>
	</div>
	<div class="input-group mb-3">
		<div class="input-group-prepend">
			<span class="input-group-text">내 용</span>
		</div>
		<!-- #chatArea -->
		<div id="chatArea" class="input-group-append">
			<div id="chatMessageArea">
			</div>
		</div>
	</div> 
</div>
</body>
</html>