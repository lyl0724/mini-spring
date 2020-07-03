package framework.aop.pointcut;

public interface ClassFilter {
    //判断该类是否需要被增强
    boolean matches(Class<?> targetClass);
}
