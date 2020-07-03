package framework.aop.joinpoint;

import framework.aop.target.TargetSource;
import lombok.AllArgsConstructor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

/**
 *  这个类是封装了目标对象的所有方法，可以看作是一个JoinPoint的实现
 */
@AllArgsConstructor
public class ReflectiveMethodInvocation implements MethodInvocation {
    protected TargetSource target;
    protected Method method;
    protected Object[] args;

    @Override
    public Method getMethod() {
        return method;
    }

    @Override
    public Object[] getArguments() {
        return args;
    }

    /**
     *  执行method，即调用被代理对象的方法
     */
    @Override
    public Object proceed() throws Throwable {
        //@TODO 后续修改可以让它支持拦截链
        return method.invoke(target, args);
    }

    @Override
    public Object getThis() {
        return target;
    }

    @Override
    public AccessibleObject getStaticPart() {
        return method;
    }
}
