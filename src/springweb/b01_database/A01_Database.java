package springweb.b01_database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import springweb.z01_vo.Emp;


@Repository
public class A01_Database {
	private Connection con;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public void setCon() throws SQLException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String info="jdbc:oracle:thin:@localhost:1521:xe";
		con = DriverManager.getConnection(info, "scott", "tiger");
		System.out.println("정상 접속 성공.");
	}

	public ArrayList<Emp> getEmpList(){
		ArrayList<Emp> empList=new ArrayList<Emp>();
		try {
			setCon(); 
			String sql = "SELECT * FROM emp77 ORDER BY empno DESC";
			stmt = con.createStatement();
			rs=stmt.executeQuery(sql);
			int cnt=1;
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

	
	public ArrayList<Emp> getEmpList(Emp sch){
		ArrayList<Emp> empList=new ArrayList<Emp>();
		try {
			setCon(); // Connection 객체가 메모리 로딩.
			String sql = "SELECT * \r\n" + 
					"FROM emp77\r\n" + 
					"WHERE 1=1\r\n";
					if(sch.getEname()!=null&&!sch.getEname().equals(""))
					sql+="AND ename LIKE '%'||'"+sch.getEname()+"'||'%'\r\n";
					if(sch.getJob()!=null&&!sch.getJob().equals(""))
					sql+="AND job LIKE '%'||'"+sch.getJob()+"'||'%'\r\n";
					sql+="ORDER BY empno DESC ";
					System.out.println("##getEmpList sql문##");
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
		
	public Emp getEmp(int empno){
		Emp emp=null;
		try {
			setCon(); // Connection 객체가 메모리 로딩.
			String sql = "SELECT * \r\n" + 
					"FROM emp77\r\n" + 
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








