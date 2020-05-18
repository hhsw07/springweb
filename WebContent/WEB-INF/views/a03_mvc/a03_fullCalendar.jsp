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
<!-- fullCalendar -->
<link href='${path}/a00_com/packages/core/main.css' rel='stylesheet' />
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
  input[type=text], select, label, textarea{
  	margin-bottem:12px; padding:.4em; width:95%;
  }
  .input-group-text{width:100%;
  		text-align:center;background-color:#cfffdf;color:black;font-weight:bolder;}
  .input-group-prepend{width:35%;}  
</style>
<link href='${path}/a00_com/packages/daygrid/main.css' rel='stylesheet' />
<link href='${path}/a00_com/packages/timegrid/main.css' rel='stylesheet' />
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
		<%-- 
		
		--%>
		$("h2").text("fullCalendar");
		var calendarEl = document.getElementById('calendar');

	    var calendar = new FullCalendar.Calendar(calendarEl, {
	      plugins: [ 'interaction', 'dayGrid', 'timeGrid' ],
	      header: {
	        left: 'prev,next today',
	        center: 'title',
	        right: 'dayGridMonth,timeGridWeek,timeGridDay'
	      },
	      defaultDate: '2020-05-12',
	      navLinks: true, // can click day/week names to navigate views
	      selectable: true,
	      selectMirror: true,
	      eventClick:function(info,element){
	    	// 이벤트 클릭시, 상세 내용 확인..
	    	var event = info.event;
	    	// alert(event.title+":"+event.start.toISOString());
	    	console.log(event); // 이벤트의 모든 속성을 확인할 수 있다.
	    						// 확인 후 필요한 속성을 사용한다.
	    	// detSchFrm, detfrm
	    	$("#detScheFrm [name=id]").val(event.id);
	    	$("#detScheFrm [name=title]").val(event.title);
	    	// content 기본 속성이 아닌 확장 속성.
	    	var extEvt = event.extendedProps;
	    	$("#detScheFrm [name=content]").val(extEvt.content);
	    	$("#detScheFrm [name=start]").val(event.start.toISOString());
	    	$("#detScheFrm [name=end]").val(event.end.toISOString());
	    	$("#detScheFrm [name=color]").val(event.backgroundColor);
	    	$("#detScheFrm [name=textColor]").val(event.textColor);
	    	$("#detScheFrm [name=allDay]").val(""+event.allDay);
	    	
	    	$("#detScheFrm").dialog("open");
	    	
	      },
	      select: function(arg) {
	    	// arg매개변수를 통해, 클릭한 날짜와 시간의 데이터, 종일여부 가져오게 처리..
	    	// alert("시작일:"+arg.start+" 마지막일:"+arg.end);
	    	console.log("## event의 속성 ##");
	    	console.log(arg); // 객체가 가지고 있는 속성 표기.
	    	$("#inScheFrm [name=start]").val(arg.start.toISOString());
	    	$("#inScheFrm [name=end]").val(arg.end.toISOString());
	    	$("#inScheFrm [name=allDay]").val(""+arg.allDay);
	    	$("#inScheFrm").dialog("open");
	    	
	    	/*
	    	// 선택해서 등록 처리.
	        // arg 매개 객체로, 
	        // 시작일자와 시간 start, end
	        // 종일 여부를 가져온다.
	    	// alert("allDay:"+arg.allDay); // 종일여부:(하루단위:ture, 시간단위:false)
	    	var title = prompt('Event Title:');
	        // 제목을 입력받아서..
	        if (title) {
	          // calendar 메인 객체에 객체단위로,
	          // 일정을 등록 처리.
	          calendar.addEvent({
	            title: title,
	            start: arg.start,
	            end: arg.end,
	            allDay: arg.allDay
	          })
	        }
	        calendar.unselect()
	        */
	      },
	      editable: true,
	      eventLimit: true, // allow "more" link when too many events
	      /* fullCalendar 기본 데이터
	      events: [
	    	// 일정 리스트 내용을 대 한 부분을 json 데이터 처리.
	    	// title, start, end, groupId, url
	    	// color, textColor, allDay(하루단위:ture, 시간단위:false)
	        {
	          title: 'All Day Event',
	          start: '2020-05-01',
	          color: 'red',
	          textColor: 'yellow'
	        },
	        {
	          title: 'Long Event',
	          start: '2020-05-07',
	          end: '2020-05-10'
	        },
	        {
	          groupId: 999,
	          title: 'Repeating Event',
	          start: '2020-05-09T16:00:00'
	        },
	        {
	          groupId: 999,
	          title: 'Repeating Event',
	          start: '2020-05-16T16:00:00'
	        },
	        {
	          title: 'Meeting',
	          start: '2020-05-12T10:30:00',
	          end: '2020-05-12T12:30:00'
	        },
	        {
	          title: 'Click for Google',
	          url: 'http://google.com/',
	          start: '2020-05-28'
	        }
	      ]
	      */
	      events : function(info, successCallback, failureCallback){
	    	  // function succexxCallback(데이터){}
	    	  // 	json데이터를 화면에 리스트하는 함수.
	    	  // function(info, 함수1, 함수2)
	    	  
	    	  
	    	  // 서버에서 비동기 통신으로 데이터 가져오기.
	    	  // localhost:5080/springweb/calendar.do?method=data
	    	  $.ajax({
	    		  type:"post",
	    		  url:"${path}/calendar.do?method=data",
	    		  dataType:"json",
	    		  success:function(data){
	    			  // alert("성공"+data.callist.length);
	    			  console.log(data.callist);
	    			  // 함수의 기능 내용을 처리
	    			  // json 객체의 내용을 리스트 처리.
	    			  successCallback(data.callist);
	    		  },
	    		  error:function(err){
	    			  alert("에러");
	    			  console.log(err);
	    		  }
	    		  
	    	  });
	      }
	    });

	    calendar.render();
		
	    // dialog 등록화면
	    $("#inScheFrm").dialog({
	    	autoOpen:false,
	    	buttons:{
	    		"등록":function(){
	    			// 등록 버튼 클릭시,
	    			// 1. 화면등록, 2. 서버단 DB등록
	    			//alert("등록처리 & ajax");
	    			var addSch = {};
	    			addSch.title = $("#inScheFrm [name=title]").val();
	    			addSch.start = $("#inScheFrm [name=start]").val();
	    			addSch.end = $("#inScheFrm [name=end]").val();
	    			addSch.content = $("#inScheFrm [name=content]").val();
	    			addSch.color = $("#inScheFrm [name=color]").val();
	    			addSch.textColor = $("#inScheFrm [name=textColor]").val();
	    			// boolean 값으로 넣어준다.
	    			addSch.allDay = $("#inScheFrm [name=allDay]").val()=="true";
	    			// 1. 화면에서 등록..
	    			if(addSch.title){ // 타이틀값이 있으면..
	    				calendar.addEvent(addSch);
	    			}
	    			// 2. ajax로 DB등록
	    			// calendar.do?method=insert
	    			$.ajax({
	    				type:"post",
	    				url:"${path}/calendar.do?method=insert",
	    				data:addSch, // 요청데이터 전송 json객체형식 가능
	    				success:function(){
	    					alert("등록완료!");
	    				},
	    				error:function(err){
	    					alert("에러");
	    					console.log(err)
	    				}
	    			});
	    					
	    			// 3. 등록 후 dialog 초기화
	    			// $("#inScheFrm #inputfrm").reset();
	    			$("#inScheFrm").dialog("close");
	    		}
	    	},
	    	modal:true
	    });
	    
	 	// dialog 상세화면
	 	// detSchFrm, detfrm
	    $("#detScheFrm").dialog({
	    	autoOpen:false,
	    	buttons:{
	    		"삭제":function(){
	    			
	    		},
	    		"수정":function(){
	    			// 등록 버튼 클릭시,
	    			// 1. 화면등록, 2. 서버단 DB등록
	    			//alert("등록처리 & ajax");
	    			var addSch = {};
	    			addSch.title = $("#inScheFrm [name=title]").val();
	    			addSch.start = $("#inScheFrm [name=start]").val();
	    			addSch.end = $("#inScheFrm [name=end]").val();
	    			addSch.content = $("#inScheFrm [name=content]").val();
	    			addSch.color = $("#inScheFrm [name=color]").val();
	    			addSch.textColor = $("#inScheFrm [name=textColor]").val();
	    			// boolean 값으로 넣어준다.
	    			addSch.allDay = $("#inScheFrm [name=allDay]").val()=="true";
	    			// 1. 화면에서 등록..
	    			if(addSch.title){ // 타이틀값이 있으면..
	    				calendar.addEvent(addSch);
	    			}
	    			// 2. ajax로 DB등록
	    			// calendar.do?method=insert
	    			$.ajax({
	    				type:"post",
	    				url:"${path}/calendar.do?method=insert",
	    				data:addSch, // 요청데이터 전송 json객체형식 가능
	    				success:function(){
	    					alert("등록완료!");
	    				},
	    				error:function(err){
	    					alert("에러");
	    					console.log(err)
	    				}
	    			});
	    					
	    			// 3. 등록 후 dialog 초기화
	    			// $("#inScheFrm #inputfrm").reset();
	    			$("#inScheFrm").dialog("close");
	    		}
	    	},
	    	modal:true
	    });
	});
	
</script>
</head>

<body>
<div class="jumbotron text-center">
  <h2></h2>
</div>
<div class="container">
	<div id='calendar'></div>    
	<div id="inScheFrm" title="일정등록">
		<form id="inputfrm">
			<div class="input-group mb-3">	
				<div class="input-group-prepend">
					<span class="input-group-text">제목</span>
				</div>		
				<input id="title"  class="form-control"  name="title" type="text"/>
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
					<span class="input-group-text">시작일(ISO)</span>
				</div>		
				<input class="form-control"  name="start" type="text"/>
			</div>
			<div class="input-group mb-3">	
				<div class="input-group-prepend">
					<span class="input-group-text">종료일(ISO)</span>
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
	<div id="detScheFrm" title="일정상세">
		<form id="detfrm">
		<input type="hidden" name="id" />
			<div class="input-group mb-3">	
				<div class="input-group-prepend">
					<span class="input-group-text">제목</span>
				</div>		
				<input id="title"  class="form-control"  name="title" type="text"/>
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
					<span class="input-group-text">시작일(ISO)</span>
				</div>		
				<input class="form-control"  name="start" type="text"/>
			</div>
			<div class="input-group mb-3">	
				<div class="input-group-prepend">
					<span class="input-group-text">종료일(ISO)</span>
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
<!-- 
# dialogue 처리.
1. form
2. jquery 호출..
-->

</body>
</html>