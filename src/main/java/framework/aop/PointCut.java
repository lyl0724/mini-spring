package framework.aop;

/**
 *  AOP中的切点，用于匹配哪些类和方法需要被增强，也就是要切在哪？
 */
public interface PointCut {
    //用于判断哪些类要被增强，即要为哪些类创建代理对象
    ClassFilter getClassFilter();

    //用于判断哪些方法要被增强
    MethodMatcher getMethodMatcher();
}
