package springweb.a02_di.z01_vo;

public class Man {
	private String name;
	private Woman woman;
	
	public Man() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Man(String name, Woman woman) {
		super();
		this.name = name;
		this.woman = woman;
	}
	
	public void show() {
		System.out.println("이름: "+name);
		if(woman != null) {
			System.out.println("## 그녀는 ##");
			System.out.println("이름: "+woman.getName());
			System.out.println("나이: "+woman.getAge());
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
	// 객체 메모리에 객체가 할당되는 메서드 선언
	public void setWoman(Woman woman) {
		this.woman = woman;
	}
	
}
