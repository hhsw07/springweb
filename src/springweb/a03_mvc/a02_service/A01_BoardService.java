package springweb.a03_mvc.a02_service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springweb.a03_mvc.a03_repository.BoardDao;
import springweb.a03_mvc.a04_vo.Board;

@Service
public class A01_BoardService {
	@Autowired(required=false)
	private BoardDao dao;
	public ArrayList<Board> list(Board sch){
		return dao.list(sch);
	}
	public void insert(Board ins) {
		dao.insert(ins);
	}
	
}
