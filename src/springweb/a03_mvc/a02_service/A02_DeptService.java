package springweb.a03_mvc.a02_service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springweb.a03_mvc.a03_repository.DeptRep;
import springweb.a03_mvc.a04_vo.Dept;

@Service
public class A02_DeptService {
	@Autowired(required=false)
	private DeptRep rep;
	
	public ArrayList<Dept> deptList(Dept sch){
		return rep.deptList(sch);
	}
	
}
