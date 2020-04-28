package springweb.a03_mvc.a03_repository;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import springweb.a03_mvc.a04_vo.Emp;

@Repository
public interface EmpRep {
	// empMapper.xml과 연결
	// EmpRep ==> namespace명
	// ArrayList<Emp> => ResuletMap
	// empList ==> id
	// Emp ==> parameterType
	public ArrayList<Emp> empList(Emp sch);
}
