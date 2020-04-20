package springweb.a01_basic;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import springweb.a02_di.z01_vo.Product;

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
	
/* 
/prod.do
물건명: [  ]
가격: [  ]
개수: [  ]
[구매]

/prodShow.do
@@@을 @@개 @@@원 구매하여 총비용이 @@원 입니다.
*/	
	// http://localhost:5080/springweb/prod.do
	@RequestMapping("/prod.do")
	public String prod(Model d) {
		
		return "WEB-INF\\views\\a01_basic\\a10_prodForm.jsp";
	}
	
	
	// http://localhost:5080/springweb/prodShow.do
	// ?name=사과&price=3000&cnt=5
	// Product 클래스의 
	// setName(), setPrice(), setCnt()가 있으면
	// 요청값을 Product 객체에 데이터 할당 처리.
	@RequestMapping("/prodShow.do")
	public String prod(Product p, Model d) {
		System.out.println("물건명: "+p.getName());
		System.out.println("가격: "+p.getPrice());
		System.out.println("개수: "+p.getCnt());
		
		d.addAttribute("tot",p.getPrice()*p.getCnt());
		return "WEB-INF\\views\\a01_basic\\a10_prodForm.jsp";
	}	
	
	
}
