package framework;

import framework.beans.BeanDefinition;
import framework.beans.PropertyValue;
import framework.beans.PropertyValues;
import framework.beans.config.XmlBeanDefinitionsReader;
import framework.beans.factory.AutowireCapableBeanFactory;
import framework.beans.factory.BeanFactory;
import framework.core.io.ResourceLoader;
import org.junit.Test;

public class UserTest {
    @Test
    public void test() throws Exception {
        XmlBeanDefinitionsReader xmlBeanDefinitionsReader = new XmlBeanDefinitionsReader(new ResourceLoader());
        xmlBeanDefinitionsReader.loadBeanDefinitions("beans.xml");

        BeanFactory beanFactory = new AutowireCapableBeanFactory();
        for (String beanName : xmlBeanDefinitionsReader.getBeanDefinitionMap().keySet()) {
            beanFactory.registerBeanDefinition(beanName, xmlBeanDefinitionsReader.getBeanDefinitionMap().get(beanName));
        }

        User user = (User) beanFactory.getBean("user");
        System.out.println(user);
    }
}
