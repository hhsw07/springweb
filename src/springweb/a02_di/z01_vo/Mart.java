package springweb.a02_di.z01_vo;

import java.util.ArrayList;

// 1:다 관계의 클래스 선언..
public class Mart {
	private String name;
	private ArrayList<Product> plist;
	private Product prod;
	
	public Mart() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Mart(String name) {
		super();
		this.name = name;
	}
	public Mart(String name, ArrayList<Product> plist) {
		super();
		this.name = name;
		this.plist = plist;
	}
	public void buyOne() {
		System.out.println("### "+name+"에서 구매한 물건 ###");
		if(prod!=null) {
			System.out.println("물건명\t가격\t개수");
			System.out.print(prod.getName()+"\t");
			System.out.print(prod.getPrice()+"\t");
			System.out.print(prod.getCnt()+"\n");
		}
	}
	public void buyList() {
		System.out.println("### "+name+"에서 구매한 물건 ###");
		if(plist != null) {
			System.out.println("물건명\t가격\t개수");
			for(Product p : plist) {
				System.out.print(p.getName()+"\t");
				System.out.print(p.getPrice()+"\t");
				System.out.print(p.getCnt()+"\n");
			}
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
	public ArrayList<Product> getPlist() {
		return plist;
	}
	
	/*
	DI에서는
	plist.add(new Product());
	plist.add(new Product());
	plist.add(new Product());
	...
	*/
	public void setPlist(ArrayList<Product> plist) {
		this.plist = plist;
	}
	
	
	
	
}
