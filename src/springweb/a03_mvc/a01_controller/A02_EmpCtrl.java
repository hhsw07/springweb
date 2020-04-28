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
}
