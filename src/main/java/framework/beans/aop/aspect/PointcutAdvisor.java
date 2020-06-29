package framework.beans.aop.aspect;

import framework.beans.aop.advice.Advisor;
import framework.beans.aop.pointcut.Pointcut;

/**
 *  获取切点和通知器
 */
public interface PointcutAdvisor extends Advisor {
    Pointcut getPointcut();
}
