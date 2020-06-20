package framework.aop;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  保存目标对象的信息，也就是被代理对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TargetSource {
    private Object target;
    private Class<?> targetClass;
}
