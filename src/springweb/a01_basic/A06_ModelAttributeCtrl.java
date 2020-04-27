package springweb.a01_basic;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import springweb.a02_di.z01_vo.Product;
import springweb.z01_vo.Calcu;
import springweb.z01_vo.Code;
import springweb.z01_vo.Member;

@Controller
public class A06_ModelAttributeCtrl {
/*
# ModelAttribute
1. 요청값과 모델데이터를 함께 처리해준다.
2. controller에서 공통적인 모델 선엉ㄴ을 할 수 있다.

*/
	@ModelAttribute("mem")
	public Member getMember() {
		return new Member("himan","7777");
	}
	@ModelAttribute("sel01")
	public ArrayList<Code> getCode() {
		ArrayList<Code> sel = new ArrayList<Code>();
		sel.add(new Code("fruit1","사과"));
		sel.add(new Code("fruit2","바나나"));
		sel.add(new Code("fruit3","딸기"));
		return sel;
	}
	
	// http://localhost:5080/springweb/mdatter.do
	@RequestMapping(value="/mdatter.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String form(@ModelAttribute("p01") Product p) {
		
		return "WEB-INF\\views\\a01_basic\\a14_productInfo.jsp";
	}
/*	
ex) 수학 문제
숫자1:[ ]
숫자2:[ ]
연산식:[select +,-,*,/ ]
선택했을 때, submit 처리 되어,
결과 @@ + @@ = @@
*/
/*
# Controller
1) 요청 처리.
	변수, 객체 setXXX
2) Model 처리
3) 요청+Model : ModelAttribute
4) View
5) Model+View : ModelAttribute

	
	
 */
	@ModelAttribute("calList")
	public ArrayList<Code> callist() {
		ArrayList<Code> clist = new ArrayList<Code>();
		clist.add(new Code("0","+"));
		clist.add(new Code("1","-"));
		clist.add(new Code("2","*"));
		clist.add(new Code("3","/"));
		return clist;
	}
	// http://localhost:5080/springweb/cal.do
	// 요청과 model 같이 처리하는 Calcu 객체 선언
	@RequestMapping(value="/cal.do",method= {RequestMethod.GET,RequestMethod.POST})
	public String calcu(@ModelAttribute("calcu") Calcu c) {
	
	return "WEB-INF\\views\\a01_basic\\a15_calcu.jsp";
	}
	
}
