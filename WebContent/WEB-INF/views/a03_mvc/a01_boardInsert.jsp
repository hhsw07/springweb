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
</style>
<script src="${path}/a00_com/jquery.min.js"></script>
<script src="${path}/a00_com/popper.min.js"></script>
<script src="${path}/a00_com/bootstrap.min.js"></script>
<script src="${path}/a00_com/jquery-ui.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		<%-- 
		
		--%>
		var isReg= "${param.title}";
		if(isReg!=""){
			if(!confirm("등록 완료하였습니다\n계속 등록하시겠습니까?")){
				$(location).attr("href","${path}/board.do?method=list");	
			}
		}
		
		var refno = "${param.refno}";
		if(refno != ""){
			$("[name=refno]").val(refno);
			$("[name=refno]").attr("readonly","true");
		}
		
		
		$("#regBtn").click(function(){
			if(confirm("등록합니다.")){
				$("form").submit();				
			}
		});	
		$("#goMain").click(function(){
			if(confirm("조회 화면 이동 합니다.")){
				$(location).attr("href","${path}/board.do?method=list");			
			}
		});	
		$(".custom-file-input").on("change",function(){
			$(this).next(".custom-file-label").text($(this).val());
		});
	});
</script>
</head>

<body>
<div class="jumbotron text-center">
  <h1>게시판 등록</h1>
</div>
<div class="container">
	<!-- 파일을 업로드 할 수 있는 type으로 변경 -->
	<form method="post" enctype="multipart/form-data" 
		action="${path}/board.do?method=insert">
	<div class="input-group mb-3">
		<div class="input-group-prepend">
			<span class="input-group-text">제 목</span>
		</div>
		<input name="title" class="form-control" 
			placeholder="제목입력하세요" />	
		 
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
		<input name="refno" class="form-control" 
			placeholder="상위글번호" value="0" />	
	</div>		
	<div class="input-group mb-3">
		<div class="input-group-prepend">
			<span class="input-group-text">내 용</span>
		</div>
		<textarea name="content" rows="10" 
			class="form-control" 
			placeholder="내용입력하세요" ></textarea>		 
	</div> 
	<div class="input-group mb-3">
		<div class="input-group-prepend">
			<span class="input-group-text">첨부 파일</span>
		</div>
		<div class="custom-file">
			<input type="file" name="report" 
				class="custom-file-input" id="file01"/>
			<label class="custom-file-label" for="file01">
			파일을 선택하세요!</label>
		</div>		
	</div> 	
	<div class="input-group mb-3">
		<div class="input-group-prepend">
			<span class="input-group-text">첨부 파일</span>
		</div>
		<div class="custom-file">
			<input type="file" name="report" 
				class="custom-file-input" id="file01"/>
			<label class="custom-file-label" for="file01">
			파일을 선택하세요!</label>
		</div>		
	</div> 
	<div class="input-group mb-3">
		<div class="input-group-prepend">
			<span class="input-group-text">첨부 파일</span>
		</div>
		<div class="custom-file">
			<input type="file" name="report" 
				class="custom-file-input" id="file01"/>
			<label class="custom-file-label" for="file01">
			파일을 선택하세요!</label>
		</div>		
	</div> 				
	<div style="text-align:right;">
		<input type="button" class="btn btn-info"
			value="등록" id="regBtn"/>
		<input type="button" class="btn btn-success"
			value="조회 화면으로" id="goMain"/>
	</div>
 	
	</form>
</div>

</body>
</html>