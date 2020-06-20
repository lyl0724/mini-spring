package framework.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 *  基于JDK动态代理实现的AOP，通过它获取代理对象
 */
public class JdkDynamicAopProxy implements AopProxy, InvocationHandler {
    private AdviceSupport adviceSupport;

    public JdkDynamicAopProxy(AdviceSupport adviceSupport) {
        this.adviceSupport = adviceSupport;
    }

    @Override
    public Object getProxy() {
        return Proxy.newProxyInstance(adviceSupport.getTargetSource().getTargetClass().getClassLoader(),
                adviceSupport.getTargetSource().getTargetClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return adviceSupport.getMethodInterceptor().invoke(
                new ReflectiveMethodInvocation(adviceSupport.getTargetSource().getTarget(), method, args)
        );
    }
}
