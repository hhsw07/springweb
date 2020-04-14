package springweb.a02_di.z01_vo;

import org.springframework.beans.factory.annotation.Autowired;

public class AnnoMan {
	private String name;
	// source로 autowire선언으로 
	// container(@.xml)에 같은 type이 있으면 할당처리.
	@Autowired
	private Woman woman;
	public AnnoMan() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void show() {
		System.out.println("이름은 "+name);
		if(woman != null) {
			woman.info();
		}
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Woman getWoman() {
		return woman;
	}
	public void setWoman(Woman woman) {
		this.woman = woman;
	}
	
}
