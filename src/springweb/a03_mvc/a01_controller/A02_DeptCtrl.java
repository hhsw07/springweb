package springweb.a03_mvc.a01_controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import springweb.a03_mvc.a02_service.A02_DeptService;
import springweb.a03_mvc.a04_vo.Dept;

@Controller
@RequestMapping("/dept.do")
public class A02_DeptCtrl {
	@Autowired(required=false)
	private A02_DeptService service;
	// http://localhost:5080/springweb/dept.do?Method=list
	@RequestMapping(params="Method=list")
	public String list(Dept sch, Model d) {
		d.addAttribute("deptlist", service.deptList(sch));
		return "WEB-INF\\views\\a03_mvc\\a02_dept.jsp";
	}
}
