package springweb.a03_mvc.a03_repository;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import springweb.a03_mvc.a04_vo.Board;

@Repository
public interface BoardDao {
	// select * from reboard
	// springweb.a03_mvc.a03_repository.BoardDao
	public ArrayList<Board> list(Board sch);
	public void insert(Board ins);
}
