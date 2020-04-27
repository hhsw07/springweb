package springweb.z01_vo;

import java.util.ArrayList;

public class EmpList {
	/*
	{"emplist":[{"empno":7934,"ename":"MILLER","job":"CLERK",
						"mgr":7782,"hiredate":"1? 23, 1982","sal":1300.0,
						"comm":0.0,"deptno":10},
					    {"empno":7902,"ename":"FORD","job":"ANALYST","mgr":7566,"hiredate":"12? 3, 1981",
					    "sal":3000.0,"comm":0.0,"deptno":20}]} 
	 * */
	
	private ArrayList<Emp> emplist;
	public ArrayList<Emp> getEmplist() {
		return emplist;
	}

	public void setEmplist(ArrayList<Emp> emplist) {
		this.emplist = emplist;
	}
	
	
}
