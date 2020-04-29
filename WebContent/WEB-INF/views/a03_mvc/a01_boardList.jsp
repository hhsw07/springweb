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
		$("h2").text("시작");
		$("#regBtn").click(function(){
			if(confirm("등록합니다")){
			// 등록화면 호출.
				$(location).attr("href","${path}/board.do?method=insForm");
			}
		});
	});
	function go(no){
		$(location).attr("href","${path}/board.do?method=detail&no="+no);
	}
	
</script>
</head>
<body>
<div class="jumbotron text-center">
  <h1>답변형 게시판</h1>
</div>
<div class="container">
  <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
  <form class="form-inline" method="post" action="${path}/board.do?method=list">
    <input class="form-control mr-sm-2" name="title" type="text" 
    	value= "${param.title}" placeholder="제목">
    <input class="form-control mr-sm-2" name="writer" type="text" 
		value= "${param.writer}" placeholder="작성자">
    <button class="btn btn-success" type="submit">Search</button>
    <button class="btn btn-info" id="regBtn" type="button">Registe</button>
  </form>
  </nav>
   <table class="table table-hover">
    <thead>
      <tr class="table-success text-center">
        <th>번호</th>
        <th>제목</th>
        <th>작성자</th>
        <th>작성일</th>
        <th>조회수</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="board" items="${blist}">	
      <tr class="text-center" onclick="javascript:go(${board.no})">
        <td>${board.no}</td>
        <td class="text-left">${board.title}</td>
        <td class="text-center">${board.writer}</td>
        <td><fmt:formatDate value="${board.credte}"/> </td>
        <td>${board.readcnt}</td>
      </tr>
      </c:forEach>
    </tbody>
  </table>  

</div>

</body>
</html>