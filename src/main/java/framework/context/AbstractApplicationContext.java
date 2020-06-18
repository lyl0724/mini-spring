package framework.context;

import framework.beans.factory.AbstractBeanFactory;

/**
 *  对BeanFactory进行增强，这里主要提供了一个refresh方法，用于加载配置
 *  实际上就是将BeanDefinitionReader和BeanFactory整合起来
 */
public abstract class AbstractApplicationContext implements ApplicationContext {
    protected AbstractBeanFactory beanFactory;

    public AbstractApplicationContext(AbstractBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Override
    public Object getBean(String beanName) throws Exception {
        return beanFactory.getBean(beanName);
    }

    public abstract void refresh() throws Exception;
}
