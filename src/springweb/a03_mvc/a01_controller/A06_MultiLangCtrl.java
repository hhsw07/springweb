package springweb.a03_mvc.a01_controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;

@Controller
public class A06_MultiLangCtrl {
	@Autowired(required = false)
	private LocaleResolver localeResolver;
	
	
	// http://localhost:5080/springweb/multi.do
	@RequestMapping("/multi.do")
	public String multi() {
		return "WEB-INF\\views\\a03_mvc\\a06_multiLanguage.jsp";
	}
	
	// http://localhost:5080/springweb/choiceLan.do
	@RequestMapping("/choiceLan.do")
	public String choiceLan(@RequestParam("lang") String lang,
			HttpServletRequest request, HttpServletResponse responce) {
		System.out.println("선택한 언어:"+lang);
		Locale locale = new Locale(lang);
		localeResolver.setLocale(request, responce, locale);
		
		return "WEB-INF\\views\\a03_mvc\\a06_multiLanguage.jsp";
	}
}
