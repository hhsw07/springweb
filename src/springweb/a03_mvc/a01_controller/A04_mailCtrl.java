package springweb.a03_mvc.a01_controller;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import springweb.a03_mvc.a02_service.A04_MailService;
import springweb.a03_mvc.a04_vo.Mail;

@Controller
@RequestMapping("mail.do")
public class A04_mailCtrl {
	@Autowired
	private A04_MailService service;
	
	// 초기 화면
	// http://localhost:5080/springweb/mail.do?method=init
	@RequestMapping(params="method=init")
	public String init() {
		
		return "WEB-INF\\views\\a03_mvc\\a04_mail.jsp";
	}
	
	// 메일발송
	// http://localhost:5080/springweb/mail.do?method=send
	@RequestMapping(params="method=send")
	public String send(Mail send) throws MessagingException {
		System.out.println("subject:"+send.getSubject());
		service.sendMail(send);
		// 메일 전송은 service단에서 처리..
		return "WEB-INF\\views\\a03_mvc\\a04_mail.jsp";
	}
}
