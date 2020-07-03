package framework.aop.aspect;

import framework.aop.pointcut.AspectJExpressionPointcut;
import lombok.Getter;
import org.aopalliance.aop.Advice;

/**
 *  封装的切面实体类
 *  包含了增强和切点两部分信息
 */
@Getter
public class AspectJPointcutAdvisor {
    private AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();

    private Advice advice;

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    public void setExpression(String expression) {
        this.pointcut.setExpression(expression);
    }
}
