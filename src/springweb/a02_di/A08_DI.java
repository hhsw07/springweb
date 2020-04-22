package springweb.a02_di;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import springweb.a02_di.z01_vo.Man;
import springweb.a02_di.z01_vo.Person;
import springweb.a02_di.z01_vo.Product;
import springweb.a02_di.z01_vo.Woman;

public class A08_DI {
	/*

			
	*/	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AbstractApplicationContext 
			ctx = new GenericXmlApplicationContext("springweb\\a02_di\\a08_DI.xml");
		// component-scan에 의해서 해당 container에 객체를 호출하여 사용할 수 있다.
		Woman woman=ctx.getBean("woman", Woman.class);
		//woman.setName("이영자");
		//woman.setAge(25);
		Man man01=ctx.getBean("man", Man.class);		
		man01.setName("홍길동");
		man01.setWoman(woman);
		man01.show();
		Person p01 = ctx.getBean("p01",Person.class);
		p01.show();
		Product prod = ctx.getBean("product", Product.class);
		prod.info();
		
		
		ctx.close();
	}

}




