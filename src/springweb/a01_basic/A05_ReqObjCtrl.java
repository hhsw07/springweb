package springweb.a01_basic;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import springweb.z01_vo.Member;
// springweb.a01_basic.A05_ReqObjCtrl
@Controller
public class A05_ReqObjCtrl {
// 1. 요청값의 default 처리..
//	reqexp01.do?greet1=안녕 : 요청값이 있을 때는 요청값을 처리
//	reqexp01.do?X : 요청값이 없을 때, default 문자열 처리..
	
	// http://localhost:5080/springweb/reqexp01.do
	@RequestMapping("reqexp01.do")
	public String reqexp01(@RequestParam(value="greet1",
										defaultValue="hello") String greet01,
						   @RequestParam(value="num01",defaultValue="-1") int num
							) {
		System.out.println("인사말: "+greet01);
		System.out.println("숫자: "+num);
		return "WEB-INF\\views\\a01_basic\\a11_reqExp.jsp";
	}

// 2. 요청의 get/post 방식 처리
//	url호출 요청값 ?name=@@&age=@@ : get방식, 전달값 제한, 보안상
//	페이지 호출하고 form method="post" 요청값 전달 body에서 전달 post 방식
//	같은 url이더라도 get방식과 post방식으로 요청처리가 있다.
	// 초기화면 호출시
	// http://localhost:5080/springweb/calling.do
	@RequestMapping(value="/calling.do", method=RequestMethod.GET)
	public String calling1() {
		System.out.println("GET방식");
		return "WEB-INF\\views\\a01_basic\\a12_methodForm.jsp";
	}
	// form method="post" 요청값 처리..
	@RequestMapping(value="/calling.do", method=RequestMethod.POST)
	public String calling2() {
		System.out.println("POST방식");
		return "WEB-INF\\views\\a01_basic\\a12_methodForm.jsp";
	}
	@RequestMapping(value="/calling2.do", 
					method= {RequestMethod.GET,RequestMethod.POST})
	public String calling3() {
		System.out.println("GET/POST방식 동시처리");
		return "WEB-INF\\views\\a01_basic\\a12_methodForm.jsp";
	}
/*
ex) login 화면을 get/post를 같이 처리하는 메서드를 선언하여, id/pass 인증 여부를 처리하세요.
*/
	// http://localhost:5080/springweb/login2.do
	@RequestMapping(value="/login2.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String login(Member m, Model d) {
		System.out.println("아이디: "+ m.getId());
		System.out.println("비밀번호: "+ m.getPass());
		if(m.getId() != null&&!m.getId().equals("")) {
			if(m.getId().equals("himan")&&m.getPass().equals("7777")) {
				d.addAttribute("valid", true);
			}else {
				d.addAttribute("valid", false);
			}
		}
			
		return "WEB-INF\\views\\a01_basic\\a13_loginExp.jsp";
	}
}
