package springweb.a01_basic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import springweb.b01_database.A01_Database;
import springweb.z01_vo.Emp;

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
		return "good day!!"+name;	// Response값으로 문자열을 전달
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
	@RequestMapping("/callServer3.do")
	public String callServer3(Model d) {
		d.addAttribute("model01", "모델 데이터입니다.");
		// 서버단에서 클라이언트단에 모델데이터 전달
		return "WEB-INF\\views\\a01_basic\\b01_frontbackExp.jsp";	
	}
	@RequestMapping("/callServer4.do")
	public String callServer4() {
		return "WEB-INF\\views\\a01_basic\\b02_exp.jsp"; // jsp호출
	}
/*	
# Front end 프로그래밍
1. 주로 컴파일과 실행이 브라우저(client)에서 수행 되어진다.
2. javascript, jquery 등 프로그래밍

# Back end 프로그래밍
1. 주로 컴파일과 실행이 서버단(web server)에서 이루어진다.
2. java, jsp 프로그래밍

# Ajax
1. XMLHttpRequest 객체를 통해서 javascript로 서버단과 연결해준다.
2. 비동기식 처리로 서버단에 요청을 한 후, 다른 처리를 하다가, 반응값이 오면 해당 DOM에 대한 변경을 처리할 수 있다.
3. 주로 웝화면에서 전체화면을 변경하여 처리하는 것이 아니라, json형식의 데이터를 반응값으로 받는다.
	ex) json형식 : [{"name":"Hong","age":25}, {"name":"Hong2","age":27}, ] 

# 웹 프로그래밍 처리 방식
1. 페이지 호출과 요청처리
	http://127.3.64.252:8080/springweb/call.do?name=홍길동
	http://127.3.64.252:8080 : 특정 서버의 위치
	/springweb/call.do : 요청되는 자원(page나 controller)
	?name=홍길동 : 해당 자원에 처리할 요청값
	
2. 요청된 서버단 처리..
	/springweb/call.do 연결되는 페이지와 메서드를 찾고,
	?name=홍길동 요청데이터에 따른 처리를 한다.
	
3. 모델 데이터 처리..
	위 요청값(name=홍길동)으로 DB처리, 조건이나 반복문 처리를 통해 Model 데이터를 설정한다.
	key, value(Object)
	
4. 뷰단 처리..
	모델데이터와 html, css, javascript코드를 servlet jsp 엔진으로
	response 객체로 stream 통해 client단에 html결과파일을 전송한다.
	
5. FrontEnd(client)단 전달
	html파일/문자열이 포함된 파일을 전달 받아서, 
	
6. FrontEnd(client)단에서 처리..
	다시, javascript에 선언한 내용대로 수행한다.

# 언제 클라이언트 프로그램을 서버단과 연동할 수 있는가?
1. 서버단의 결과로 client을 만들어서 수행하고자 할 때..
	ex) 넘겨진 모델 데이터를 화면을 로딩함과 함께, javascript 수행하고자 할 때
	1) 실무에서는 로그인해서, 로그인 회원에 대한 메시지 정보 또는
		javascript로 수행했을 때, 처리할 정보를 위와 같은 el과 scriptlet 코드와 js의 변수로 할당하여
		수행한다.
	2) 각종 프로세스 완료 후, 메시지를 보이고자 할 때도 활용된다.
		등록성공, 수정/삭제 완료 등등..

# Ajax는 언제 처리하는가?
1. 화면이 로딩된 상태에서, 다시 화면을 반응객체(response)로
	refresh하지 않고, 서버와 통신을 하여, 서버에 있는 값을 가져오고자 할 때, 활용된다.
	ex) 검색할 때 키워드 입력하는 순간 서버에서 연관 검색어를 검색하여 가져온다.
	1) 동일하게 서버에 대한 요청 프로세스는 수행이 된다.
		ip, port, 자원, 요청값
	2) 반응객체에 대한 차이이다.(response)
		XMLHttpRequest객체에 의해서, 현재 화면의 다른 부분은 그대로 두고,
		서버에 요청을 처리한다.
	3) 일반적인 요청을 url페이지 이동, form의 submit를 통해서
		서버에 요청을 하지만, Ajax는 이벤트를 통해서 XMLHttpRequest객체 하위의
		responseText를 통해서 결과값을 받으면서 처리된다. 
*/
	@Autowired(required=false)
	private A01_Database dao;
	@RequestMapping("/emplist.do") // http://localhost:5080/springweb/emplist.do
	public String empList(Emp sch, Model d) {
		// sch, dao를 모델로 넘겨준다.
		d.addAttribute("emplist", dao.getEmpList(sch));
		
		return "WEB-INF\\views\\z01_data\\z05_emp.jsp";
	}
	
	
}
