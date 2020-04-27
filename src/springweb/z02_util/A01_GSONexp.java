package springweb.z02_util;

import java.util.ArrayList;

import com.google.gson.Gson;

import springweb.a02_di.z01_vo.Person;
import springweb.b01_database.A01_Database;
import springweb.z01_vo.Book;
import springweb.z01_vo.Emp;
import springweb.z01_vo.EmpList;
import springweb.z01_vo.Member;
import springweb.z01_vo.ProdList;
import springweb.z01_vo.Product;

public class A01_GSONexp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
/*
# Gson api의 활용
1. 객체 ==> json문자열, json문자열 ==> 객체
2. 단위 객체, 배열 객체..

		객체==> json문자열 변환처리( client==> server,
		json데이터 전송할 때, 활용
		ex) webserver단에서 폰에 json데이터를 전송할 때,
			webserver단(jsp, spring에서 처리)
*/
		// 1. 객체 생성
		Product p01 = new Product("사과",3000,2);
		// 2. Gson 객체 생성과 메서드 toJson(객체참조)
		Gson gson = new Gson();
		String json = gson.toJson(p01);
		System.out.println("# Product의 json 데이터 #");
		System.out.println(json);
		// {"name":"사과","price":3000,"cnt":2}
		
		/*
		ex1) Person 객체로 이름 나이 사는곳을 설정하고,
			객체에 데이터를 입력한 후, Gson api에 의해서 json 데이터를 만드세요
		*/
		Person p1 = new Person("홍길동",25,"서울 신림동");
		String jsondata2 = gson.toJson(p1);
		System.out.println("ex1) "+jsondata2);
		
		/*
		ex2) A01_Database.java의 public Emp getEmp(int empno)를
			활용하여, Emp의 json 데이터를 처리하세요.
		*/
		A01_Database dao = new A01_Database();
		int empno = 7369;
		Emp emp = dao.getEmp(empno);
		String jsonEmp = gson.toJson(emp);
		System.out.println("ex2) "+jsonEmp);
		
/*		
# list형 데이터에 대한 json data변환처리..
[{"empno":7934,"ename":"MILLER","job":"CLERK","mgr":7782,"hiredate":"1월 23, 1982","sal":1300.0,"comm":0.0,"deptno":10},{"empno":7902,"ename":"FORD","job":"ANALYST","mgr":7566,"hiredate":"12월 3, 1981","sal":3000.0,"comm":0.0,"deptno":20},{"empno":7900,"ename":"JAMES","job":"CLERK","mgr":7698,"hiredate":"12월 3, 1981","sal":950.0,"comm":0.0,"deptno":30},{"empno":7844,"ename":"TURNER","job":"SALESMAN","mgr":7698,"hiredate":"9월 8, 1981","sal":1500.0,"comm":0.0,"deptno":30},{"empno":7839,"ename":"KING","job":"PRESIDENT","mgr":0,"hiredate":"11월 17, 1981","sal":5000.0,"comm":0.0,"deptno":10},{"empno":7782,"ename":"CLARK","job":"MANAGER","mgr":7839,"hiredate":"6월 9, 1981","sal":2450.0,"comm":0.0,"deptno":10},{"empno":7698,"ename":"BLAKE","job":"MANAGER","mgr":7839,"hiredate":"5월 1, 1981","sal":2850.0,"comm":0.0,"deptno":30},{"empno":7654,"ename":"MARTIN","job":"SALESMAN","mgr":7698,"hiredate":"9월 28, 1981","sal":1250.0,"comm":1400.0,"deptno":30},{"empno":7566,"ename":"JONES","job":"MANAGER","mgr":7839,"hiredate":"4월 2, 1981","sal":2975.0,"comm":0.0,"deptno":20},{"empno":7521,"ename":"WARD","job":"SALESMAN","mgr":7698,"hiredate":"2월 22, 1981","sal":1250.0,"comm":500.0,"deptno":30},{"empno":7499,"ename":"ALLEN","job":"SALESMAN","mgr":7698,"hiredate":"2월 20, 1981","sal":1600.0,"comm":300.0,"deptno":30},{"empno":7369,"ename":"SMITH","job":"CLERK","mgr":7902,"hiredate":"12월 17, 1980","sal":800.0,"comm":0.0,"deptno":20}]

*/	
		ArrayList<Emp> elist = dao.getEmpList();
		String jsonList = gson.toJson(elist);
		System.out.println(jsonList); // 한글 안깨짐
/*
3. client(핸드폰)에서 서버에서 온 json데이터 객체화 처리
*/
		// {"id":"himan","pass":"7777"}
		String jsData = "{\"id\":\"himan\",\"pass\":\"7777\"}";
		Member mem = gson.fromJson(jsData, Member.class);
		System.out.println("# json을 통해서 만들어진 Member의 데이터 #");
		System.out.println(mem.getId());
		System.out.println(mem.getPass());
		/*
		ex) 도서명 도서가격 작가 출판사  속성값을 가진 json 데이터를 만들고,
			이 json 데이터를 통해 할당할 객체 Book를 선언하고,
			해당 Book 객체에 데이터를 처리하세요.
		*/
		// {"title":"안드로이드 실무","price":30000,"publisher":"아이티나라","writer":"홍길동"}
		String jsBook = "{\"title\":\"안드로이드 실무\",\"price\":30000,\"publisher\":\"아이티나라\",\"writer\":\"홍길동\"}";
		Book bk = gson.fromJson(jsBook, Book.class);
		System.out.println("# 책정보 #");
		System.out.println(bk.getTitle());
		System.out.println(bk.getPrice());
		System.out.println(bk.getWriter());
		System.out.println(bk.getPublisher());
/*	
4. ArrayList<VO>의 json 데이터 처리..
	1) gson.fromJson(jsData2, ArrayList<Book>().class) (X)
	2) ArrayList<Book>의 객체를 수용할 수 있는 객체를 따로 선언하고 사용
	3) {"emplist":[{"empno":7934,"ename":"MILLER","job":"CLERK",
					"mgr":7782,"sal":1300.0,
					"comm":0.0,"deptno":10},
				    {"empno":7902,"ename":"FORD","job":"ANALYST","mgr":7566,
				    "sal":3000.0,"comm":0.0,"deptno":20}]}
		cf) date 형식은 다른방법 필요
	4) 사용자 정의 객체가 필요로 한다.
*/	
		String jsData3 = "{\"emplist\":[{\"empno\":7934,\"ename\":\"MILLER\",\"job\":\"CLERK\",\r\n" + 
				"					\"mgr\":7782,\"sal\":1300.0,\r\n" + 
				"					\"comm\":0.0,\"deptno\":10},\r\n" + 
				"				    {\"empno\":7902,\"ename\":\"FORD\",\"job\":\"ANALYST\",\"mgr\":7566,\r\n" + 
				"				    \"sal\":3000.0,\"comm\":0.0,\"deptno\":20}]} ";
		
		EmpList emplist = gson.fromJson(jsData3, EmpList.class);
		ArrayList<Emp> list = emplist.getEmplist();
		for(Emp e: list) {
			System.out.print(e.getEmpno()+"\t");
			System.out.print(e.getEname()+"\t");
			System.out.print(e.getJob()+"\t");
			System.out.print(e.getSal()+"\n");
		}
		
/*
ex) Product를 ArrayList에 할당하게 jsondata 만들기
	{"plist":[{"name":"사과","price":3000,"cnt":5},
			  {"name":"바나나","price":4000,"cnt":3},
			  {"name":"딸기","price":12000,"cnt":2} ]}
*/		
		String jsData4 = "{\"plist\":[{\"name\":\"사과\",\"price\":3000,\"cnt\":5},\r\n" + 
				"			  {\"name\":\"바나나\",\"price\":4000,\"cnt\":3},\r\n" + 
				"			  {\"name\":\"딸기\",\"price\":12000,\"cnt\":2} ]}";
		ProdList prodlist11 = gson.fromJson(jsData4, ProdList.class);
		System.out.println();
		System.out.println(prodlist11.getPlist());
		
		ArrayList<Product> plist = prodlist11.getPlist();
		//System.out.println(plist);
		System.out.println("# 물건정보 json => 객체 #");
		for(Product p : plist) {
			System.out.print(p.getName()+"\t");
			System.out.print(p.getPrice()+"\t");
			System.out.print(p.getCnt()+"\n");
		}
	}

}
