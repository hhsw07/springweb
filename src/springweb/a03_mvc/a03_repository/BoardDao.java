package springweb.a03_mvc.a03_repository;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Repository;

import springweb.a03_mvc.a04_vo.Board;

@Repository
public interface BoardDao {
	// select * from reboard
	// springweb.a03_mvc.a03_repository.BoardDao
	public ArrayList<Board> list(Board sch);
	
	public void insert(Board ins);
	public void updateBoard(Board upt);
	public void deleteBoard(int no);
	
	public Board getBoard(int no);
	public void uptReadCnt(int no);
	
	// 파일 정보 등록..
	public void insertRepo(String fname);
	// 파일 다운로드 정보 
	public ArrayList<String> fnames(int no);
	public void insFileInfo(HashMap hm);
	public void uptFileInfo(HashMap hm);
	public void deleteFile(int no);
	
	
}
