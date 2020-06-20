package framework.aop;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.aopalliance.intercept.MethodInvocation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

/**
 *  被代理对象的方法信息
 */
@AllArgsConstructor
@NoArgsConstructor
public class ReflectiveMethodInvocation implements MethodInvocation {
    private Object target;

    private Method method;

    private Object[] args;

    @Override
    public Method getMethod() {
        return method;
    }

    @Override
    public Object[] getArguments() {
        return args;
    }

    //执行被代理对象的方法，也就是增强前的逻辑
    @Override
    public Object proceed() throws Throwable {
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
