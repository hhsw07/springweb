package springweb.a03_mvc.a04_vo;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class Board {
	private int no;			// 글번호
	private int refno;		// 답글번호
	private String title; 	// 제목
	private String content; // 내용
	private String writer; 	//작성자
	private int readcnt; 	// 조회건수
	private Date credte;	// 등록일
	private Date uptdte;	// 수정일
	private String etc; 	// 기타
	private int level;		// 계층형에서 level-답글처리시.
	
	// 파일 등록 처리 객체
	private MultipartFile[] report;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getRefno() {
		return refno;
	}
	public void setRefno(int refno) {
		this.refno = refno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public int getReadcnt() {
		return readcnt;
	}
	public void setReadcnt(int readcnt) {
		this.readcnt = readcnt;
	}
	public Date getCredte() {
		return credte;
	}
	public void setCredte(Date credte) {
		this.credte = credte;
	}
	public Date getUptdte() {
		return uptdte;
	}
	public void setUptdte(Date uptdte) {
		this.uptdte = uptdte;
	}
	public String getEtc() {
		return etc;
	}
	public void setEtc(String etc) {
		this.etc = etc;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public MultipartFile[] getReport() {
		return report;
	}
	public void setReport(MultipartFile[] report) {
		this.report = report;
	}
	
	
	
}
