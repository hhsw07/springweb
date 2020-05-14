package springweb.a03_mvc.a02_service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import springweb.a03_mvc.a03_repository.BoardDao;
import springweb.a03_mvc.a04_vo.Board;
import springweb.a03_mvc.a04_vo.BoardSch;

@Service
public class A01_BoardService {
	@Autowired(required=false)
	private BoardDao dao;
	public ArrayList<Board> list(BoardSch sch){
		// 1. 데이터 총 건수
		sch.setCount(dao.totCnt(sch));
		// 2. 화면에 한번에 보여줄 데이터 건수 초기값 5.
		// 	    다음부터는 선택된 페이지 데이터 건수에 의해서 처리..
		if(sch.getPageSize() == 0) {
			sch.setPageSize(5);
		}
		// 3. 총 페이지 수 : 총건수/한번에 보여줄 데이터
		//				17/5 ==> 4
		//				20/5 ==> 4
		//				21/5 ==> 5 (올림)
		//	총건수/페이지크기를 실수로 처리하여 나머지가 있게 한 후, 올림 처리..
		// int totPage = (int)(Math.ceil(sch.getCount()/(double)sch.getPageSize()));
		sch.setPageCount((int)(Math.ceil(sch.getCount()/(double)sch.getPageSize())));
		// 4. 현태 클릭한 페이지 초기값
		if(sch.getCurPage() == 0) {
			sch.setCurPage(1);
		}
		/*
		ex)
		현재 페이지 번호 1
		페이지당 보일 데이터 건수 5
		화면에 나타날 데이터 1~5
		
		현재 페이지번호	페이지당 보일 데이터 건수	화면에 나타날 데이터	시작번호	마지막번호
		1			5					1 2 3 4 5		1		5
		2			5					6 7 8 9 10		6		10
		3			5					11 12 13 14 15	11		15
		마지막번호?	클릭한 페이지번호*페이지당 보일 데이터 건수
		시작번호?	(클릭한 페이지번호-1)*페이지당 보일 데이터 건수 +1
					(1-1)*5+1 = 1
					(2-1)*5+1 = 6
		*/
		sch.setStart((sch.getCurPage()-1)*sch.getPageSize()+1);
		sch.setEnd(sch.getCurPage()*sch.getPageSize());
		System.out.println("### ###");
		System.out.println("시작페이지 번호:"+sch.getStart());
		System.out.println("마지막페이지 번호:"+sch.getEnd());		
		// 4. block을 위한 속성값 설정.
		// 	1) 초기 block size(한번에 보일 block의 크기) 설정.
		sch.setBlocksize(5);
		/*
		1 2 3 4 5  ==> blocknum 1
		6 7 8 9 10 ==> blocknum 2
		11 12 13   ==> blocknum 3
			2) blocknum은 현재 페이지번호/블럭의크기 를 올림처리.
				1/5 2/5 3/5 4/5 5/5   =>(올림) 1
				6/5 7/5 8/5 9/5 10/5  =>(올림) 2
				11/5 12/5 13/5   	  =>(올림) 3
		*/
		int blocknum = (int)Math.ceil(sch.getCurPage()/(double)sch.getBlocksize());
		System.out.println("현재block 번호:"+sch.getCurPage());
		System.out.println("blocknum:"+blocknum);
		/*
			3) blocknum가 5를 기준으로
			시작페이지번호와 마지막페이지번호
				- block의 마지막번호는 block의크기 * blocknum
				    단, 마지막페이지번호보다 클 수 없다.
				    마지막 block번호가 페이지크기보다 클 때는 페이지크기를 마지막 block번호로 설정한다.
				  ==> 3항 연산자로 간단하게 처리.
				ex) blocknum 3 ==> 15(X), 13(O)
		*/
		int endBlock = blocknum * sch.getBlocksize();
		sch.setEndBlock(endBlock>sch.getPageCount()?sch.getPageCount():endBlock);
		//		- 시작번호는 현재block의번호 (blocknum-1)*현재블럭크기 +1
		sch.setStartBlock((blocknum-1)*sch.getBlocksize()+1);
		System.out.println("시작block번호:"+sch.getStartBlock());
		System.out.println("마지막block번호:"+sch.getEndBlock());
		
		
		return dao.list(sch);
	}
	public void insert(Board ins) {
		/*
		# 등록시 처리할 부분.
		1. 물리적 파일을 생성과 특정 위치로 변경.
		2. 등록 파일 정보 DB 처리.
		3. 실제 등록 처리..
		 * */ 
		dao.insert(ins);
		// 파일 업로드 내용..
		for(MultipartFile report:ins.getReport()) {
			// 물리적 파일 정리
			upload(report);
			
		}
	}
	public void update(Board update) {
		/*
		데이터 수정 처리
		1. 
		*/
		dao.updateBoard(update);
		// 파일 수정 정보 처리
		upload2(update);
	}	
	public void deleteBoard(int no) {
		dao.deleteBoard(no);
		dao.deleteFile(no);
	}
	
	
	@Value("${upload}")
	private String upload; // 업로드할 위치..
	@Value("${tmpUpload}")
	private String tmpUpload;// 임시업로드 위치.
	private void upload(MultipartFile mtf) {
		
		String fileName=mtf.getOriginalFilename();
		
		if(fileName!=null&&!fileName.equals("")) {
			File tmpFile = new File(tmpUpload+fileName);
			// 해당 폴드에 동일한 파일이 있으면 삭제 처리
			if(tmpFile.exists()) tmpFile.delete();
			try {
			// Stream으로 온 MultipartFile을 실제 파일로 변경처리.	
				mtf.transferTo(tmpFile);
				File orgFile = new File(upload+fileName);
		
			// tmp위치에 있는 파일을 현재 웹서버에 특정할 폴드로 이동.
			// StandardCopyOption.REPLACE_EXISTING : 기존 동일 파일명이 있을 때,
			// 마지막에 올린 파일로 변경 처리..
				Files.copy(tmpFile.toPath(), orgFile.toPath(), 
							StandardCopyOption.REPLACE_EXISTING);
				
				
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dao.insertRepo(fileName);
			
		}
	}
	// 파일 수정, 파일 정보 수정..
	private void upload2(Board upt) {
		// upt.getReport() : 수정파일 정보(배열)
		// upt.getFnames() : 기본 파일 정보(배열)
		MultipartFile[] files = upt.getReport();
		for(int idx=0;idx<files.length;idx++) {
			String fileName=files[idx].getOriginalFilename();
			if(fileName!=null&&!fileName.equals("")) {
				File tmpFile = new File(tmpUpload+fileName);
				// 해당 폴드에 동일한 파일이 있으면 삭제 처리
				if(tmpFile.exists()) tmpFile.delete();
				try {
				// Stream으로 온 MultipartFile을 실제 파일로 변경처리.	
					files[idx].transferTo(tmpFile);
					File orgFile = new File(upload+fileName);
			
				// tmp위치에 있는 파일을 현재 웹서버에 특정할 폴드로 이동.
				// StandardCopyOption.REPLACE_EXISTING : 기존 동일 파일명이 있을 때,
				// 마지막에 올린 파일로 변경 처리..
					Files.copy(tmpFile.toPath(), orgFile.toPath(), 
								StandardCopyOption.REPLACE_EXISTING);
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				/*
				# 파일 수정에 대한 DB처리..
				1. 게시물 고유번호 no, 기존 파일, 변경할파일 정보.
				2. HashMap no : 게시물 글번호
						   tar : 변경할 파일명
						   org : 기존 파일명..
				3. 기존 파일을 대체하는 경우 : update
				      기존에 없는 파일을 추가 처리하는 경우 : insert
				*/
				
				// org, tar, no
				String fnm = upt.getFnames()[idx];
				HashMap<String, String> hm = new HashMap<String, String>();
				hm.put("org", fnm);
				hm.put("tar", fileName);
				hm.put("no", ""+upt.getNo());
				// 등록된 파일이 있으면 수정,
				// 추가 등록시에는 등록 처리.
				if( fnm!=null && !fnm.equals("")) {
					dao.uptFileInfo(hm);
				}else {
					dao.insFileInfo(hm);
				}
				
			}			
			
		}
	}	
	// 상세화면 처리..
	public Board getBoard(int no) {
		// 조회수 카운트업..
		dao.uptReadCnt(no);
		System.out.println("파일의 갯수:"+dao.fnames(no).size());
		Board d = dao.getBoard(no);
		d.setFilenames(dao.fnames(no));
		return d;
	}
	
}
