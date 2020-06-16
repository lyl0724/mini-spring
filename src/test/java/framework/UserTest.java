package framework;

import framework.beans.BeanDefinition;
import framework.beans.PropertyValue;
import framework.beans.PropertyValues;
import framework.beans.factory.AutowireCapableBeanFactory;
import framework.beans.factory.BeanFactory;
import org.junit.Test;

public class UserTest {
    @Test
    public void test() throws Exception {
        BeanFactory beanFactory = new AutowireCapableBeanFactory();

        //现在还需要手动配置，后续这一块就是交给配置文件了，例如xml和注解
        BeanDefinition beanDefinition = new BeanDefinition();
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addProperty(new PropertyValue("name", "lyl"));
        propertyValues.addProperty(new PropertyValue("age", "24"));
        propertyValues.addProperty(new PropertyValue("isBoy", "true"));
        propertyValues.addProperty(new PropertyValue("height", "1.72"));
        beanDefinition.setPropertyValues(propertyValues);
        beanDefinition.setBeanClassName("framework.User");

        //注册bean
        beanFactory.registerBeanDefinition("lyl", beanDefinition);

        //获取bean
        User user = (User) beanFactory.getBean("lyl");
        System.out.println(user);
    }
}
