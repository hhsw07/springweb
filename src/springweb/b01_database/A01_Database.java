package springweb.b01_database;

// DB관련 package java.sql.*로 모두 사용 처리.
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import springweb.z01_vo.Emp;


/*
# java에서 Database 처리..
1. 자바와 데이터베이스 연결은 다음과 같은 절차로 진행된다.
	1) 각 밴드(회사)에 지원하는 공통 모듈인 jdbc드라이버를 이용한다.
	 	- jdbc(java database connection) 모듈을 사용할 수 있게끔  lib
	 		에 위치시킴.
	2) jdbc드라이버를 메모상 객체로 올려놓고 사용할 수 있겠끔
	 		Class.forName("jdbc드라이버")
	 	- 해당 드라이버 클래스를 메모리에 올리면 가지고 있는
	 		객체와 메서드를 사용할 수 있게 된다.
	3) 데이터베이스 서버와 연결할 수 있는 객체(Connection)를 통해서
	 	- ip, port, sid, 계정, 비밀번호
	4) sql을 사용할 수 있는 객체(Statement)를 이용하여, sql을 실행한다.
	 	- sql 중에 결과값이 있는 객체(ResultSet)을 통하여,
	 		결과를 받는다.
	5) 데이터베이스 자원을 해제 처리한다..
	 	- 예외 처리
	 		
 * */
// jspexp.b01_database.A01_Database
@Repository
public class A01_Database {
	// 공통으로 사용할 field 선언.
	// 연결, 대화, 결과 처리하는 객체 선언.
	// 1. 연결 객체
	private Connection con;
	// 2. 대화 객체
	// 	1) 기본 대화 객체
	private Statement stmt;
	//  2) 준비된 대화 객체
	private PreparedStatement pstmt;
	// 3. 결과값을 받는 객체..
	private ResultSet rs;
	
	// DB하기 위한 공통 기능 메서드인 연결처리 메서드 선언.
	public void setCon() throws SQLException {
//		1. driver를 메모리에 올려서
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		2. 특정 서버에 접속
//			- 접속정보 : 드라이버명:@ip:port:sid
//		String info="jdbc:oracle:thin:@211.238.140.48:1521:xe";
//			- 공통 DB 서버 접속시, 접속 ip, 해당 서버의 방화벽 열어놓기
		String info="jdbc:oracle:thin:@localhost:1521:xe";
//		3. Connection 객체로 연결처리.
		con = DriverManager.getConnection(info, "scott", "tiger");
		System.out.println("정상 접속 성공.");
//		
//		ex) oracle 방화벽 처리, A02_Database.java를 통해서,
//		    옆 pc DB 접근 처리..
	}
/*
# select 조회처리 DB 모듈 만들기..
1. sql을 작성..
	SELECT * FROM emp77
	
2. sql를 통해서 나타낼 데이터를 담는 객체(VO), 객체배열ArrayList<VO>
	선언.
	0) VO를 어떻게 만들것인가?
		데이터가 나올 컬럼명, 어떤 데이터 type을 저장할 것인지를
		고려하여 작성한다.
		#주의 sql의 결과를 기준으로 만들어야 된다.(테이블 구조X)
	
	1) 메서드 기본틀 정의 
	public ArrayList<Emp> getEmpList(){
		ArrayList<Emp> list= null;
		return list;
	}
3. 메서드 세부내역 선언과 구현..
	1) 공통 모듈 DB연결.
	2) sql을 통해서 대화객체 생성과 수행 Statement
	3) 수행한 결과값을 ResultSet객체로 할당.
	4) ResultSet의 결과를 ArrayList<VO>로 담기.
		while(rs.next()) 반복 처리..
	5) 자원의 해제..
	6) 예외의 처리..
	7) main()를 테스트
	8) 웹화면에서 구현된 내용 출력 처리.
 * 
 * */	
	public ArrayList<Emp> getEmpList(){
		ArrayList<Emp> empList=new ArrayList<Emp>();
		// 1. DB 연결..예외 처리..
		try {
			setCon(); // Connection 객체가 메모리 로딩.
			//   Connection con 클래스의 필드로 할당된 상황..
		// 2. sql을 통해서 대화객체 생성과 수행 Statement
			String sql = "SELECT * FROM emp77 ORDER BY empno DESC";
			// 1) Connection 객체를 통해서 대화할 수 있는 객체인
			//    Statement객체 생성.. 필드에 있는 Statement에 할당.
			stmt = con.createStatement();
			// 2) Statement를 통해서 sql을 수행하고, 수행할 결과를
			//     ResultSet 객체에 할당..
		// 3. 결과인 ResultSet 객체의 생성	
			rs=stmt.executeQuery(sql);
		// 		1) ResultSet은 2차원의 컬럼과 데이터를 가진 객체이다.
		/*			- next() : 
		 * 				행단위로 접근하게 해준다.
		 * 				rs.next()  true/false true값 있다
		 * 				 해당 행의 데이터가 있을 때, true가 
		 * 				데이터를접근할 수 있다.
		 * 				총 데이터가 row가 12개일 때, 12 번 접근할 때, true가 나오고,
		 * 				13번째 접근하는 순간 데이터 없기 때문에 false가 나온다.
		 * 				
		 * 			- getXXX("컬럼"), getXXX(idx)
		 * 				get가져올데이터타입 : 기본적으로 String으로 가져올 수 있다.
		 * 				ex) rs.getString("job"), rs.getDouble("sal")
		 * 					rs.getDate("hiredate"), rs.getInt("empno")
		 * 					SELECT ename name, empno no, deptno FROM emp77;
		 * 					rs.getString(1) ==> rs.getString("name")
		 * 					rs.getInt(2)  ==> rs.getInt("no")
		 * 					rs.getInt(3)  ==> rs.getInt("deptno")
		 * */
			// 반복   for, while, do while
			// 데이터가 있는 행까지 반복을 처리..
			// rs.getString("컬럼명") rs.getString(1으로시작하는 index) 
			// 가져올 수 있지만, 이것을 타입 맞게 사용하지 못한다.
			// rs.getInt(), rs.getDoble()로 할 때, 연산이나 기타 처리를
			// 할 수 있다.
			int cnt=1;
			// ArrayList<Emp>에 해당 객체를 담을려면?
			//   행단위로 Emp객체를 생성해서, 각각의 속성에 컬럼에서온
			//   데이터를 할당하고, 
			//   list.add() Emp객체를 누적 처리..
			Emp emp = null;
			while(rs.next()) {
				emp = new Emp();
				emp.setEmpno(rs.getInt("empno"));
				emp.setEname(rs.getString("ename"));
				emp.setMgr(rs.getInt("mgr"));
				emp.setJob(rs.getString("job"));
				emp.setHiredate(rs.getDate("hiredate"));
				emp.setSal(rs.getDouble("sal"));
				emp.setComm(rs.getDouble("comm"));
				emp.setDeptno(rs.getInt("deptno"));
				empList.add(emp);
				System.out.print(cnt+++"행:\t");
				System.out.print(rs.getInt("empno")+"\t");
				System.out.print(rs.getString("ename")+"\t");
				System.out.print(rs.getString("job")+"\t");
				System.out.print(rs.getInt("mgr")+"\t");
				System.out.print(rs.getDate("hiredate")+"\t");
				System.out.print(rs.getDouble("sal")+"\t");
				System.out.print(rs.getDouble("comm")+"\t");
				System.out.print(rs.getInt("deptno")+"\n");
			}
			/*
			5) 자원의 해제..
			 	- 객체가 메모리할당 반대로 안쪽부터 객체의
			 		메모리를 해제하여야 한다.
			 		rs, stmt, con
			 */
			rs.close();
			stmt.close();
			con.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return empList;
	}
	/*
	# select 조회처리 DB 모듈 만들기..
	1. sql을 작성..
		SELECT * FROM emp77
		
	2. sql를 통해서 나타낼 데이터를 담는 객체(VO), 객체배열ArrayList<VO>
		선언.
		0) VO를 어떻게 만들것인가?
			데이터가 나올 컬럼명, 어떤 데이터 type을 저장할 것인지를
			고려하여 작성한다.
			#주의 sql의 결과를 기준으로 만들어야 된다.(테이블 구조X)
		
		1) 메서드 기본틀 정의 
		public ArrayList<Emp> getEmpList(){
			ArrayList<Emp> list= null;
			return list;
		}
	3. 메서드 세부내역 선언과 구현..
		1) 공통 모듈 DB연결.
		2) sql을 통해서 대화객체 생성과 수행 Statement
		3) 수행한 결과값을 ResultSet객체로 할당.
		4) ResultSet의 결과를 ArrayList<VO>로 담기.
			while(rs.next()) 반복 처리..
		5) 자원의 해제..
		6) 예외의 처리..
		7) main()를 테스트
		8) 웹화면에서 구현된 내용 출력 처리.
	 * 
	 * */	
		public ArrayList<Emp> getEmpList(Emp sch){
			ArrayList<Emp> empList=new ArrayList<Emp>();
			try {
				setCon(); // Connection 객체가 메모리 로딩.
				String sql = "SELECT * \r\n" + 
						"FROM emp77\r\n" + 
						"WHERE 1=1\r\n";
						// 검색한 내용을  Emp sch로 넘기고 해당 내용을
						// keyword검색하게 한다.
						if(sch.getEname()!=null&&!sch.getEname().equals(""))
						sql+="AND ename LIKE '%'||'"+sch.getEname()+"'||'%'\r\n";
						if(sch.getJob()!=null&&!sch.getJob().equals(""))
						sql+="AND job LIKE '%'||'"+sch.getJob()+"'||'%'\r\n";
						sql+="ORDER BY empno DESC ";
						System.out.println("##sql##");
						System.out.println(sql);
				stmt = con.createStatement();
				rs=stmt.executeQuery(sql);
				Emp emp = null;
				while(rs.next()) {
					emp = new Emp();
					emp.setEmpno(rs.getInt("empno"));
					emp.setEname(rs.getString("ename"));
					emp.setMgr(rs.getInt("mgr"));
					emp.setJob(rs.getString("job"));
					emp.setHiredate(rs.getDate("hiredate"));
					emp.setSal(rs.getDouble("sal"));
					emp.setComm(rs.getDouble("comm"));
					emp.setDeptno(rs.getInt("deptno"));
					empList.add(emp);
				}
				rs.close();
				stmt.close();
				con.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(e.getMessage());
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
			
			return empList;
		}
	/*
	 # CRUD(create, read, update, delete)
	 1. sql 만들기.
	 2. Connection 객체의 autocommit 방지
	 3. Statement로 등록 처리..
	 4. commit()수행
	 5. 자원해제
	 6. 예외처리에서 rollback 처리..
	 	
	 */
			public void insertEmp(Emp ins){
				try {
					setCon(); // Connection 객체가 메모리 로딩.
					String sql = "INSERT INTO emp77 values(emp77_seq.nextval,\r\n" + 
								"	'"+ins.getEname()+"','"+ins.getJob()+"',"+
								ins.getMgr()+",sysdate,"+ins.getSal()+","+
								ins.getComm()+","+ins.getDeptno()+")";
					System.out.println(sql);
					// autocommit방식
					con.setAutoCommit(false);
					stmt = con.createStatement();
					// 실행
					stmt.executeUpdate(sql);
					con.commit();
					// 자원해제
					stmt.close(); con.close();
					System.out.println("등록 성공!!");
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println(e.getMessage());
					// 입력시, 문제 발생시, 이전 데이터 원복 처리.
					try {
						con.rollback();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}catch(Exception e) {
					System.out.println(e.getMessage());
				}
			}
	/*
			# select 조회처리 DB 모듈 만들기..
			1. sql을 작성..
				SELECT * FROM emp77
				
			2. sql를 통해서 나타낼 데이터를 담는 객체(VO), 객체배열ArrayList<VO>
				선언.
				0) VO를 어떻게 만들것인가?
					데이터가 나올 컬럼명, 어떤 데이터 type을 저장할 것인지를
					고려하여 작성한다.
					#주의 sql의 결과를 기준으로 만들어야 된다.(테이블 구조X)
				
				1) 메서드 기본틀 정의 
				public ArrayList<Emp> getEmpList(){
					ArrayList<Emp> list= null;
					return list;
				}
			3. 메서드 세부내역 선언과 구현..
				1) 공통 모듈 DB연결.
				2) sql을 통해서 대화객체 생성과 수행 Statement
				3) 수행한 결과값을 ResultSet객체로 할당.
				4) ResultSet의 결과를 ArrayList<VO>로 담기.
					while(rs.next()) 반복 처리..
				5) 자원의 해제..
				6) 예외의 처리..
				7) main()를 테스트
				8) 웹화면에서 구현된 내용 출력 처리.
			 * 
			 * */	
				public Emp getEmp(int empno){
					Emp emp=null;
					try {
						setCon(); // Connection 객체가 메모리 로딩.
						String sql = "SELECT * \r\n" + 
								"FROM emp\r\n" + 
								"WHERE empno="+empno+" ";
								System.out.println("##sql##");
								System.out.println(sql);
						stmt = con.createStatement();
						rs=stmt.executeQuery(sql);
						if(rs.next()) {
							emp = new Emp();
							emp.setEmpno(rs.getInt("empno"));
							emp.setEname(rs.getString("ename"));
							emp.setMgr(rs.getInt("mgr"));
							emp.setJob(rs.getString("job"));
							emp.setHiredate(rs.getDate("hiredate"));
							emp.setSal(rs.getDouble("sal"));
							emp.setComm(rs.getDouble("comm"));
							emp.setDeptno(rs.getInt("deptno"));
						}
						rs.close();
						stmt.close();
						con.close();
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println(e.getMessage());
					}catch(Exception e) {
						System.out.println(e.getMessage());
					}
					return emp;
				}
	/*
				 # CRUD(create, read, update, delete)
				 1. sql 만들기.
				 2. Connection 객체의 autocommit 방지
				 3. Statement로 등록 처리..
				 4. commit()수행
				 5. 자원해제
				 6. 예외처리에서 rollback 처리..
				 	
				 */
						public void updateEmp(Emp upt){
							try {
								setCon(); // Connection 객체가 메모리 로딩.
								String sql = "UPDATE EMP77 \r\n" + 
											"	SET ename='"+upt.getEname()+"',\r\n" + 
											"		job='"+upt.getJob()+"',\r\n" + 
											"		mgr="+upt.getMgr()+",\r\n" + 
											"		sal="+upt.getSal()+",\r\n" + 
											"		comm="+upt.getComm()+",\r\n" + 
											"		deptno="+upt.getDeptno()+"\r\n" + 
											"WHERE empno="+upt.getEmpno()+" ";
								System.out.println(sql);
								// autocommit방식
								con.setAutoCommit(false);
								stmt = con.createStatement();
								// 실행
								stmt.executeUpdate(sql);
								con.commit();
								// 자원해제
								stmt.close(); con.close();
								System.out.println("수정 성공!!");
								
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								System.out.println(e.getMessage());
								// 입력시, 문제 발생시, 이전 데이터 원복 처리.
								try {
									con.rollback();
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								
							}catch(Exception e) {
								System.out.println(e.getMessage());
							}
						}
	/*
						 # CRUD(create, read, update, delete)
						 1. sql 만들기.
						 2. Connection 객체의 autocommit 방지
						 3. Statement로 등록 처리..
						 4. commit()수행
						 5. 자원해제
						 6. 예외처리에서 rollback 처리..
						 	
						 */
								public void deleteEmp(int empno){
									try {
										setCon(); // Connection 객체가 메모리 로딩.
										String sql = "DELETE EMP77 \r\n" + 
													"WHERE empno="+empno+" ";
										System.out.println(sql);
										// autocommit방식
										con.setAutoCommit(false);
										stmt = con.createStatement();
										// 실행
										stmt.executeUpdate(sql);
										con.commit();
										// 자원해제
										stmt.close(); con.close();
										System.out.println("삭제 성공!!");
										
									} catch (SQLException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
										System.out.println(e.getMessage());
										// 입력시, 문제 발생시, 이전 데이터 원복 처리.
										try {
											con.rollback();
										} catch (SQLException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
										
									}catch(Exception e) {
										System.out.println(e.getMessage());
									}
								}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		A01_Database db = new A01_Database();
		Emp ins = new Emp("마영수", "대리", 7788, 3500, 100, 10);
		db.insertEmp(ins);
		
		
		System.out.println("데이터건수:"+db.getEmpList().size());
	}

}








