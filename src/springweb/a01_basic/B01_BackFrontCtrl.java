package springweb.a01_basic;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class B01_BackFrontCtrl {
	// http://localhost:5080/springweb/callServer.do
	// 1. 서버에 대한 요청(client단 프로그램-브라우저)
	//	  1) 서버에 어떤 자원(서버에 있는 controller단, jsp...)
	//		  특정 controller에 메서드를 호출..
	//	  2) 요청값(get/post) callServer.do?name=홍길동
	// 2. 서버는 위 요청에 합당한 자원과 받은 요청값을 처리한다.
	@ResponseBody
	@RequestMapping("/callServer.do")
	public String callServer(@RequestParam("name") String name) {
		System.out.println("요청값1(name): "+name);
	// 3. client에 넘겨줄 화면단인 jsp를 호출// 문자열도 전달..
		return "good day!!";	// Response값으로 문자열을 전달
	}
	
	// http://localhost:5080/springweb/callServer2.do
	@RequestMapping("/callServer2.do")
	public String callServer2() {
	// 3. client에 넘겨줄 화면단인 jsp를 호출..
		return "WEB-INF\\views\\a01_basic\\b01_frontbackExp.jsp"; // jsp호출 = html을 만들어 클라이언트단에 전달
	}
/*
ex) callserver3.do
	himan을 문자로 출력
	callserver4.do
	b02_exp.jsp
*/
	@ResponseBody
	@RequestMapping("/callServer3.do")
	public String callServer3() {
		return "himan";	// 서버단에서 클라이언트단에 문자열을 전달
	}
	@RequestMapping("/callServer4.do")
	public String callServer4() {
		return "WEB-INF\\views\\a01_basic\\b02_exp.jsp"; // jsp호출
	}
	
	
}
