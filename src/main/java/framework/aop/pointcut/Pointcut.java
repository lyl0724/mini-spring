package framework.aop.pointcut;

/**
 *  切点，确定哪些类和哪些方法需要被增强
 *  Pointcut接口封装了两个方法，可以用于获取匹配被增强的类和被增强的方法的工具类的实例
 */
public interface Pointcut {
    //获取ClassFilter对象，以便于验证哪些类会被创建，从而创建它们的代理对象
    ClassFilter getClassFilter();

    //获取MethodMatcher对象，以便于验证哪些方法需要被增强，从而进行拦截并加入横切逻辑
    MethodMatcher getMethodMatcher();
}
