package framework.aop.proxy;

import framework.aop.pointcut.MethodMatcher;
import framework.aop.target.TargetSource;
import lombok.Data;
import org.aopalliance.intercept.MethodInterceptor;

/**
 *  封装了创建代理对象所需要的资源
 */
@Data
public class AdvisedSupport {
    //目标对象
    private TargetSource targetSource;

    //方法拦截器
    //这里面定义了被增强的方法需要执行的逻辑
    private MethodInterceptor methodInterceptor;

    //方法匹配器，判断哪些方法需要被拦截，并织入横切逻辑
    private MethodMatcher methodMatcher;
}
