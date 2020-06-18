package framework.beans;

import lombok.Data;

@Data
public class BeanReference {
    private String beanName;
    private Object bean;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }
}
