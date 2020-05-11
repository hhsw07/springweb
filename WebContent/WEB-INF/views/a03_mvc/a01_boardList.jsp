<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*"
    %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
		$("#pageSize").change(function(){
			$("#curPage").val(1);	// 페이지크기를 바꾸면 초기 첫페이지가 나오도록 처리
			$("form").submit();
			
		});
		
	});
	function go(no){
		$(location).attr("href","${path}/board.do?method=detail&no="+no);
	}
	function goPage(no){
		$("#curPage").val(no);
		$("form").submit();
	}
		
	
</script>
</head>
<body>
<div class="jumbotron text-center">
  <h1>답변형 게시판</h1>
</div>
<div class="container">
  <form:form class="form" commandName="bsch" method="post">
  	<form:hidden path="curPage" /> <!-- 현재 클릭한 페이지 번호. -->
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
	  <form:input class="form-control mr-sm-2" path="title" placeholder="제목" />
	  <form:input class="form-control mr-sm-2" path="writer" placeholder="작성자" />
	  <button class="btn btn-success" type="submit">Search</button>
	  <button class="btn btn-info" id="regBtn" type="button">Registe</button>
	</nav>
    <div class="input-group mb-3">	
		<div class="input-group-prepend">
			<span class="input-group-text">총 ${bsch.count}건</span>
		</div>
		<input class="form-control" />	
		<div class="input-group-prepend">
			<span class="input-group-text">페이지 크기: </span>
			<form:select path="pageSize" class="form-control" >
				<form:option value="3">3</form:option>
				<form:option value="5">5</form:option>
				<form:option value="10">10</form:option>
				<form:option value="20">20</form:option>
				<form:option value="30">30</form:option>
			</form:select>
		</div>
	</div>
  </form:form>
  
  
  
  
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
        <td>${board.cnt}</td>
        <!-- 답글의 레벨만큼 들여쓰기 설정.. -->
        <td class="text-left">
        	<c:forEach varStatus="sts" begin="1" end="${board.level}">
        		&nbsp;&nbsp;
        		<c:if test="${board.level>1 and sts.last}">☞</c:if>
        	</c:forEach>
        	${board.title}</td>
        <td class="text-center">${board.writer}</td>
        <td><fmt:formatDate value="${board.credte}"/> </td>
        <td>${board.readcnt}</td>
      </tr>
      </c:forEach>
    </tbody>
  </table>  
	<ul class="pagination justify-content-center" style="margin:20px 0">
	  <li class="page-item"><a class="page-link" href="javascript:goPage(${bsch.startBlock-1})">Previous</a></li>
	  <c:forEach var="cnt" begin="${bsch.startBlock}" end="${bsch.endBlock}">
		  <li class="page-item ${bsch.curPage==cnt?'active':'' }">
		  	<a class="page-link" href="javascript:goPage(${cnt})">${cnt}</a>
		  </li>
	  </c:forEach>
  	  <li class="page-item"><a class="page-link" href="javascript:goPage(${(bsch.endBlock==bsch.pageCount)?bsch.endBlock:bsch.endBlock+1})">Next</a></li>
	</ul>
</div>

</body>
</html>