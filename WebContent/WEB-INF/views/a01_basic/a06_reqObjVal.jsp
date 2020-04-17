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
		$("h2").text("요청값 VO 객체");
	});
</script>
</head>

<body>
<h2 align="center"></h2>
<div></div>
<h3>이름: ${p01.name} </h3>
<h3>나이: ${p01.age}</h3>
<h3>사는곳: ${p01.loc}</h3>
<!-- 
/prod.do
물건명: [  ]
가격: [  ]
개수: [  ]
[구매]

/prodShow.do
@@@을 @@개 @@@원 구매하여 총비용이 @@원 입니다.
-->
</body>
</html>