package springweb.a03_mvc.a01_controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class A06_MultiLangCtrl {
	
	@RequestMapping("/multi.do")
	// http://localhost:5080/springweb/multi.do
	public String multi() {
		return "WEB-INF\\views\\a03_mvc\\a06_multiLanguage.jsp";
	}
}
