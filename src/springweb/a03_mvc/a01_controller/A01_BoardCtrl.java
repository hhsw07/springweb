package springweb.a03_mvc.a01_controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import springweb.a03_mvc.a02_service.A01_BoardService;
import springweb.a03_mvc.a04_vo.Board;

@Controller
@RequestMapping("/board.do")
public class A01_BoardCtrl {
	// http://localhost:5080/springweb/board.do?method=list
	// http://localhost:5080/springweb/board.do?method=insert
	// http://localhost:5080/springweb/board.do?method=detail
	// http://localhost:5080/springweb/board.do?method=update
	// http://localhost:5080/springweb/board.do?method=delete
	@Autowired(required=false)
	private A01_BoardService service;
	@RequestMapping(params="method=list")
	public String list(Board sch, Model d) {
		d.addAttribute("blist", service.list(sch));
		// view resolver로 접두어와 접미어가 생략했을 때 처리..
		//return "a01_boardList";
		return "WEB-INF\\views\\a03_mvc\\a01_boardList.jsp";
	}
	@RequestMapping(params="method=Ajaxlist")
	public String ajaxlist(Board sch, Model d) {
		d.addAttribute("blist", service.list(sch));
		return "jsonReport";
	}	
	@RequestMapping(params="method=insForm")
	public String insertForm() {
		return "WEB-INF\\views\\a03_mvc\\a01_boardInsert.jsp";
	}
	@RequestMapping(params="method=insert")
	public String insert(Board insert) {
		System.out.println("등록 제목:"+insert.getTitle());
		System.out.println("파일 로딩:"+
		insert.getReport()[0].getOriginalFilename());
		service.insert(insert);
		
		return "WEB-INF\\views\\a03_mvc\\a01_boardInsert.jsp";
	}
	@RequestMapping(params="method=detail")
	public String detail(@RequestParam("no") int no, Model d) {
		d.addAttribute("board", service.getBoard(no));
		return "WEB-INF\\views\\a03_mvc\\a01_boardDetail.jsp";
	}
	
	// 다운로드 처리.
	@RequestMapping(params="method=download")
	public String download(@RequestParam("fname") String fname, Model d) {
		// 탑재할 모델명은 파일명으로 설정..
		System.out.println("다운로드할 파일명:"+fname);
		d.addAttribute("downloadFile",fname);
		return "download"; // 컨테이너에서 선언한 viewer명
	}
	
	// 게시글 수정
	@RequestMapping(params="method=update")
	public String update(Board upt, Model d) {
		service.update(upt);
		d.addAttribute("board", service.getBoard(upt.getNo()));
		return "WEB-INF\\views\\a03_mvc\\a01_boardDetail.jsp";
	}
	// 게시글 삭제
	@RequestMapping(params="method=delete")
	public String update(@RequestParam("no") int no, Model d) {
		service.delete(no);
		d.addAttribute("blist", service.list(new Board()));
		return "WEB-INF\\views\\a03_mvc\\a01_boardList.jsp";
	}
	
	
	
	
}
