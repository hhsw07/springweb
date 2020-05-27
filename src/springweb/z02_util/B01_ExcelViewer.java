package springweb.z02_util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import springweb.a03_mvc.a04_vo.Board;

// springweb.z02_util.B01_ExcelViewer
// 1. AbstractExcelView를 상속 처리.( 추상메서드: 반드시 재정의할 기능메서드가 필요)
public class B01_ExcelViewer extends AbstractExcelView {
	private CellStyle cs;
	
	// 2. 재정의할 기능 메서드.. : buildExcelDocument
	// 		모델로 받은 데이터(ArrayList<Board>)==> Excel 화면으로 만드는 작업
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		/*
		# 매개변수가 처리할 기능 내용..
		1. Map<String Objcet> model
			controller에서 넘겨온 모델데이터 ArrayList<Board>를 가지고 오는 처리..
		2. HSSFWorkbook workbook : 실제 출력할 엑셀 자체를 지정..
		3. request : 요청 데이터에 대한 처리..
		4. response : client에 전달하는 객체..
			이 객체를 통해서 client에 Stream형식 excel 파일을 전송한다.
		*/
		/*
		# controller에서 보낸 정보..
		d.addAttribute("blist",service.getAllList());
		return "excelViewer"
		*/
		String modelNm = "blist";
		// 0. 모델 데이터. controll에서 보낸 모델명으로 실제
		//		ArrayList 데이터를 가져올 수 있다.
		//		model.get("모델명") => Object
		//		file이나 객체는 Object로 전송할 수 있다.
		// 		Object를 typecasting에 의해 변환 처리한다.
		ArrayList<Board> blist = (ArrayList<Board>)model.get(modelNm);
		// 1. 파일 filename="모델명.xls"
		// 		다운로드 받을 파일명을 지정처리..
		response.setHeader("Content-Disposition",
				"attachment;filename=\""+modelNm+".xls\";");
		cs= setStyle(workbook);	// workbook이 엑셀에 대한 객체를 지칭.
		// 2. sheet
		//		1) sheet명 설정.
		HSSFSheet sheet = workbook.createSheet();
		workbook.setSheetName(0, "게시판데이터");
		//		2) 기본 컬럼 넓이 지정.(index는 0부터 시작)
		//sheet.setColumnWidth(1, 256*20);	
		sheet.setColumnWidth(3, 256*20);	
		sheet.setColumnWidth(4, 256*20);	
		sheet.setColumnWidth(6, 256*20);	
		sheet.setColumnWidth(7, 256*20);
		// 3. row
		//		1) title 지정.
			setExcelTitle(sheet);
		//		2) 데이터 지정.. 모델데이터를 넘겨줘서 처리.
			setExcelList(sheet,blist);
		
	}
	
	// 3.row 1) title 지정
	public void setExcelTitle(HSSFSheet sheet) {
		// 1. title 지정
		// 첫번째 행 만들어 타이틀 넣기.( <tr>라고 생각 )
		HSSFRow titleRow = sheet.createRow(0);
		titleRow.setHeight((short) 400);
		// 행에서 넣은 데이터 cell 만들기. ( <td>라고 생각 )
		String []titles= {"계층lv","글번호","상위글","제목","내용","조회수","작성일","수정일"};
		for(int idx=0;idx<titles.length;idx++) {
			HSSFCell cel = titleRow.createCell(idx); 
			cel.setCellValue(titles[idx]);
			cel.setCellStyle(cs); // 정의된 style을 적용할 부분에 대하여 설정
			// 1. style을 변경하고자 할 때.
			// 2. 정의된 메서드를 다시 만들어서 return 된 CellStyle로 각 cell에서
			//		setCellStyle로 할당 처리하면 된다.
		}
	}
	// 3.row 2) 데이터 지정
	public void setExcelList(HSSFSheet sheet, List<Board> list) {
		// HSSRow를 2번째부터 생성 처리..
		for(int idx=0; idx<list.size();idx++) {
			Board b = list.get(idx);
			HSSFRow dataRow = sheet.createRow(idx+1);
			// 각 HSSFCell 마다 모델 데이터를 할당 처리..
			HSSFCell cel0 = dataRow.createCell(0);	cel0.setCellValue(b.getLevel());
			HSSFCell cel1 = dataRow.createCell(1);	cel1.setCellValue(b.getNo());
			HSSFCell cel2 = dataRow.createCell(2);	cel2.setCellValue(b.getRefno());
			HSSFCell cel3 = dataRow.createCell(3);	cel3.setCellValue(b.getTitle());
			HSSFCell cel4 = dataRow.createCell(4);	cel4.setCellValue(b.getContent());
			HSSFCell cel5 = dataRow.createCell(5);	cel5.setCellValue(b.getReadcnt());
			HSSFCell cel6 = dataRow.createCell(6);	cel6.setCellValue(b.getCredte());
			HSSFCell cel7 = dataRow.createCell(7);	cel7.setCellValue(b.getUptdte());
		}
	}
	public CellStyle setStyle(HSSFWorkbook workbook) {
		// POI api 참고
		//스타일 설정
		CellStyle styleOfColorTest = workbook.createCellStyle();
		 
		//정렬
		styleOfColorTest.setAlignment(CellStyle.ALIGN_CENTER);
		styleOfColorTest.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		//테두리 라인
		styleOfColorTest.setBorderRight(HSSFCellStyle.BORDER_THIN);
		styleOfColorTest.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		styleOfColorTest.setBorderTop(HSSFCellStyle.BORDER_THIN);
		styleOfColorTest.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		//폰트 설정
		HSSFFont fontOfGothic = workbook.createFont();
		fontOfGothic.setFontName("고딕");
		fontOfGothic.setFontHeight((short) 200);
		styleOfColorTest.setFont(fontOfGothic);
		//배경색
		styleOfColorTest.setFillForegroundColor(HSSFColor.AQUA.index);  
		styleOfColorTest.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		return styleOfColorTest;
	}
	
}





