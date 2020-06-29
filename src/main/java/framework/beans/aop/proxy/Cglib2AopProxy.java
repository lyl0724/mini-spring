package framework.beans.aop.proxy;

import framework.beans.aop.joinpoint.ReflectiveMethodInvocation;
import framework.beans.aop.target.TargetSource;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 *  基于cglib，创建代理对象
 */
public class Cglib2AopProxy extends AbstractAopProxy {
    public Cglib2AopProxy(AdvisedSupport advisedSupport) {
        super(advisedSupport);
    }

    @Override
    public Object getProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(advisedSupport.getTargetSource().getTargetClass());
        enhancer.setInterfaces(advisedSupport.getTargetSource().getInterfaces());
        //回调函数，我们在通过代理对象访问方法时，会执行这个逻辑


        return null;
    }

    private static class DynamicAdvisedInterceptor implements MethodInterceptor {
        private AdvisedSupport advisedSupport;

        public DynamicAdvisedInterceptor(AdvisedSupport advisedSupport) {
            this.advisedSupport = advisedSupport;
        }

        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            org.aopalliance.intercept.MethodInterceptor methodInterceptor = advisedSupport.getMethodInterceptor();
            //方法匹配成功，需要织入横切逻辑
            if (advisedSupport.getMethodMatcher() != null && advisedSupport.getMethodMatcher().matches(method, advisedSupport.getTargetSource().getTargetClass())) {
                return methodInterceptor.invoke(new CglibMethodInvocation(advisedSupport.getTargetSource(), method, objects, methodProxy));
            } else {
                return method.invoke(advisedSupport.getTargetSource().getTarget(), objects);
            }
        }

        private static class CglibMethodInvocation extends ReflectiveMethodInvocation {
            private MethodProxy methodProxy;

            public CglibMethodInvocation(TargetSource target, Method method, Object[] args, MethodProxy methodProxy) {
                super(target, method, args);
                this.methodProxy = methodProxy;
            }

            //之所以不采用原来的Invocation类，是因为cglib在使用上和jdk动态代理不一样。
            @Override
            public Object proceed() throws Throwable {
                return this.methodProxy.invoke(this.target, this.args);
            }
        }
    }
}

