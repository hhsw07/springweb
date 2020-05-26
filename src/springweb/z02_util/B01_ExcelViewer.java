package springweb.z02_util;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import springweb.a03_mvc.a04_vo.Board;

// springweb.z02_util.B01_ExcelViewer
public class B01_ExcelViewer extends AbstractExcelView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String modelNm = "blist";
		// 0. 모델 데이터. controll에서 보낸 모델명으로 실제
		//		ArrayList 데이터를 가져올 수 있다.
		List<Board> blist = (List<Board>)model.get(modelNm);
		// 1. 파일 filename="모델명.xls"
		response.setHeader("Content-Disposition",
				"attachment;filename=\""+modelNm+".xls\";");
		// 2. sheet
		//		1) sheet명 설정.
		workbook.setSheetName(0, "게시판데이터");
		HSSFSheet sheet = workbook.createSheet();
		//		2) 기본 컬럼 넓이 지정.
		sheet.setColumnWidth(1, 256*20);
		// 3. row
		//		1) title 지정.
			setExcelTitle(sheet);
		//		2) 데이터 지정..
			setExcelList(sheet,blist);
		// 4. cell
		
	}
	
	// 3-1 title 지정
	public void setExcelTitle(HSSFSheet sheet) {
		// 1. title 지정
		// 첫번째 행 만들어 타이틀 넣기.( <tr>라고 생각 )
		HSSFRow titleRow = sheet.createRow(0);
		// 행에서 넣은 데이터 cell 만들기. ( <td>라고 생각 )
		HSSFCell cel0 = titleRow.createCell(0);	cel0.setCellValue("계층lv");
		HSSFCell cel1 = titleRow.createCell(1);	cel1.setCellValue("글번호");
		HSSFCell cel2 = titleRow.createCell(2);	cel2.setCellValue("상위글번호");
		HSSFCell cel3 = titleRow.createCell(3);	cel3.setCellValue("제목");
		HSSFCell cel4 = titleRow.createCell(4);	cel4.setCellValue("내용");
		HSSFCell cel5 = titleRow.createCell(5);	cel5.setCellValue("조회수");
		HSSFCell cel6 = titleRow.createCell(6);	cel6.setCellValue("작성일");
		HSSFCell cel7 = titleRow.createCell(7);	cel7.setCellValue("수정일");
	}
	// 3-2 데이터 지정
	public void setExcelList(HSSFSheet sheet, List<Board> list) {
		for(int idx=0; idx<list.size();idx++) {
			Board b = list.get(idx);
			HSSFRow dataRow = sheet.createRow(idx+1);
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
	
	
}





