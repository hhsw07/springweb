package springweb.z02_util;

import com.google.gson.Gson;

import springweb.z01_vo.Product;

public class A01_GSONexp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
/*
# GSon의 활용
1. 객체를 json 만들기..
	1) Gson객체 참조.toJson(객체) : json데이터 생성.
	
2. json데이터 객체로 만들기
	1) 직접 만들기.
	2) json 파싱 처리하기..
*/
		Product p01 = new Product("사과",3000,2);
		Gson gson = new Gson();
		String json = gson.toJson(p01);
		System.out.println(json);
		// {"name":"사과","price":3000,"cnt":2}
		
		
		
		
		
	}

}
