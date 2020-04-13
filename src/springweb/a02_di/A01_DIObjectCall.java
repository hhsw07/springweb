package springweb.a02_di;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import springweb.a02_di.z01_vo.Man;
import springweb.a02_di.z01_vo.Mart;
import springweb.a02_di.z01_vo.Person;
import springweb.a02_di.z01_vo.Woman;

public class A01_DIObjectCall {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
/*
# DI 연습을 위한 환경 구성
1. xml파일(가상의 컨테이너)에 객체를 메로리 올리는 순서
	1) 가상의 컨테이너 xml 파일에 선언된 class를 정의 한다.
	2) xml에 있는 객체를 main()에서 호출하여 사용할 수 있도록, 
		Resource 객체를 활용한다.
	3) BeanFactory를 선언하고, getBean("xml에 선언된 bean이름")
		메서드를 통해서, 객체를 main에서 호출한다.
	4) 호출된 객체를 사용한다.
*/
		// import org.springframework.core.io.Resource;
		// src하위 기준으로 xml주소 사용
		Resource rs = new ClassPathResource("springweb\\a02_di\\a01_DI.xml");
		BeanFactory bean = new XmlBeanFactory(rs);
		Person ob = (Person)bean.getBean("p01"); 	// a01_DI.xml에서 id="p01"
		ob.show();
		Person ob2 = (Person)bean.getBean("p02");
		ob2.show();
/*
ex) a2_DI.xml, A02_DiObjectExp.java 만들고, Product로 물건명과 가격 개수를 출력 처리하세요.
*/
		Woman w01 = (Woman)bean.getBean("w01");
		w01.info();
		Man m01 = (Man)bean.getBean("m01");
		m01.show();
/*
ex) Mart.java, Product.java로 마트에서 물건을 산 정보를 출력 처리하세요.
 */
		System.out.println("ex) Mart.java, Product.java 사용 ##");
		Mart mart01 = (Mart)bean.getBean("mart01");
		mart01.show();
	}

}
