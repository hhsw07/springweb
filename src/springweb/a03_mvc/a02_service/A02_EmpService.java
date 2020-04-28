package springweb.a03_mvc.a02_service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springweb.a03_mvc.a03_repository.EmpRep;
import springweb.a03_mvc.a04_vo.Emp;

@Service
public class A02_EmpService {
	@Autowired(required=false)
	private EmpRep rep;
	
	public ArrayList<Emp> empList(Emp sch){
		return rep.empList(sch);
	}
	
}
