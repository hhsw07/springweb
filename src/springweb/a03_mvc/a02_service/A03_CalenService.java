package springweb.a03_mvc.a02_service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springweb.a03_mvc.a03_repository.CalendarDao;
import springweb.a03_mvc.a04_vo.Calendar;

@Service
public class A03_CalenService {
	@Autowired(required = false)
	private CalendarDao dao;
	
	public ArrayList<Calendar> list(){
		return dao.list();
	}
	public void insertCal(Calendar ins) {
		dao.insertCal(ins);
	}
	public void update(Calendar upt) {
		dao.update(upt);
	}
	public void delete(int id) {
		dao.delete(id);
	}	
}
