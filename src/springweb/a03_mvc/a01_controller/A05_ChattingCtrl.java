package springweb.a03_mvc.a01_controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class A05_ChattingCtrl {
	
	// http://localhost:5080/springweb/chatting.do
	@RequestMapping("/chatting.do")
	public String chatting() {
		return "WEB-INF\\views\\a03_mvc\\a05_chatting.jsp";
	}
	// http://localhost:5080/springweb/chatting2.do
	@RequestMapping("/chatting2.do")
	public String chatting2() {
		return "WEB-INF\\views\\a03_mvc\\a05_chatting2.jsp";
	}
}
