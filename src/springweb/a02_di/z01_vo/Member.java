package springweb.a02_di.z01_vo;

public class Member {
	private String id;
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
