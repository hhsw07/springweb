package springweb.a02_di.z01_vo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

// springweb.a02_di.z01_vo.Product
//@Component
public class Product {
	@Value("${pname}")
	private String name;
	@Value("${price}")
	private int price;
	@Value("${cnt}")
	private int cnt;
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Product(String name, int price, int cnt) {
		super();
		this.name = name;
		this.price = price;
		this.cnt = cnt;
	}
	
	public void info() {
		System.out.println("물건명: "+name);
		System.out.println("가격: "+price);
		System.out.println("개수: "+cnt);
	}
	
	/**
	 * @return the pname
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param pname the pname to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}
	/**
	 * @return the cnt
	 */
	public int getCnt() {
		return cnt;
	}
	/**
	 * @param cnt the cnt to set
	 */
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	
	
}
