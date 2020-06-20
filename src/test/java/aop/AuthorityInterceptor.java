package aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class AuthorityInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        String emailPrefix = (String) methodInvocation.getArguments()[0];
        checkPower(emailPrefix);
        Object result = methodInvocation.proceed();
        return result;
    }

    public void checkPower(String emailPrefix) {
        if (!emailPrefix.equals("lyl")) {
            throw new IllegalArgumentException("no power");
        }
        System.out.println("has power!");
    }
}
