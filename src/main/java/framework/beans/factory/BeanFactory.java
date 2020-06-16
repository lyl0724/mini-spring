package framework.beans.factory;

import framework.beans.BeanDefinition;

/**
 *  工厂的顶层接口，包含了两个基本方法：注册对象和从容器中获取对象
 */
public interface BeanFactory {
    Object getBean(String beanName);

    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) throws Exception;
}
