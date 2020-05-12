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
<style type="text/css">
	.ordR{text-align:right;}
	.ordL{text-align:left;}
</style>
<script type="text/javascript">
	$(document).ready(function(){
		$("h2").text("사원정보");
		
		// JQUERY ajax 처리..
		$("[name=ename], [name=job]").keyup(function(){
			// jquery form.serialize() 메서드를 통해서 query string으로 변환 처리.
			// $("h2").text($("form").serialize());
/*
# jquery ajax
1. $.ajax({속성:속성값, 속성:속성값...});
2. 속성
	1) type : 요청값 전달 방식 get/post 설정..
	2) url : 요청 url로 controller단 호출
	3) data : 요청 데이터 query string, {요청키:요청값,...}
			key=value&key=value, $("form").serialize()
	4) dataType : 결과값으로 받은 데이터 유형, xml, json, text
	5) success : function(data){}
			정상 접속되었을 때, 받는 json 데이터를 data로 받음.
	6) error : function(err){}
			서버에러시 에러에 대한 처리..
*/
// http://localhost:5080/springweb/emp.do?Method=ajaxList
			$.ajax({
				type:"post",
				url:"${path}/emp.do?Method=ajaxList",
				data: $("form").serialize(),
				dataType: "json",
				success: function(data){
					var elist = data.elist;
					// $("h2").text("검색데이터크기:"+ elist.length);
					var show = "";
					$.each(elist, function(idx, emp){
						console.log(idx+":"+emp.empno);
						show += '<tr class="text-center">';
						show += '<td>'+emp.empno+'</td>';
						show += '<td  class="text-left">'+emp.ename+'</td>';
						show += '<td  class="text-left">'+emp.job+'</td>';
						show += '<td>'+emp.mgr+'</td>';
						var dt = new Date(emp.hiredate);
						show += '<td>'+dt.toLocaleDateString()+'</td>';
						var sal = emp.sal.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
						show += '<td class="text-right">'+sal+'</td>';
						var comm = emp.comm.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
						show += '<td class="text-right">'+comm+'</td>';
						show += '<td>'+emp.deptno+'</td></tr>';
							
						/*
						<tr class="text-center">
							<td>${emp.empno}</td>
							<td>${emp.ename}</td>
							<td>${emp.job}</td>
							<td>${emp.mgr}</td>
							<td> <fmt:formatDate value="${emp.hiredate}"/></td>
							<td class="ordR"><fmt:formatNumber pattern="###,##0.0" value="${emp.sal}" /></td>
							<td class="ordR"><fmt:formatNumber pattern="###,##0.0" value="${emp.comm}" /></td>
							<td>${emp.deptno}</td>
						</tr>
						*/
					});
					$("#list").html(show);
				},
				error: function(err){
					console.log("ajax처리 에러");
					console.log(err);
				}
			});
			
			
			
		});
		
		
	});
</script>
</head>

<body>
<div class="jumbotron text-center">
    <h2 align='center'></h2>
</div>
<div class="container">
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
	  <form class="form-inline" method="post" action="${path}/emp.do?Method=ajaxList">
		    <input class="form-control mr-sm-2" type="text" 
		    	name="ename"
		    	placeholder="사원명">
		    <input class="form-control mr-sm-2" type="text" 
		    	name="job" 
		    	placeholder="직책명">
		    <button class="btn btn-success" type="submit">Search</button>
		  </form>
	  </nav>
	<table class="table table-hover">
		<thead>
			<tr class="table-success text-center">
				<th>사원번호</th>
				<th>사원명</th>
				<th>직책</th>
				<th>관리자번호</th>
				<th>입사일</th>
				<th>급여</th>
				<th>보너스</th>
				<th>부서번호</th>
			</tr>
		</thead>
		<tbody id="list">
		</tbody>
	</table>
</div>
</body>
</html>