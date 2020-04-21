package springweb.z01_vo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

// springweb.z01_vo.Member
@Component
public class Member {
	@Value("${id}")
	private String id;
	@Value("${pass}")
	private String pass;
	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Member(String id, String pass) {
		super();
		this.id = id;
		this.pass = pass;
	}

	

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	
}
