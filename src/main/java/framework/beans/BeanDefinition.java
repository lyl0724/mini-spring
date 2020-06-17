package framework.beans;

import framework.core.util.ClassUtil;
import lombok.Data;

/**
 *  BeanDefinition是IOC容器中的一等公民，是容器中存放的基本单位
 *  包含实际的bean对象和一些描述或者用于构造bean对象的metadata
 */
@Data
public class BeanDefinition {
    //bean的名称，用于在ioc容器中取出bean
    private String beanName;

    //bean的实例
    private Object bean;

    //bean所属类的全限定名
    private String beanClassName;

    //bean所属类的Class对象
    private Class<?> beanClass;

    //bean所依赖的基本类型和String类型数据
    private PropertyValues propertyValues = new PropertyValues();

    public void setBeanClassName(String beanClassName) throws ClassNotFoundException {
        this.beanClassName = beanClassName;
        this.beanClass = ClassUtil.loadClass(beanClassName);
    }
}
