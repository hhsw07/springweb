package springweb.a02_di.z01_vo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//@Component
public class Woman {
	// file로 key=value에서 key값으로 해당 변수로
	// mapping하여 바로 처리할 수 있다.
	@Value("${name2}")
	private String name;
	@Value("${age}")
	private int age;
	public Woman() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Woman(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	public void info() {
		System.out.println("이름: "+name);
		System.out.println("나이: "+age);
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}
	
}
