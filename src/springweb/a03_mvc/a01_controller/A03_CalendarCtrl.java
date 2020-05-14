package springweb.a03_mvc.a01_controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import springweb.a03_mvc.a02_service.A03_CalenService;
import springweb.a03_mvc.a04_vo.Calendar;

@Controller
@RequestMapping("/calendar.do")
public class A03_CalendarCtrl {
	@Autowired(required=false)
	private A03_CalenService service;
	
	// 초기 화면 로딩
	// http://localhost:5080/springweb/calendar.do?method=list
	@RequestMapping(params="method=list")
	public String list() {
		return "WEB-INF\\views\\a03_mvc\\a03_fullCalendar.jsp";
	}
	
	// ajax로 호출
	// http://localhost:5080/springweb/calendar.do?method=data
	@RequestMapping(params="method=data")
	public String calData(Model d) {
		d.addAttribute("callist",service.list());
		// pageJsonReport : ArrayList를 json 변환
		// pageJsonReport View가 모델명을 ArrayList형태의 데이터를 jons형식의 데이터로 변경처리.
		// {"callist":[{},{}...]}
		return "pageJsonReport";
	}
	
	// http://localhost:5080/springweb/calendar.do?method=insert
	@RequestMapping(params="method=insert")
	public String calInsert(Calendar ins) {
		service.insertCal(ins);
		
		return "pageJsonReport";
	}
	
}













