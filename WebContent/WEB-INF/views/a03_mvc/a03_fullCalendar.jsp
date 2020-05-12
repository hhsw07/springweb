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
	    	alert(event.title+":"+event.start.toISOString());
	    	console.log(event); // 이벤트의 모든 속성을 확인할 수 있다.
	      },
	      select: function(arg) {
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
	      },
	      editable: true,
	      eventLimit: true, // allow "more" link when too many events
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
	          title: 'Conference',
	          start: '2020-05-11',
	          end: '2020-05-13'
	        },
	        {
	          title: 'Meeting',
	          start: '2020-05-12T10:30:00',
	          end: '2020-05-12T12:30:00'
	        },
	        {
	          title: 'Lunch',
	          start: '2020-05-12T12:00:00'
	        },
	        {
	          title: 'Meeting',
	          start: '2020-05-12T14:30:00'
	        },
	        {
	          title: 'Happy Hour',
	          start: '2020-05-12T17:30:00'
	        },
	        {
	          title: 'Dinner',
	          start: '2020-05-12T20:00:00'
	        },
	        {
	          title: 'Birthday Party',
	          start: '2020-05-13T07:00:00'
	        },
	        {
	          title: 'Click for Google',
	          url: 'http://google.com/',
	          start: '2020-05-28'
	        }
	      ]
	    });

	    calendar.render();
		
	});
</script>
</head>

<body>
<div class="jumbotron text-center">
  <h2></h2>
</div>
<div class="container">
	<div id='calendar'></div>    
</div>
</body>
</html>