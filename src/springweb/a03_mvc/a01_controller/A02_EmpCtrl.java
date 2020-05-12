package springweb.a03_mvc.a01_controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import springweb.a03_mvc.a02_service.A02_EmpService;
import springweb.a03_mvc.a04_vo.Emp;

@Controller
@RequestMapping("/emp.do")
public class A02_EmpCtrl {
	@Autowired(required=false)
	private A02_EmpService service;
	// http://localhost:5080/springweb/emp.do?Method=list
	@RequestMapping(params="Method=list")
	public String list(Emp sch, Model d) {
		d.addAttribute("elist", service.empList(sch));
		return "WEB-INF\\views\\a03_mvc\\a02_emp.jsp";
	}
	
	
	// http://localhost:5080/springweb/emp.do?Method=ajaxForm
	@RequestMapping(params="Method=ajaxForm")
	public String ajaxForm() {
		return "WEB-INF\\views\\a03_mvc\\a03_emp.jsp";
	}
	// http://localhost:5080/springweb/emp.do?Method=ajaxList
	@RequestMapping(params="Method=ajaxList")
	public String ajaxlist(Emp sch, Model d) {
		// view를 json형 뷰로 선언.
		d.addAttribute("elist", service.empList(sch));
		// 모델에 있는 elist로 된 ArrayList 객체를
		// json 형식으로 변경 {"elist":[{"empno":"7788","ename":"홍길동"...}]}
		return "pageJsonReport";
	}
	
	
}
