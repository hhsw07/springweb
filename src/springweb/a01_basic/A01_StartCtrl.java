package springweb.a01_basic;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import springweb.a02_di.z01_vo.Person;
import springweb.a02_di.z01_vo.Product;

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
/*
ex) login 화면 id, pass 입력 인증 여부 처리. 
http://localhost:5080/springweb/login.do
http://localhost:5080/springweb/login.do?id=&pass=
 */
/*
# 요청값을 객체로 처리하기..
1. 스프링에서는 요청값을 useBean과 같이 setXXX로 요청값을 받아서 처리할 수 있다.
	?name=홍길동&age=25&loc=서울신림동
	요청을 받은 매개변수로 VO객체를 선언하고,
	VO객체에 setName(), setAge(), setLoc()가 있으면, 요청값으로 해당 객체의 field(속성)에 할당할 수 있다.
*/
	// http://localhost:5080/springweb/personVo.do?name=홍길동&age=25&loc=서울신림동
	@RequestMapping("/personVo.do")
	public String callPerson(Person p, Model d) {
		System.out.println("요청값1: "+p.getName());
		System.out.println("요청값2: "+p.getAge());
		System.out.println("요청값3: "+p.getLoc());
		d.addAttribute("p01",p);
		return "WEB-INF\\views\\a01_basic\\a06_reqObjVal.jsp";
	}
/*	
# 요청값과 모델 화면단 처리의 분리
1. 스프링의 요청값
	1) @RequestParam("요청key") String val
	2) 요청값을 처리해주는 객체 :
		Person p : 요청 key와 mapping되는 method가 있을 때
		setXXX, setKey 객체에 데이터를 할당 처리.
2. 모델데이터 처리
	Model d
	d.addAttribute("모델key",모델객체);
3. 화면단 호출..
	${모델key}


*/
}
