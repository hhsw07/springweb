package springweb.a01_basic;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import springweb.a02_di.z01_vo.Code;
import springweb.a02_di.z01_vo.Member;
import springweb.a02_di.z01_vo.Product;

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
	@ModelAttribute("sign")
	public ArrayList<Code> getSign() {
		ArrayList<Code> sign = new ArrayList<Code>();
		sign.add(new Code("plus","+"));
		sign.add(new Code("minus","-"));
		sign.add(new Code("multiple","*"));
		sign.add(new Code("divide","/"));
		return sign;
	}
	// http://localhost:5080/springweb/numExp.do
	@RequestMapping("/numExp.do")
	public String mathExp(@RequestParam(value="num01",defaultValue="0") int num01,
						  @RequestParam(value="num02",defaultValue="0") int num02
						  ) {
		System.out.println("num01:"+num01);
		System.out.println("num02:"+num02);
		return "WEB-INF\\views\\a01_basic\\a15_mathExp.jsp";
	}
	
}
