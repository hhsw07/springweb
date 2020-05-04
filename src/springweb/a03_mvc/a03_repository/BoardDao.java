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
	// 파일 정보 등록..
	public void insertRepo(String fname);
	
	public Board getBoard(int no);
	// 파일 다운로드 정보 
	public ArrayList<String> fnames(int no);
	
	public void uptReadCnt(int no);
	
	// 게시글 수정
	public void update(Board upt);
	// 게시글 삭제
	public void delete(int no);
	
}
