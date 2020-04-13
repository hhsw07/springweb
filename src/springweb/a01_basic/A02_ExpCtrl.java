package springweb.a01_basic;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
// springweb.a01_basic.A02_ExpCtrl
@Controller
public class A02_ExpCtrl {
// ex) A02_ExpCtrl.java		a03_exp.jsp
//	http://localhost:5080/springweb/exp02.do?num01=25&num02=30
//	화면 출력 : 25 + 30 = 50	
	
	@RequestMapping("/exp02.do")
	public String exp02(@RequestParam("num01") int num01, 
						@RequestParam("num02") int num02, Model d) {
		// 1. 요청
		// 2. 모델
		d.addAttribute("sum", num01+num02);
		// 3. view단
		
		return "WEB-INF\\views\\a01_basic\\a03_exp.jsp";
	}
}
