package springweb.a03_mvc.a01_controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import springweb.a03_mvc.a02_service.A01_BoardService;
import springweb.a03_mvc.a04_vo.Board;

@Controller
@RequestMapping("/board.do")
public class A01_BoardCtrl {
	// http://localhost:5080/springweb/board.do?method=list
	// http://localhost:5080/springweb/board.do?method=insert
	// http://localhost:5080/springweb/board.do?method=detail
	// http://localhost:5080/springweb/board.do?method=update
	// http://localhost:5080/springweb/board.do?method=delete
	@Autowired(required=false)
	private A01_BoardService service;
	@RequestMapping(params="method=list")
	public String list(Board sch, Model d) {
		d.addAttribute("blist", service.list(sch));
		return "WEB-INF\\views\\a03_mvc\\a01_boardList.jsp";
	}
	@RequestMapping(params="method=insForm")
	public String insertForm() {
		return "WEB-INF\\views\\a03_mvc\\a01_boardInsert.jsp";
	}
	@RequestMapping(params="method=insert")
	public String insert(Board insert) {
		System.out.println("등록 제목:"+insert.getTitle());
		service.insert(insert);
		
		return "WEB-INF\\views\\a03_mvc\\a01_boardInsert.jsp";
	}
	

}
