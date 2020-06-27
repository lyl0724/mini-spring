package framework.beans.aop.proxy;

import framework.beans.aop.advice.ReflectiveMethodInvocation;
import org.aopalliance.intercept.MethodInterceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 *  创建基于JDK动态代理的代理对象，实现了基本的织入功能
 *  在通过对Pointcut和Advice的配置后，我们就可以通过这个类完成织入，从而获得增强后的代理对象
 */
public class JdkDynamicAopProxy extends AbstractAopProxy implements InvocationHandler {
    public JdkDynamicAopProxy(AdvisedSupport advisedSupport) {
        super(advisedSupport);
    }

    @Override
    public Object getProxy() {
        return Proxy.newProxyInstance(advisedSupport.getTargetSource().getTarget().getClass().getClassLoader(),
                                        advisedSupport.getTargetSource().getInterfaces(),
                                        this);
    }

    /**
     *  JDK动态代理本质上是创建一个代理对象，我们在访问代理对象时，就会访问这个invoke中的逻辑
     *  因此，Advice的实现需要在invoke中表现出来
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //Advice的实现类，其中定义了增强的横切逻辑
        MethodInterceptor methodInterceptor = advisedSupport.getMethodInterceptor();
        //通过方法匹配器，判断该方法是否需要被增强
        //增强逻辑定义在interceptor中，如果该方法要被增强，则调用interceptor的invoke方法，表示调用的是代理对象增强后的方法
        //而增强的逻辑，我们需要自己写一个类去实现MethodInterceptor，在其中定义增强逻辑，这一步在实际操作中，是通过配置完成的
        //否则直接调用原方法的invoke方法，表示调用的是目标对象的原方法
        if (advisedSupport.getMethodMatcher() != null && advisedSupport.getMethodMatcher().matches(method, advisedSupport.getClass())) {
            return methodInterceptor.invoke(new ReflectiveMethodInvocation(advisedSupport.getTargetSource(), method, args));
        } else {
            return method.invoke(method, args);
        }
    }
}
