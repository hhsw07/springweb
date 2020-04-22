<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.* "
    %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<fmt:requestEncoding value="utf-8"/>
<!DOCTYPE html>
<%--

--%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${path}/a00_com/a00_com.css" >
<script src="${path}/a00_com/jquery-3.4.1.js" type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function(){
<%--

--%>
		$("h2").text("front end js");
		$("body").append("<h3>js 수행...</h3>")
		$("h3").click(function(){
			alert("수행. (클라이언트단)");
		});
		<%-- 서버단 주석으로 client단에서 보이지 않는다. --%>
		
		// 자바스크립트 초기(화면이 로딩될 시)에 server단에서 넘겨온 변수를 할당 처리한다.
		// (서버단에서 java프로그램을 결과를 만들어서 넘겨준다. 즉, 서버단에서 model01 값을 보내준다.)
		// (serverdptj ${model01}를 통해서 실제 모델 데이터를 만들어 '결과물'만 넘겨주기 때문에 
		//  client단 코드에서는 el등 java관련된 코드를 볼 수 없다.)
		var model = "${model01}";
		alert(model);
		/*
		ex) 
		1. 실무에서는 로그인해서, 로그인 회원에 대한 메시지 정보 또는
			javascript로 수행했을 때, 처리할 정보를 위와 같은 el과 scriptlet 코드와 js의 변수로 할당하여
			수행한다.
		2. 각종 프로세스 완료 후, 메시지를 보이고자 할 때도 활용된다.
			등록성공, 수정/삭제 완료 등등..
		*/
		$("h4").click(function(){
			alert("${model01}");
		});
		
		$("#ajaxBtn").click(function(){
			var xhr = new XMLHttpRequest();
			xhr.open("get","${path}/callServer.do?name="+name,true);
			xhr.onreadystatechange = function(){
				if(xhr.readyState==4 && xhr.status==200){
					alert(xhr.responseText);
				}
			};
			xhr.send();
		});
		
		$(".sch").keyup(function(){
			var xhr = new XMLHttpRequest();
			var param = "ename="+$("[name=ename]").val();
			param += "&job="+$("[name=job]").val();
			xhr.open("get","/jspexp/b01_jquery/z01_ajax/z05_emp.jsp?"+param,true);
			xhr.onreadystatechange = function(){
				if(xhr.readyState==4&&xhr.status==200){
					$("h5").html(xhr.responseText);
					var emplist = eval('('+xhr.responseText+')');
					var show = "<table align='center'>";
					show += "<tr><th>사원번호</th><th>사원명</th><th>직책</th><th>급여</th></tr>";
					for(var idx in emplist){
						var emp = emplist[idx];
						show += "<tr>";
						for(var pro in emp){
							show +="<td>"+emp[pro]+"</td>"
						}
						show += "</tr>";
					}
					show += "</table>";
					$("div").html(show);
				}
			};
			xhr.send();
		});
		
	});
</script>
</head>

<body>
<h2 align="center"></h2>
<h3>${model01}</h3> <!-- 서버단에서 클라이언트단에 모델데이터를 보낸 결과를 받아서 사용. 클라이언트에서는 html코드만 남는다. -->
<h4>클릭했을 때, 서버단 내용.</h4>
<input type="button" id="ajaxBtn" value="Ajax호출" />

<table  align="center">
	<tr><th>사원명</th>
		<td><input class="sch" type="text" name="ename" /></td></tr>
	<tr><th>직책</th>
		<td><input class="sch" type="text" name="job"  /></td></tr>
</table>
<h5></h5>
<div></div>
</body>
</html>