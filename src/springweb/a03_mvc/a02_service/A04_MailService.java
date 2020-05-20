package springweb.a03_mvc.a02_service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import springweb.a03_mvc.a04_vo.Mail;

@Service
public class A04_MailService {
	// 메일 모듈 호출..
	@Autowired(required=false)
	private JavaMailSender sender;
	
	public void sendMail(Mail email) throws MessagingException {
		// 1. 멀티미디어형 메일 데이터 전송.
		MimeMessage msg = sender.createMimeMessage();
		// 2. 제목 설정.
		msg.setSubject(email.getSubject());
		// 3. 수신자 설정.
		msg.setRecipient(RecipientType.TO, 
				new InternetAddress(email.getReceiver()));
		// 4. 내용 설정.
		msg.setText(email.getContent());
		// 5. 발송 처리.
		sender.send(msg);
	}
}
