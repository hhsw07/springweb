package springweb.a02_di;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import springweb.a02_di.z01_vo.Mart;

public class A03_DIMultiple {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Resource rs = new ClassPathResource("springweb\\a02_di\\a03_DI.xml");
		BeanFactory bean = new XmlBeanFactory(rs);
		Mart ob = (Mart)bean.getBean("mart01");
		ob.buyList();
		
	}

}
