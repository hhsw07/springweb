package springweb.a01_basic;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

// springweb.a01_basic.A04_ProductCtrl
@Controller
public class A04_ProductCtrl {
	
	// http://localhost:5080/springweb/buy.do
	@RequestMapping("buy.do")
	public String buy() {
		return "WEB-INF\\views\\a01_basic\\a05_buy_product.jsp";
	}
	
	// http://localhost:5080/springweb/buyPrc.do
	// ?name=@@@&price=@@@&cnt=@@
	@RequestMapping("buyPrc.do")
	public String buyPrc(@RequestParam("pname") String pname,
					  @RequestParam("price") int price,
					  @RequestParam("cnt") int cnt,
					  Model d) {
		
		String result = pname+"를 "+cnt+"개 구매하여 총 비용이 "+(price*cnt)+"원 입니다.";
		
		d.addAttribute("result", result);
		return "WEB-INF\\views\\a01_basic\\a05_buy_product.jsp";
	}
	
}
