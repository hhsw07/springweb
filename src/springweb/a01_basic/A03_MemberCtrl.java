package springweb.a01_basic;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*
# Controller 작성..
1. 사용자 정의 Controller class 작성.
	- @Controller 선언
2. dispatcher-servlet.xml에 Controller 클래스 등록.
	- <bean class="" />
3. 요청 url 정리
	- http://localhost:5080/springweb/login.do
4. 메서드 선언
	- 요청 url의 pattern에 맞는 @RequestMapping("login.do")
	- 연결되는 메서드 선언 
		public String login(){
			return "";
		}
	- 해당 url을 통해서 호출되는 jsp 화면단 파일 생성과 return 값으로 정의
		springweb\WebContent\WEB-INF\views\a01_basic\a04_login.jsp
		return "springweb\WebContent\WEB-INF\views\a01_basic\a04_login.jsp";
	- 요청값 매개변수 선언
	- Model 데이터 선언
	- Model 데이터 할당
5. 화면구현.a04_login.jsp

	
	
*/
// springweb.a01_basic.A03_MemberCtrl
@Controller
public class A03_MemberCtrl {
	// ex) login 화면
	//	id, pass 입력 인증 여부 처리.
	// http://localhost:5080/springweb/login.do
	@RequestMapping("login.do")
	public String login() {
		return "WEB-INF\\views\\a01_basic\\a04_login.jsp";
	}
	// http://localhost:5080/springweb/loginPrc.do
	// ?id=@@@&pass=@@@
	@RequestMapping("/loginPrc.do")
	public String loginPrc(@RequestParam("id") String id,
						   @RequestParam("pass") String pass,
						   Model d){
		// 로그인 경과 처리 1. 요청  2. 모델  3. view
		String result = "로그인 실패";
		if(id.equals("himan")&&pass.equals("7777")) {
			result = id+"님 로그인 성공";
		}
		d.addAttribute("result", result);
		return "WEB-INF\\views\\a01_basic\\a04_login.jsp";
	}
	
}
