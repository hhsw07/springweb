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
A06_ModelAttributeCtrl.java

ex) 수학 문제
숫자1:[ ]
숫자2:[ ]
연산식:[select +,-,*,/ ]
선택했을 때, submit 처리 되어,
결과 @@ + @@ = @@

springweb.z01_vo.Calcu.java
--%>
		$("h2").text("수학 문제");
		// calList calcu
		$("[name=cal]").val("${calcu.cal}");
		$("[name=cal]").change(function(){
			if($(this).val()!="") $("form").submit();
		});
		
	});
</script>
</head>

<body>
<h2 align="center"></h2>
	<form>
	<table align="center" border>
		<tr><th>숫자1</th>
			<td><input type="text" name="num01" value="${calcu.num01}"></td>
		<tr><th>연산식</th>
			<td>
				<select name="cal">
					<option value="">연산자를 선택하세요</option>
					<c:forEach var="cd" items="${calList}">
						<option value="${cd.key}">${cd.val}</option>
					</c:forEach>
				</select>
			</td>
		<tr><th>숫자2</th>
			<td><input type="text" name="num02" value="${calcu.num02}"></td>
	</table>
	</form>
<h3 align="center">${calcu.num01} ${calcu.cal} ${calcu.num02} = ${calcu.sum() }</h3>	
	
	
</body>
</html>