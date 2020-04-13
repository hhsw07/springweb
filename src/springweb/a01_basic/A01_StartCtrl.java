package springweb.a01_basic;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

// springweb.a01_basic.A01_StartCtrl
@Controller
public class A01_StartCtrl {
/*
# controller 사용
1. 선언
2. @controller
3. 메서드별로 요청 url를 선언.
4. 연결되는 view단 선언..
5. container에 등록..
*/
	// http://localhost:5080/springweb/start.do
	@RequestMapping("/start.do")
	public String start() {
		// 1. 요청..
		// 2. model
		// 3. view단 호출..
		return "WEB-INF\\views\\a01_basic\\a01_start.jsp";
	}
	// http://localhost:5080/springweb/start2.do?name=홍길동
	@RequestMapping("/start2.do")
	public String start2(@RequestParam("name") String name, Model d) {
		// 1. 요청..
		System.out.println("요청값: "+name);
		// 2. model
		d.addAttribute("md01", name+"님 안녕하세요");
		// 3. view단 호출..
		return "WEB-INF\\views\\a01_basic\\a02_show.jsp";
	}
// ex) A02_ExpCtrl.java		a03_exp.jsp
//		http://localhost:5080/springweb/exp02.do?num01=25&num02=30
//		화면 출력 : 25 + 30 = 50

// ex) login 화면
//		id, pass 입력 인증 여부 처리.
// 	http://localhost:5080/springweb/login.do
// 	http://localhost:5080/springweb/login.do?id=&pass=
	
}
