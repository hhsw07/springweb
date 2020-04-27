package springweb.z01_vo;

public class Calcu {
	private int num01;
	private int cal;	// 선택자연산자를 코드값으로 받기.. 0,1,2,3
	private int num02;
	
	
	public Calcu() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int sum() {
		int[] chCal = {num01+num02, num01-num02, num01*num02, num01/(num02==0?1:num02)};
		
		return chCal[cal];
	}
	
	
	
	public int getNum01() {
		return num01;
	}
	public void setNum01(int num01) {
		this.num01 = num01;
	}
	public int getCal() {
		return cal;
	}
	public void setCal(int cal) {
		this.cal = cal;
	}
	public int getNum02() {
		return num02;
	}
	public void setNum02(int num02) {
		this.num02 = num02;
	}
	
}
