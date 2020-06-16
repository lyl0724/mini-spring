package framework.beans.factory;

import framework.beans.BeanDefinition;
import framework.beans.PropertyValue;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

/**
 *  AbstractBeanFactory的小弟，帮助其实现了根据metadata，构造bean实例的这个过程。
 *  生成对象、注入属性的操作，主要是依靠反射来完成的
 */
public class AutowireCapableBeanFactory extends AbstractBeanFactory {
    @Override
    public Object doCreateBean(BeanDefinition beanDefinition) throws Exception {
        Object bean = beanDefinition.getBeanClass().newInstance();
        //注入bean所需的属性
        applyPropertyValues(bean, beanDefinition);
        return bean;
    }

    private void applyPropertyValues(Object bean, BeanDefinition beanDefinition) throws NoSuchFieldException, IllegalAccessException {
        for (PropertyValue propertyValue : beanDefinition.getPropertyValues().getPropertyValues()) {
            Field declaredField = beanDefinition.getBeanClass().getDeclaredField(propertyValue.getName());
            declaredField.setAccessible(true);
            //属性的类型可能是基本类型或者String，如果是基本类型，这里需要做将String做类型转换后再设置
            Type fieldType = declaredField.getType();
            String valueStr = propertyValue.getValue();
            declaredField.set(bean, typeConversion(fieldType, valueStr));
        }
    }

    /**
     *
     * @param fieldType 注入对象的属性类型，八大基本类型及其包装类 + String
     * @param valueStr String类型的变量
     * @return 根据bean中属性的类型，得到正确类型的属性
     */
    //@TODO 这样写感觉很蠢，留个坑，后续看看怎么改进
    private Object typeConversion(Type fieldType, String valueStr) {
        if (fieldType.equals(Integer.TYPE) || fieldType == Integer.class) {
            return Integer.parseInt(valueStr);
        } else if (fieldType.equals(Short.TYPE) || fieldType == Short.class) {
            return Short.parseShort(valueStr);
        } else if (fieldType.equals(Byte.TYPE) || fieldType == Byte.class) {
            return Byte.parseByte(valueStr);
        } else if (fieldType.equals(Long.TYPE) || fieldType == Long.class) {
            return Long.parseLong(valueStr);
        } else if (fieldType.equals(Float.TYPE) || fieldType == Float.class) {
            return Float.parseFloat(valueStr);
        } else if (fieldType.equals(Double.TYPE) || fieldType == Double.class) {
            return Double.parseDouble(valueStr);
        } else if (fieldType.equals(Character.TYPE) || fieldType == Character.class) {
            return valueStr.charAt(0);
        } else if (fieldType.equals(Boolean.TYPE) || fieldType == Boolean.class) {
            return Boolean.parseBoolean(valueStr);
        } else {
            return valueStr;
        }
    }
}
