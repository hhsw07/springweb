package springweb.a03_mvc.a01_controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/calendar.do")
public class A03_CalendarCtrl {
	
	// 초기 화면 로딩
	// http://localhost:5080/springweb/calendar.do?method=list
	@RequestMapping(params="method=list")
	public String list() {
		return "WEB-INF\\views\\a03_mvc\\a03_fullCalendar.jsp";
	}
	
	
}
