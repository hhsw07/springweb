package springweb.a02_di;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import springweb.z01_vo.Member;

public class A09_DIExp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		AbstractApplicationContext 
			ctx = new GenericXmlApplicationContext("springweb\\a02_di\\a09_DIExp.xml");
		Member mem = ctx.getBean("member", Member.class);
		System.out.println("## 회원 정보 ##");
		System.out.println("ID: "+mem.getId());
		System.out.println("PASS: "+mem.getPass());
		
		ctx.close();
	}

}
