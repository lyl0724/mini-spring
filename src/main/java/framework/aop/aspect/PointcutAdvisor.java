package framework.aop.aspect;

import framework.aop.advice.Advisor;
import framework.aop.pointcut.Pointcut;

/**
 *  获取切点和通知器
 */
public interface PointcutAdvisor extends Advisor {
    Pointcut getPointcut();
}
