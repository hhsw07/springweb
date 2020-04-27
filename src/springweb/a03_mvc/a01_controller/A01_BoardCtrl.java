package springweb.a03_mvc.a01_controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board.do")
public class A01_BoardCtrl {
	// http://localhost:5080/springweb/board.do?method=list
	// http://localhost:5080/springweb/board.do?method=insert
	// http://localhost:5080/springweb/board.do?method=detail
	// http://localhost:5080/springweb/board.do?method=update
	// http://localhost:5080/springweb/board.do?method=delete
	@RequestMapping(params="method=list")
	public String list() {
		return "";
	}
}
