package springweb.a02_di;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import springweb.a02_di.z01_vo.Man;
import springweb.a02_di.z01_vo.Mart;

public class A05_DI {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Resource rs = new ClassPathResource("springweb\\a02_di\\a05_DI.xml");
		BeanFactory bean = new XmlBeanFactory(rs);
		Man ob = (Man)bean.getBean("m01");
		ob.show();
		
		Mart ob2 = (Mart)bean.getBean("mt01");
		ob2.buyOne();
	}

}
