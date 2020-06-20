package framework.aop;

public interface ClassFilter {
    //判断该类是否是要被增强的类
    boolean matches(Class<?> targetClass);
}
