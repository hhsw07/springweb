package springweb.a03_mvc.a03_repository;

import java.util.ArrayList;

import springweb.a03_mvc.a04_vo.Calendar;


// springweb.a03_mvc.a03_repository.CalendarDao
// ArrayList<Calendar> list()
public interface CalendarDao {
	public ArrayList<Calendar> list();
	public void insertCal(Calendar ins);
}
