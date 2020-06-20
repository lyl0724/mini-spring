package aop;

import aop.account.AccountServiceImpl;
import framework.aop.AspectJExpressionPointcut;
import org.junit.Test;

public class AspectJPointcutTest {
    @Test
    public void test() {
        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
        String expression = "execution(* aop.account.*.*(..))";
        aspectJExpressionPointcut.setExpression(expgit ression);

        boolean res = aspectJExpressionPointcut.getClassFilter().matches(JdkDynamicTest.class);
        System.out.println(res);
    }
}
