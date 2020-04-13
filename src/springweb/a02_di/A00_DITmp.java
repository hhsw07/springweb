package springweb.a02_di;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class A00_DITmp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Resource rs = new ClassPathResource("springweb\\a02_di\\a00_DITmp.xml");
		BeanFactory bean = new XmlBeanFactory(rs);
		Object ob = (Object)bean.getBean("");
		
	}

}
