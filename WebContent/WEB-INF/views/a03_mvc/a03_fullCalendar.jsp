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
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="${path}/a00_com/bootstrap.min.css" >
<link rel="stylesheet" href="${path}/a00_com/jquery-ui.css" >
<link href='${path}/a00_com//packages/core/main.css' rel='stylesheet' />
<link href='${path}/a00_com/packages/daygrid/main.css' rel='stylesheet' />
<link href='${path}/a00_com/packages/timegrid/main.css' rel='stylesheet' />
<style>

  body {
    margin: 40px 10px;
    padding: 0;
    font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
    font-size: 14px;
  }

  #calendar {
    max-width: 900px;
    margin: 0 auto;
  }
  input[type=text], select, label,textarea{
  	margin-bottom:12px;padding:.4em;width:95%;
  }
	.input-group-text{width:100%;
		text-align:center;background-color:#cfffdf;color:black;font-weight:bolder;}
	.input-group-prepend{width:35%;}  
</style>
<script src='${path}/a00_com/packages/core/main.js'></script>
<script src='${path}/a00_com/packages/interaction/main.js'></script>
<script src='${path}/a00_com/packages/daygrid/main.js'></script>
<script src='${path}/a00_com/packages/timegrid/main.js'></script>


<script src="${path}/a00_com/jquery.min.js"></script>
<script src="${path}/a00_com/popper.min.js"></script>
<script src="${path}/a00_com/bootstrap.min.js"></script>
<script src="${path}/a00_com/jquery-ui.js"></script>
<script type="text/javascript">
	$(document).ready(function(){

		$("h2").text("fullCalendar");
		var date={};
	    var calendarEl = document.getElementById('calendar');
	    var calendar = new FullCalendar.Calendar(calendarEl, {
	      	plugins: [ 'interaction', 'dayGrid', 'timeGrid' ],
	      	header: {
	        	left: 'prev,next today',
	        	center: 'title',
	        	right: 'dayGridMonth,timeGridWeek,timeGridDay'
	      	},
	      	defaultDate: '2020-05-11',
	      	navLinks: true, // can click day/week names to navigate views
	      	selectable: true,
	      	selectMirror: true,
	      	eventClick:function(info,element){
	    	  // 이벤트 클릭시, 상세 내용 확인..
	    	  // startStr
	    		detail(info);

		    },
	      	eventDrop:function(info){
	      		detail(info);
	      	},
	      	eventDragStop:function(info){
	      		detail(info);
	      	},
	      	eventResize:function(info){
	      		detail(info);
	      	},		    
	      	select: function(arg) {
	      		$("#frm")[0].reset();
	    	  // arg매개변수를 통해, 클릭한 날짜와 시간의 데이터
	    	  //   종일여부 가져오게 처리..
	    	  // 가져다 준다.
	    	  //alert(arg.start+":"+arg.end);
	    	  	console.log("## event의 속성 ##");
	    	  	console.log(arg.end); // 객체가 가지고 있는 속성 표기.
	    	  	date.start=arg.start;
	    	  	date.end=arg.end;
	    		$("[name=start]").val(arg.start.toLocaleString());
	    	  	$("[name=end]").val(arg.end.toLocaleString());
	    	  	$("[name=allDay]").val(""+arg.allDay);
	  	    	diaOpt.buttons={
	  	    		"등록":function(){
	  	    			// 등록 버튼 클릭시, 
	  	    			// 1. 화면, 2. 서버단 DB 등록..
	  	    			//alert("등록처리ajax");
						// 2. ajax로 DB등록
	  	    			//calendar.do?method=insert
	  	    			//alert("등록합니다!");
	  	    			var sch = callSch();
	  	    			// 1. 화면에서 등록..
	  	    			if(sch.title){ // 타이틀 값이 있으면..
	  	    				calendar.addEvent(sch);
	  	    				calendar.render();
	  	    			}
	  	    			
	  	    			
		  	    		$.ajax({
		  	    			type:"post",
		  	    			url:"${path}/calendar.do?method=insert",
		  	    			data:sch, // 요청데이터 전송 json객체형식 가능
		  	    			success:function(){
		  	    				alert("등록 완료!");
		  	    			},
		  	    			error:function(err){
		  	    				console.log(err);
		  	    			}	    				
		  	    		});			    			
	  	    			// 3. 등록후 dialog 초기화.
	  	    			$("#schDialog").dialog("close"); //팝업창 닫기..
	  	    	}
	  	    };   	  
	  	    $("#schDialog").dialog(diaOpt);
	    	$("#schDialog").dialog("open");
	        	calendar.unselect()
	   		},
	    	editable: true,
	    	eventLimit: true, // allow "more" link when too many events
	    	events: function(info, successCallback, failureCallback){
	    		$.ajax({
	    		type:"post",
	    		url:"${path}/calendar.do?method=data",
	    		dataType:"json",
	    		success:function(data){
	    			console.log(data.callist);
	    			successCallback(data.callist);
	    		},
	    		error:function(err){
	    		  	console.log(err);
	    			}
	    		});
	    	}
		});
	    calendar.render();	
	    // dialog 등록 화면 기본 설정.
	    // schDialog frm
	    var diaOpt = {
		   	autoOpen:false,
		   	width:"350px",    		
		   	modal:true	
	    };
	    $("#schDialog").dialog(diaOpt);
	    function callSch(){
			var sch={};
			sch.id=$("[name=id]").val();
			sch.title=$("[name=title]").val();
			
			sch.start=date.start.toISOString();
			sch.end=date.end.toISOString();
			//sch.start=$("[name=start]").val();
			//sch.end=$("[name=end]").val();
			sch.content=$("[name=content]").val();
			sch.color=$("[name=color]").val();
			sch.textColor=$("[name=textColor]").val();
  			// boolean 값이기에 ..
  			sch.allDay=$("[name=allDay]").val()=="true";

  			console.log("##수정###");
  			console.log(sch);
  			return sch;
	    }
		function detail(info){
		  	var event=info.event;
		  	console.log(event);
		  	//alert("상세조회시작:"+event.end);
		  	$("[name=id]").val(event.id);
		  	$("[name=title]").val(event.title);
		  	// content 기본 속성이 아닌 확장 속성.
		  	var extEvt = event.extendedProps;
		  	$("[name=content]").val(extEvt.content);
		  	$("[name=start]").val(event.start.toLocaleString());
		  	date.start = event.start;
	    	var end;
	    	if(event.end==null){
	    	 	end = event.start;
	    	 	//alert("엔드 데이터 없음.");
	    		//end.setDate(end.getDate()+1);
	    		date.end = end;
	    	}else{
	    		date.end=event.end;
	    		end = event.end;
	    	}
		  	$("[name=end]").val(end.toLocaleString());
		  	$("[name=color]").val(event.backgroundColor);	    	
		  	$("[name=textColor]").val(event.textColor);	    	
		  	$("[name=allDay]").val(""+event.allDay);
		  	diaOpt.buttons={
	    		"수정":function(){
	    			//alert("수정합니다!");
	    					// 1) 화면 ui에서 변경..
	    					// 2) AJAX를 통해서 실제 db변경 처리..
					var sch=callSch();	    			      
	    			        // 화면에 변경 처리..
	    			        // 1. calendar.getEventById( id ) : 해당 event 객체 가져오기..
	    			        //    id값으로 calendar객체에 있는 이벤트 중에서 지정된 이벤트를 객체로
	    			        //    가져올 수 있다.
	    		    var event = calendar.getEventById( sch.id );
	    			        // 변경할 속성값 저장..
	    			        // 이벤트객체.setProp("속성",속성값)
	    			        // 이벤트객체.setExtendedProp("속성",속성값) 
	    			       //    확장속성(사용자 정의)
	    		    event.setProp("title",sch.title);
	    		    event.setProp("backgroundColor",sch.color);
	    		    event.setProp("textColor",sch.textColor);
	    		    event.setExtendedProp("content",sch.content);
	    			        // 날짜는 클릭과 스크롤 드랍, 사이즈를 변경하는 순간,
	    			        //  변경이 되게 때문에 입력값으로 가져올 필요가 없다.
	    		    event.setAllDay(sch.allDay);
	    			updateCall();
	    		    $("#schDialog").dialog("close");
	    			        
	    		},
	    		"삭제":function(){
	    		    var id = $("[name=id]").val();
	    		    var event =calendar.getEventById( id );
	    		    event.remove();
	    			$.ajax({
	    		      	type:"post",
	    		        url:"calendar.do?method=delete",
	    		        data:{id:id},
	    		        success:function(data){
	    		        	alert("삭제완료");
	    			    }
	    			});  
	    			$("#schDialog").dialog("close");
	    		}	    	
	    	  			
		  	};   	  
	  	    $("#schDialog").dialog(diaOpt);
	    	$("#schDialog").dialog("open");	  	
		}		
		function updateCall(){
			$.ajax({
		    	type:"post",
		    	url:"calendar.do?method=update",
		    	data:callSch(),
		    	success:function(data){
		    		//if(data=="")
		    		alert("수정완료");
		    	}
		    });	
		}	    
	});
	
</script>
</head>

<body>
<div class="jumbotron text-center">
  <h2></h2>
</div>
<div class="container">
	<div id='calendar'></div>  
	<!-- 
	jquery ui의 dialog plugin 사용..  schDialog frm
	 -->  
	<div id="schDialog" title="일정">
		<form id="frm">
			<input name="id" type="hidden" value="0"/>
			<div class="input-group mb-3">	
				<div class="input-group-prepend">
					<span class="input-group-text">제목</span>
				</div>		
				<input class="form-control"  name="title" type="text"/>
			</div>
			<div class="input-group mb-3">	
				<div class="input-group-prepend">
					<span class="input-group-text">내용</span>
				</div>		
				<textarea rows="5"  class="form-control"  cols="20" name="content"></textarea>
			</div>		
			<div class="input-group mb-3">	
				<div class="input-group-prepend">
					<span class="input-group-text">종일여부</span>
				</div>		
				<select name="allDay"  class="form-control" >
					<option value="true"> 종 일 </option>
					<option value="false"> 시 간 </option>
				</select>	
			</div>
			<div class="input-group mb-3">	
				<div class="input-group-prepend">
					<span class="input-group-text">시작일</span>
				</div>		
				<input class="form-control"  name="start" type="text"/>
			</div>
			<div class="input-group mb-3">	
				<div class="input-group-prepend">
					<span class="input-group-text">종료일</span>
				</div>		
				<input class="form-control"  name="end" type="text"/>
			</div>
			<div class="input-group mb-3">	
				<div class="input-group-prepend">
					<span class="input-group-text">배경색상</span>
				</div>		
				<input name="color"  class="form-control"  type="color" value="#0099cc"/>
			</div>
			<div class="input-group mb-3">	
				<div class="input-group-prepend">
					<span class="input-group-text">글자색상</span>
				</div>		
				<input name="textColor"  class="form-control"  type="color" value="#ccffff"/>
			</div>
		</form>
	</div>
</div>
</body>
</html>