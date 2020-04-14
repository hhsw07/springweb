package springweb.a02_di;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import springweb.a02_di.z01_vo.AnnoMan;

public class A07_DI {
/*
# 의존 자동 주입 설정.
1. 코드 자동 주입
	1) java 코드에서 어노테이션으로 autowired를 처리할 것을 말한다.
	2) 처리 단계
		- @.xml : <context:annotation-config />
		- @.java :
			@Autowired, @Resource, @Qualifier, @Required
		

*/
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Resource rs = new ClassPathResource("springweb\\a02_di\\a07_DI.xml");
		BeanFactory bean = new XmlBeanFactory(rs);
		
		AbstractApplicationContext 
			ctx = new GenericXmlApplicationContext("springweb\\a02_di\\a07_DI.xml");
		AnnoMan ob = ctx.getBean("man01",AnnoMan.class);
		ob.show();
		
	}

}
