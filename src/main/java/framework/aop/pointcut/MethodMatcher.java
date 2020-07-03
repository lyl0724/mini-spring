package framework.aop.pointcut;

import java.lang.reflect.Method;

public interface MethodMatcher {
    //判断该方法是否需要被增强，从而判断是否需要对它进行拦截，并织入横切逻辑
    boolean matches(Method method, Class<?> targetClass);
}
