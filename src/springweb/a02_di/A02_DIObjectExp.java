package springweb.a02_di;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import springweb.a02_di.z01_vo.Product;

public class A02_DIObjectExp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
/*
ex) a2_DI.xml, A02_DiObjectExp.java 만들고, Product로 물건명과 가격 개수를 출력 처리하세요.
*/
		Resource rs = new ClassPathResource("springweb\\a02_di\\a02_DI.xml");
		BeanFactory bean = new XmlBeanFactory(rs);
		Product ob = (Product)bean.getBean("prod");
		ob.info();
	}

}
