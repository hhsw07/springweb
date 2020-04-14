package springweb.a02_di.z01_vo;

import java.util.ArrayList;

public class DailySchedule {
	private int month;
	private int day;
	private String date;
	private ArrayList<Schedule> slist;
	public DailySchedule() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DailySchedule(int month, int day) {
		super();
		this.month = month;
		this.day = day;
	}
	
	public DailySchedule(String date) {
		super();
		this.date = date;
	}
	public DailySchedule(int month, int day, ArrayList<Schedule> slist) {
		super();
		this.month = month;
		this.day = day;
		this.slist = slist;
	}
	public void showSch() {
		System.out.println("## "+date+" 일정 계획 ##");
		System.out.println("시간\t계획내용");
		for(Schedule sch : slist) {
			sch.plan();
		}
	}
	
	
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public ArrayList<Schedule> getSlist() {
		return slist;
	}
	public void setSlist(ArrayList<Schedule> slist) {
		this.slist = slist;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
}
