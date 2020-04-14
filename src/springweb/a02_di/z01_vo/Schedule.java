package springweb.a02_di.z01_vo;

public class Schedule {
	private String time;
	private String content;
	public Schedule() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Schedule(String time, String content) {
		super();
		this.time = time;
		this.content = content;
	}
	public void plan() {
		System.out.println(time+"\t"+content);
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
