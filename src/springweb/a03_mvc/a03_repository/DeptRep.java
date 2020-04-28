package springweb.a03_mvc.a03_repository;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import springweb.a03_mvc.a04_vo.Dept;

@Repository
public interface DeptRep {
	public ArrayList<Dept> deptList(Dept sch);
}
