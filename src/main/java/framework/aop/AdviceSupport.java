package framework.aop;

import lombok.Data;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 *  代理类的元数据
 */
@Data
public class AdviceSupport {
    //被代理对象的信息
    private TargetSource targetSource;

    //增强
    private MethodInterceptor methodInterceptor;
}
