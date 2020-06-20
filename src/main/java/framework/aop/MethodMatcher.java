package framework.aop;

import java.lang.reflect.Method;

public interface MethodMatcher {
    //判断该方法是否需要被增强
    boolean matches(Method method, Class<?> targetClass);
}
