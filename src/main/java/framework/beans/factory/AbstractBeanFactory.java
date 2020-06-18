package framework.beans.factory;

import framework.beans.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

/**
 *  AbstractBeanFactory是一个抽象类，实现了顶层接口BeanFactory。
 *  它提供了容器的基本实现，以及注册对象和从容器中获取对象的基本实现
 *
 *  并将对象的创建和依赖注入的过程交给继承它的小弟去完成，因此将创建对象实例的方法设置为抽象方法
 */
public abstract class AbstractBeanFactory implements BeanFactory {

    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    /**
     *  lazy-init，在用到bean的时候再创建实例
     */
    @Override
    public Object getBean(String beanName) throws Exception {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (beanDefinition == null) {
            throw new IllegalArgumentException(String.format("No bean named %s is defined", beanName));
        }
        Object bean = beanDefinition.getBean();
        if (bean == null) {
            bean = doCreateBean(beanDefinition);
        }
        return bean;
    }

    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) throws Exception {
        beanDefinitionMap.put(beanName, beanDefinition);
    }

    /**
     * @param beanDefinition bean的描述信息
     * @return 根据描述信息，得到的bean实例
     */
    public abstract Object doCreateBean(BeanDefinition beanDefinition) throws Exception;
}
