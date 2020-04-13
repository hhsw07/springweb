package springweb.a02_di.z01_vo;

public class Mart {
	private String name;
	private Product prod;
	public Mart() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Mart(String name, Product prod) {
		super();
		this.name = name;
		this.prod = prod;
	}
	public void show() {
		System.out.println("마트이름: "+name);
		if(prod != null) {
			System.out.println("물건명: "+prod.getPname());
			System.out.println("가격: "+prod.getPrice());
			System.out.println("수량: "+prod.getCnt());
		}
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Product getProd() {
		return prod;
	}
	public void setProd(Product prod) {
		this.prod = prod;
	}
	
	
}
